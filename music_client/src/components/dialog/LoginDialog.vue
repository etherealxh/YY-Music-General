<template>
<!-- 登录提示框 -->
  <el-dialog title="登录提醒" v-model="loginDialogVisible" width="300px" center>
    <div class="del-dialog-cnt" align="center">您尚未登录，无法使用该功能，请登录后再试</div>
    <template #footer>
        <span class="dialog-footer">

          <el-button type="primary" @click="goSignIn">确 定</el-button>
        </span>
    </template>
  </el-dialog>
</template>

<script>
import {ref} from "vue";
import {RouterName} from "../../enums";
import mixin from "@/mixins/mixin";
export default {
  name: "LoginDialog"
  ,
  setup(props) {
     const { routerManager } = mixin();
     const loginDialogVisible = ref(props);

     function goSignIn(){
       loginDialogVisible.value = false;
       routerManager(RouterName.SignIn, { path: RouterName.SignIn });
     }
    return {
      loginDialogVisible,

      goSignIn}
  },

}
</script>

<style scoped>

</style>