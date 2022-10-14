<template>
  <div class="container">
    <div class="handle-box">
      <el-button @click="deleteAll">批量删除</el-button>
    </div>
    <el-table height="700px" border size="small" :data="data" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="40" align="center"></el-table-column>
      <el-table-column label="ID" prop="id" width="100" align="center"></el-table-column>
      <el-table-column label="用户昵称" prop="userId" width="200" align="center"></el-table-column>
      <el-table-column label="反馈内容" prop="message" width="800" align="center">
        <template v-slot="scope">
          <p style="height: 100px; overflow: scroll">
            {{ scope.row.message }}
          </p>
        </template>
      </el-table-column>

      <el-table-column label="反馈时间" prop="createTime" width="300" align="center">
        <template v-slot="scope">
          <div>{{ getBirth(scope.row.createTime) }}</div>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="273" align="center">
        <template v-slot="scope">
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
  <y-y-del-dialog :delVisible="delVisible" @confirm="confirm" @cancelRow="delVisible = $event"></y-y-del-dialog>
</template>

<script>
import {computed, defineComponent, ref} from "vue";
import YYDelDialog from "@/components/dialog/YYDelDialog.vue";
import { HttpManager } from "@/api/index";
import { getBirth } from "@/utils";
export default  defineComponent({
  components: {
    YYDelDialog,
  },setup(){
    const tableData = ref([]); // 记录数据，用于显示
    const tempDate = ref([]); // 记录数据，用于搜索时能临时记录一份
    const pageSize = ref(5); // 页数
    const currentPage = ref(1); // 当前页

    // 计算当前表格中的数据
    const data = computed(() => {
      return tableData.value.slice((currentPage.value - 1) * pageSize.value, currentPage.value * pageSize.value);
    });

    // 获取当前页
    function handleCurrentChange(val) {
      currentPage.value = val;
    }

    getData();

    async function getData() {
      tableData.value = [];
      tempDate.value = [];
      const result = (await HttpManager.getALLFeedback()) ;
      tableData.value = result.data;
      tempDate.value = result.data;
      currentPage.value = 1;
    }

    /**
     * 删除
     * */
    const idx = ref(-1); //记录当前要删除的行
    const multipleSelection = ref([]); //记录当前要删除的列表
    const delVisible = ref(false); //显示删除框

    async function confirm(){
      const result = (await HttpManager.deleteFeedback(idx.value))  ;
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
      data,
      tableData,
      currentPage,
      pageSize,
      getBirth,
      delVisible,
      confirm,
      handleCurrentChange,
      handleSelectionChange,
      deleteRow,
      deleteAll

    }

  }
})
</script>

<style scoped>

</style>