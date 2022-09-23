<template>
    <el-container>
        <el-header>
            <y-y-header></y-y-header>
        </el-header>
        <el-main>
            <router-view />
            <y-y-current-play></y-y-current-play>
            <y-y-play-bar></y-y-play-bar>
            <y-y-scroll-top></y-y-scroll-top>
            <y-y-audio></y-y-audio>
        </el-main>
        <el-footer>
            <y-y-footer></y-y-footer>
        </el-footer>
    </el-container>
</template>

<script lang="ts" setup>
    import { getCurrentInstance } from "vue";
    import YYHeader from "../components/layouts/YYHeader.vue";
    import YYCurrentPlay from "../components/layouts/YYCurrentPlay.vue";
    import YYPlayBar from "../components/layouts/YYPlayBar.vue";
    import YYScrollTop from "../components/layouts/YYScrollTop.vue";
    import YYAudio from "../components/layouts/YYAudio.vue";
    import YYFooter from "../components/layouts/YYFooter.vue";

    const { proxy } = getCurrentInstance();

    if (sessionStorage.getItem("dataStore")) {
        proxy.$store.replaceState(Object.assign({}, proxy.$store.state, JSON.parse(sessionStorage.getItem("dataStore"))));
    }

    window.addEventListener("beforeunload", () => {
        sessionStorage.setItem("dataStore", JSON.stringify(proxy.$store.state));
    });
</script>

<style lang="scss" scoped>
  @import "@/assets/css/var.scss";
  @import "@/assets/css/global.scss";

  .el-container {
    min-height: calc(100% - 60px);
  }
  .el-header {
    padding: 0;
  }
  .el-main {
    padding-left: 0;
    padding-right: 0;
  }
</style>
