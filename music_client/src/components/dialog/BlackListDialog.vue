<template>
  <!-- 警告提示框 -->
  <el-dialog title="警告" v-model="blackListDialogVisible" width="300px" center>
    <div class="del-dialog-cnt" align="center">由于你对站点的不当使用，现已被加入站点黑名单，无法使用该功能</div>
  </el-dialog>
</template>

<script>

import {ref} from "vue";


export default {
  name: "BlackListDialog"
  ,
  setup(props) {

    const blackListDialogVisible = ref(props);

    return {
      blackListDialogVisible,

     }
  },
}
</script>

<style scoped>

</style>