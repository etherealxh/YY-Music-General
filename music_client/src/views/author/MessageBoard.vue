<template>
  <div>
    <el-card class="el-card-d" shadow="always">
      <el-timeline infinite-scroll-disabled="disabled">
        <div v-if="data.length > 0">
          <el-timeline-item
              v-for="(item, index) in data"
              :key="index"
              :timestamp="parseTime(item.createTime, '{y}-{m}-{d}')"
              placement="top"
          >
            <el-card class="el-card-m">
              <span class="el-card-m-content">{{ item.message }}</span>
              <div />
              <span class="el-card-m-nick-name">{{ item.userId }} 提交于 {{ parseTime(item.createTime) }}  </span>
              <div />
              <span v-if="item.replyContent" class="el-card-m-reply">{{ item.replyName }}回复：{{ item.replyContent }} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ parseTime(item.updateTime) }} </span>
            </el-card>
          </el-timeline-item>
        </div>
        <div v-else>
          <el-timeline-item placement="top">
            <el-card class="el-card-m">
              <p class="el-card-m-nick-name">  没有任何留言</p>
            </el-card>
          </el-timeline-item>
        </div>
      </el-timeline>
      <el-pagination
          background
          :current-page="currentPage"
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="tableData.length"
          :hide-on-single-page="true"
          @current-change="handleCurrentChange"
      />

      <!-- 留言弹出框 -->
      <el-dialog title="留言" v-model="messageVisible">
        <el-form label-width="60px" :model="messageFrom">
          <p>海内存知己，天涯若比邻</p>
          <br />
          <el-form-item>
          <el-card >
            <span >用户{{ messageFrom.userId}} 欢迎您访问我的站点，请留下您想说的话吧 </span>
          </el-card>
          </el-form-item>
            <el-input
                v-model="messageFrom.message"
                rows="5"
                type="textarea"
            />

        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="messageVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveMessage()">留 言</el-button>
      </span>
        </template>
      </el-dialog>
    </el-card>


  </div>
  <div>
  <el-button type="primary" @click="messageVisibleChange()" plain>我也留个言</el-button>
  </div>
</template>

<script>
import { parseTime } from '@/utils/index'
import { HttpManager } from "@/api";
import {computed, defineComponent, reactive, ref} from "vue";
import store from "@/store";
export default defineComponent({
setup() {
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
    let i=1;
    const result = (await HttpManager.getEnabledMessageBoard(i)) ;
    tableData.value = result.data;
    tempDate.value = result.data;
    currentPage.value = 1;
  }

  // 获取当前页
  function handleCurrentChange(val) {
    currentPage.value = val;
  }

  /**
   * 留言
   * */
  const messageVisible = ref(false);
  const messageFrom = reactive({
    userId:"",
    message:""
  })
  function messageVisibleChange() {
    messageVisible.value = true;
    //messageFrom.userId = sessionStorage.getItem("username");
    messageFrom.userId = store.getters.username;
  }
  async function saveMessage(){
    try {
      let userId = store.getters.username;
      let message = messageFrom.message;

      const result = (await HttpManager.addMessageBoard({
        userId,
        message
      }))

      if (result.success) getData();
      messageVisible.value = false;
    } catch (error){
      console.error(error);
    }
  }
  return{
    data,
    tableData,
    parseTime,
    pageSize,
    currentPage,
    handleCurrentChange,
    messageVisible,
    messageFrom,
    messageVisibleChange,
    saveMessage
  }

}

})
</script>

<style scoped>
.el-card-d {
  float: left;
  margin-top: 20px;
  margin-left: 10%;
  width: 80%;
  height: 700px;
  background: rgb(252, 250, 250);
}
.el-card-m{
  width: 600px;
  height: 130px;
}
.el-card-m-content{
  display: block;
  font-weight: bold;
}
.el-card-m-nick-name{
  display: block;
  font-size: x-small;
  margin-top: 15px;
  color: gray;
}
.el-card-m-reply{
  display: block;
  font-size: xx-small;
  margin-top: 15px;
  color: gray;
}
.el-card-messages {
  float: left;
  margin-top: 20px;
  margin-left: 10%;
  width: 70%;
}
.message-nick-name {
  width: 50%;
}
.message-text {
  margin-top: 10px;
  display: block;
  width: 50%;
}
.submit-message {
  margin-top: 10px;
  width: 80px;
  background: rgb(235, 245, 247);
  color: cadetblue;
  text-align: center;
  letter-spacing: 20px;
}
</style>