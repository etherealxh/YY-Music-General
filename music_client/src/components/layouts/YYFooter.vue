<template>
    <div class="yy-footer">
      <div class="flex justify-space-between mb-4 flex-wrap gap-4">
        <el-button
            v-for="button in buttons"
            :key="button.text"
            :type="button.type"
            @click="goPage(button.id)"
            text
        >{{ button.text }}</el-button>
      </div>
        <p v-for="(item, index) in footerList" :key="index">
            {{ item }}
        </p>
    </div>
  <!-- 反馈弹出框 -->
  <el-dialog title="反馈" v-model="feedbackVisible">
    <el-form label-width="60px" :model="feedbackFrom">
      <el-form-item >
        <el-card >
          <span >用户{{ feedbackFrom.username}} 您好 欢迎您访问我的站点，来说说您对本站点的改进建议吧 </span>
        </el-card>
      </el-form-item>
      <el-input
          v-model="feedbackFrom.message"
          rows="5"
          type="textarea"
      />
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="feedbackVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveFeedback()">反 馈</el-button>
      </span>
    </template>
  </el-dialog>

  <!-- 帮助弹出框 -->
  <el-dialog title="帮助" v-model="helpVisible">
    <el-card>
      <span>用户您好，该帮助仅为开发人员提供API查看哦</span>
    </el-card>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="helpVisible = false">取 消</el-button>
        <el-button type="primary" @click="helpVisible = false">确定</el-button>
      </span>
    </template>
  </el-dialog>

<!-- 条款弹出框 -->
  <el-dialog title="使用条款" v-model="clauseVisible">
    开发者：易渊（Ethereal）提醒您在使用本网站前请仔细阅读这些条款。    2022.10.13
    <el-card v-for="(item, index) in CLAUSE" :key="index">
      <span>{{item.value}}:<br />{{item.label}}</span>
    </el-card>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="clauseVisible = false">我已知晓</el-button>
      </span>
    </template>
  </el-dialog>

  <login-dialog v-model="loginDialogVisible" ></login-dialog>

</template>

<script>
import {defineComponent, reactive, readonly, ref} from "vue";
import router from "@/router";
import {CLAUSE} from "@/enums";
import {HttpManager} from "@/api";
import store from "@/store";
import LoginDialog from "@/components/dialog/LoginDialog";

    export default defineComponent({
      components: {LoginDialog},
      setup() {
            const footerList = readonly([
                "Copyright © 2022 YY_Music owner xh.ethereal(LCF)",
            ]);
            const buttons = [
              { type: 'primary', text: '关于',id: '0'},
              { type: 'primary', text: '帮助',id: '1'},
              { type: 'primary', text: '条款',id: '2'},
              { type: 'primary', text: '反馈',id: '3'},
            ]
           function goPage(id){
              switch (id) {
                case '0':
                  router.push('/about-me');
                  break;
                case '1':
                  helpVisible.value = true;
                  break;
                case '2':
                  clauseVisible.value = true;
                  break;
                case '3':

                  feedbackFrom.username = store.getters.username;
                  if (store.getters.token == false ){
                    loginDialogVisible.value = true;
                  }else {
                    feedbackVisible.value = true;
                  }
                break;
              }
           }
           /**
            * 反馈
            * */
           const feedbackVisible = ref(false);
           const loginDialogVisible = ref(false);
           const feedbackFrom = reactive({
            username: "",
            message: ""
          })

          function FeedbackVisibleChange() {
            feedbackVisible.value = true;
            feedbackFrom.username = store.getters.username;
          }

          async function saveFeedback() {
            try {
              let username = store.getters.username;
              let message = feedbackFrom.message;

              const result = (await HttpManager.addFeedback({
                username,
                message
              }))
              if (result.success) ;
              feedbackVisible.value = false;
            } catch (error) {
              console.error(error);
            }
          }

          /**
          * 帮助
          * */
          const helpVisible = ref(false);
          /**
           * 条款
           * */
          const clauseVisible = ref(false);
          return {
            footerList,
            buttons,
            CLAUSE,
            goPage,
            feedbackVisible,
            feedbackFrom,
            FeedbackVisibleChange,
            loginDialogVisible,
            saveFeedback,
            helpVisible,
            clauseVisible
            };
        },
    });
</script>

<style lang="scss" scoped>
  @import "@/assets/css/var.scss";
  @import "@/assets/css/global.scss";

  .yy-footer {
    width: 100%;
    height: $footer-height;
    padding: 20px 0;
    box-sizing: border-box;
    font-size: 14px;
    @include layout(center, center, column);
  }

  p {
    height: 30px;
  }
</style>
