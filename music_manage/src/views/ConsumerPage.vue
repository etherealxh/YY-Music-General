<template>
  <div class="container">
    <div class="handle-box">
      <el-button @click="deleteAll">批量删除</el-button>
      <el-input v-model="searchWord" placeholder="筛选用户"></el-input>
      <el-button type="danger" @click="blackList">黑名单</el-button>
    </div>

    <el-table height="700px" border size="small" :data="data" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40" align="center"></el-table-column>
      <el-table-column label="ID" prop="id" width="50" align="center"></el-table-column>
      <el-table-column label="用户头像" width="102" align="center">
        <template v-slot="scope">
          <img :src="attachImageUrl(scope.row.avatar)" style="width: 80px" />
        </template>
      </el-table-column>
      <el-table-column label="用户名" prop="username" width="80" align="center"></el-table-column>
      <el-table-column label="性别" width="50" align="center">
        <template v-slot="scope">
          <div>{{ changeSex(scope.row.sex) }}</div>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" prop="phoneNum" width="120" align="center"></el-table-column>
      <el-table-column label="邮箱" prop="email" width="120" align="center"></el-table-column>
      <el-table-column label="生日" width="120" align="center">
        <template v-slot="scope">
          <div>{{ getBirth(scope.row.birth) }}</div>
        </template>
      </el-table-column>
      <el-table-column label="签名" prop="introduction"></el-table-column>
      <el-table-column label="地区" prop="location" width="70" align="center"></el-table-column>
      <el-table-column label="收藏" width="90" align="center">
        <template v-slot="scope">
          <el-button @click="goCollectPage(scope.row.id)">收藏</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="90" align="center">
        <template v-slot="scope">
          <el-button type="danger" @click="deleteRow(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column label="处罚" width="150" align="center">
        <template v-slot="scope">
        <el-button type="danger" @click="joinBlackList(scope.row.username)">加入黑名单</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      class="pagination"
      background
      layout="total, prev, pager, next"
      :current-page="currentPage"
      :page-size="pageSize"
      :total="tableData.length"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>

  <!-- 加入黑名单 -->
  <el-dialog title="加入黑名单" v-model="addBlackListDialogVisible">
    <el-form label-width="80px" :model="registerForm">
      <el-form-item label="用户名:" prop="username">
       {{registerForm.username}}
      </el-form-item>
      <el-form-item label="拉黑原因:" prop="reason">
        <el-input type="textarea" v-model="registerForm.reason"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addBlackListDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addBlackList">确 定</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 黑名单 -->
  <el-dialog title="黑名单" v-model="blackListDialogVisible">
    <el-table height="500px"  border size="small" :data="blackListData" >
      <el-table-column label="id" width="50" align="center">
        <template v-slot="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="username" width="120" align="center">
        <template v-slot="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column label="reason" width="280" align="center">
        <template v-slot="scope">
            {{ scope.row.reason }}
        </template>
      </el-table-column>
      <el-table-column label="createTime" width="100" align="center">
        <template v-slot="scope">
            {{ getBirth(scope.row.createTime)}}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="160" align="center">
        <template v-slot="scope">
          <el-button type="danger" @click="deleteBlackList(scope.row.id)">移除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>


  <!-- 删除提示框 -->
  <y-y-del-dialog :delVisible="delVisible" @confirm="confirm" @cancelRow="delVisible = $event"></y-y-del-dialog>
</template>

<script lang="ts">
import { defineComponent, getCurrentInstance, watch, ref, reactive, computed } from "vue";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api";
import { RouterName } from "@/enums";
import YYDelDialog from "@/components/dialog/YYDelDialog.vue";
import { getBirth } from "@/utils";

export default defineComponent({
  components: {
    YYDelDialog,
  },
  setup() {
    const { proxy } = getCurrentInstance();
    const { changeSex, routerManager } = mixin();

    const tableData = ref([]); // 记录歌曲，用于显示
    const tempDate = ref([]); // 记录歌曲，用于搜索时能临时记录一份歌曲列表
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页
    const blackListData = ref([]) // 记录黑名单数据
    // 计算当前表格中的数据
    const data = computed(() => {
      return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
    });

    const searchWord = ref(""); // 记录输入框输入的内容
    watch(searchWord, () => {
      if (searchWord.value === "") {
        tableData.value = tempDate.value;
      } else {
        tableData.value = [];
        for (let item of tempDate.value) {
          if (item.username.includes(searchWord.value)) {
            tableData.value.push(item);
          }
        }
      }
    });

    getData();

    // 获取用户信息
    async function getData() {
      tableData.value = [];
      tempDate.value = [];
      const result = (await HttpManager.getAllUser()) as ResponseBody;
      tableData.value = result.data;
      tempDate.value = result.data;
      currentPage.value = 1;
    }
    // 获取当前页
    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    /**
     * 路由
     */
    function goCollectPage(id) {
      const breadcrumbList = reactive([
        {
          path: RouterName.Consumer,
          name: "用户管理",
        },
        {
          path: "",
          name: "收藏信息",
        },
      ]);
      proxy.$store.commit("setBreadcrumbList", breadcrumbList);
      routerManager(RouterName.Collect, { path: RouterName.Collect, query: { id } });
    }

    /**
     * 删除
     */
    const idx = ref(-1); // 记录当前要删除的行
    const multipleSelection = ref([]); // 记录当前要删除的列表
    const delVisible = ref(false); // 显示删除框

    async function confirm() {
      const result = (await HttpManager.deleteUser(idx.value)) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });
      if (result) getData();
      delVisible.value = false;
    }
    function deleteRow(id) {
      idx.value = id;
      delVisible.value = true;
    }
    function handleSelectionChange(val) {
      multipleSelection.value = val;
    }
    function deleteAll() {
      for (let item of multipleSelection.value) {
        deleteRow(item.id);
        confirm();
      }
      multipleSelection.value = [];
    }

    /**
    * 黑名单
    * */
    //获取黑名单数据
    async function getBlackListData(){
      blackListData.value = [];
      const result = (await HttpManager.getAllBlacklist()) as ResponseBody;
      blackListData.value = result.data;
    }
    const blackListDialogVisible = ref(false);

    function blackList(){
      getBlackListData();
      blackListDialogVisible.value = true;
    }
    /**
     * 加入黑名单
     * */
    function joinBlackList(username){
      addBlackListDialogVisible.value = true;
      registerForm.username=username;

    }
    const addBlackListDialogVisible = ref(false);
    const registerForm = reactive({
      username:"",
      reason:""
    })
    async function addBlackList(){
      let username = registerForm.username;
      let reason = registerForm.reason;

      const result = (await HttpManager.addBlackList({username,reason})) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });

      if (result.success){
        getData();
        registerForm.reason = "";
      }
      addBlackListDialogVisible.value = false;
    }
    /**
     * 移除黑名单
     * */
    async function deleteBlackList(id){
      const result = (await HttpManager.deleteBlackList(id)) as ResponseBody;
      (proxy as any).$message({
        message: result.message,
        type: result.type,
      });
      if (result.success){
        getBlackListData();
      }
    }


    return {
      searchWord,
      data,
      tableData,
      blackListData,
      delVisible,
      pageSize,
      currentPage,
      registerForm,
      addBlackListDialogVisible,
      blackListDialogVisible,
      blackList,
      deleteBlackList,
      joinBlackList,
      addBlackList,
      deleteAll,
      handleSelectionChange,
      handleCurrentChange,
      changeSex,
      getBirth,
      deleteRow,
      confirm,
      goCollectPage,
      attachImageUrl: HttpManager.attachImageUrl,
    };
  },
});
</script>

<style scoped></style>
