
<template>
  <div class="container">
    <div class="handle-box">
      <el-button @click="deleteAll">批量删除</el-button>
    </div>
    <el-table height="700px" border size="small" :data="data" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40" align="center"></el-table-column>
      <el-table-column label="ID" prop="id" width="50" align="center"></el-table-column>
      <el-table-column label="用户昵称" prop="userId" width="110" align="center"></el-table-column>
      <el-table-column label="留言内容" prop="message" width="120" align="center">
        <template v-slot="scope">
          <p style="height: 100px; overflow: scroll">
            {{ scope.row.message }}
          </p>
        </template>
      </el-table-column>

      <el-table-column label="留言时间" prop="createTime" width="60" align="center">
        <template v-slot="scope">
          <div>{{ getBirth(scope.row.createTime) }}</div>
        </template>
      </el-table-column>

      <el-table-column label="是否回复" prop="replied" width="120" align="center">
        <template v-slot="scope">
          <div>{{changeReplied(scope.row.replied) }}</div>
        </template>
      </el-table-column>
      <el-table-column label="回复人" prop="replyName" width="100" align="center"></el-table-column>
      <el-table-column label="回复内容" prop="replyContent">
        <template v-slot="scope">
          <p style="height: 100px; overflow: scroll">
            {{ scope.row.replyContent}}
          </p>
        </template>
      </el-table-column>
      <el-table-column label="是否展示" width="120" align="center">
        <template v-slot="scope">
          <el-switch
              v-model="scope.row.enabled"
              :active-value="1"
              :inactive-value="0"
              active-text="展示"
              inactive-text="不展示"
              @change="changeSwitch(scope.row)"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="160" align="center">
        <template v-slot="scope">
          <el-button @click="editRow(scope.row)">回复</el-button>
          <el-button type="danger" @click="deleteRow(scope.row.id)">删除</el-button>
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

  <!-- 回复弹出框 -->
  <el-dialog title="回复" v-model="editVisible">
    <el-form label-width="60px" :model="editFrom">
      <el-form-item label="留言信息" prop="">
        <el-card >
          <span >{{ editFrom.message }}</span>
          <div />
          <span >{{ editFrom.id}} 提交于 {{getBirth( editFrom.createTime ) }}  </span>
        </el-card>
      </el-form-item>
      <el-form-item label="回复留言" prop="replyContent">
        <el-input
            v-model="editFrom.replyContent"
            rows="5"
            type="textarea"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEdit">回 复</el-button>
      </span>
    </template>
  </el-dialog>

  <y-y-del-dialog :delVisible="delVisible" @confirm="confirm" @cancelRow="delVisible = $event"></y-y-del-dialog>
</template>

<script>
import {computed, defineComponent, getCurrentInstance, reactive, ref} from "vue";
import YYDelDialog from "@/components/dialog/YYDelDialog.vue";
import mixin from "@/mixins/mixin";
import { HttpManager } from "@/api/index";
import { getBirth } from "@/utils";
export default defineComponent({
  components: {
    YYDelDialog,
  },setup(){
    const { proxy } = getCurrentInstance();
    const { changeReplied , routerManager } = mixin();

    const tableData = ref([]); // 记录数据，用于显示
    const tempDate = ref([]); // 记录数据，用于搜索时能临时记录一份
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页

    // 计算当前表格中的数据
    const data = computed(() => {
      return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
    });

    getData();

    async function getData() {
      tableData.value = [];
      tempDate.value = [];
      const result = (await HttpManager.getALLMessageBoard()) ;
      tableData.value = result.data;
      tempDate.value = result.data;
      currentPage.value = 1;
    }

    // 获取当前页
    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    async function changeSwitch(row) {
      let id = row.id;
      let userId = row.userId;
      let message = row.message;
      let createTime = row.createTime;
      let replied = row.replied;
      let replyName = row.replyName;
      let replyContent = row.replyContent;
      let updateTime = row.updateTime;
      let enabled = row.enabled=== 0 ? "0":"1";
        const result = (await HttpManager.replyMessageBoard({
          id,
          userId,
          message,
          replied,
          replyName,
          replyContent,
          enabled
        }));
        if (result.success)  getData();
    }
    /**
     * 回复
     * */
    const editVisible = ref(false);
    const editFrom = reactive({
      id:"",
      userId:"",
      message:"",
      createTime:new Date(),
      replied:"",
      replyName:"",
      replyContent:"",
      updateTime:new Date(),
      enabled: "",
    });
     function editRow(row) {
       editVisible.value = true;
       editFrom.id = row.id;
       editFrom.userId = row.userId;
       editFrom.message = row.message;
       editFrom.createTime = row.createTime;
       editFrom.replied = row.replied;
       editFrom.replyName = row.replyName;
       editFrom.replyContent = row.replyContent;
       editFrom.updateTime = row.updateTime;
       editFrom.enabled = row.enabled;
     }
     async function saveEdit() {
       try {
         let id = editFrom.id;
         let userId = editFrom.userId;
         let message = editFrom.message;
         let replied = 1;
         let replyName = sessionStorage.getItem("username");
         //let replyName =3;
         let replyContent = editFrom.replyContent;
         let enabled = editFrom.enabled;

         const result = (await HttpManager.replyMessageBoard({
           id,
           userId,
           message,
           replied,
           replyName,
           replyContent,
           enabled
         }));
         /*(proxy as any ).$message({
           message: result.message,
           type: result.type,
         })*/;
         if (result.success)  getData();
         editVisible.value = false;
       } catch (error) {
         console.error(error);
       }
     }

    /**
     * 删除
     * */
    const idx = ref(-1); //记录当前要删除的行
    const multipleSelection = ref([]); //记录当前要删除的列表
    const delVisible = ref(false); //显示删除框

    async function confirm(){
      const result = (await HttpManager.deleteMessageBoard(idx.value))  ;
      /*(proxy as any).$message({
        message: result.message,
        type: result.type,
      });*/
      if (result.success)  getData();
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


    return{
      tableData,
      pageSize,
      currentPage,
      data,
      editVisible,
      editFrom,
      changeReplied,
      delVisible,
      getBirth,
      editRow,
      saveEdit,
      handleCurrentChange,
      handleSelectionChange,
      deleteRow,
      confirm,
      deleteAll,
      changeSwitch

    }
  }
})
</script>

<style scoped>

</style>