<template>
  <y-y-header></y-y-header>
  <y-y-aside></y-y-aside>
  <div class="content-box" :class="{ 'content-collapse': collapse }">
    <router-view></router-view>
  </div>
  <y-y-audio></y-y-audio>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import YYHeader from "@/components/layouts/YYHeader.vue";
import YYAudio from "@/components/layouts/YYAudio.vue";
import YYAside from "@/components/layouts/YYAside.vue";
import emitter from "@/utils/emitter";


const collapse = ref(false);
emitter.on("collapse", (msg) => {
  collapse.value = msg as boolean;
});
</script>

<style scoped>
.content-box {
  position: absolute;
  left: 150px;
  right: 0;
  top: 60px;
  bottom: 0;
  overflow-y: scroll;
  transition: left 0.3s ease-in-out;
  padding: 20px;
}

.content-collapse {
  left: 65px;
}
</style>
