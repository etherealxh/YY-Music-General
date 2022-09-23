<template>
<div>
  <el-carousel direction="vertical" class="swiper-container" type="card"  height="35vw" :interval="3000" indicator-position="none" >
    <el-carousel-item v-for="(item, index) in swiperList" :key="index">
      <img :src="HttpManager.attachImageUrl(item.pic)" />
    </el-carousel-item>
  </el-carousel>
</div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { HttpManager } from "@/api";

const swiperList = ref([]);// 轮播图 每次都在进行查询

try {

  HttpManager.getBannerList().then((res) => {
    swiperList.value = (res as ResponseBody).data.sort();
  });

} catch (error) {
  console.error(error);
}


</script>

<style lang="scss" scoped>
  @import "@/assets/css/var.scss";
  @import "@/assets/css/global.scss";

  /*轮播图*/
  .swiper-container {
    width: 80%;
    margin: auto;
    padding-top: 20px;
    img {
      width: 100%;
    }
  }
  .swiper-container:deep(.el-carousel__indicators.el-carousel__indicators--outside) {
    display: inline-block;
    transform: translateX(30vw);
  }

  @media screen and (min-width: $sm) {
    .login-logo {
      width: 50vw;
    }
  }

  @media screen and (max-width: $sm) {
    .login-logo {
      width: 100vw;
    }
  }
</style>
