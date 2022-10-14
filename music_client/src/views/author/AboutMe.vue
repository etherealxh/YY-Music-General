
<template >

<div class="div">
  <div class="text">
    <img class="circleImg" src="../../assets/avatar/author.jpg" />
    <p class="line animation">
      有朋自远方来，不亦说乎。
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      在下， 易渊

    </p>
    <p class="line animation it">
      日月曰易，积水为渊&nbsp;&nbsp;&nbsp;
      欢迎您访问我的站点
    </p>
    <p>

    </p>
  </div>
  <el-button @click="goPage()" type="primary">留言板</el-button>
</div>


</template>

<script>


import {defineComponent} from "vue";
import router from "@/router";

export default defineComponent({
  setup() {
    function goPage(){
      router.push('/message');
    }

    return {
      goPage
    };
  },

})
</script>

<style lang="less"  scoped>
.div{
  width: 100%;
  height:750px;
  background-repeat: no-repeat;
  background-image: url("../../assets/images/1.jpg");
  animation:frams 15s infinite;
}
@keyframes frams {
  0%{
    background-image: url("../../assets/images/1.jpg");background-size: 100% 100%;background-repeat: no-repeat
  }
  25%{
    background-image: url("../../assets/images/2.jpg");background-size: 100% 100%;background-repeat: no-repeat
  }
  50%{
    background-image: url("../../assets/images/3.jpg");background-size: 100% 100%;background-repeat: no-repeat
  }
  75%{
    background-image: url("../../assets/images/4.jpg");background-size: 100% 100%;background-repeat: no-repeat
  }
  100%{
    background-image: url("../../assets/images/4.jpg");background-size: 100% 100%;background-repeat: no-repeat
  }
}


.circleImg{
  border-radius: 30px;
  width:60px;
  height:60px;
  margin: auto;
}

.text{width: 550px;
  height: 500px;
  margin: auto;
  position: fixed;
  top: 50%;
  left: 50%;
  -ms-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
}
.line{
  width:550px;
  border-right:2px solid #000;
  white-space:nowrap;
  overflow:hidden;
  margin: auto;
  font-family: "KaiTi";
  background: -webkit-linear-gradient(left, #0f0, #00f) 0 0 no-repeat;/*设置线性渐变*/
  -webkit-background-size: 400px;                        /*设置背景大小*/
  -webkit-background-clip: text;                            /*背景被裁剪到文字*/
  -webkit-text-fill-color: rgba(255, 255, 255, 0.5);        /*设置文字的填充颜色*/
  -webkit-animation: shine 3s infinite;                    /*设置动画*/
}
@-webkit-keyframes shine{   /*创建动画*/
  0%{
    background-position: 0 0;
  }
  100%{
    background-position: 100% 100%;
  }
}
.animation{
  animation:grow 10s steps(70) 1s normal both,
  blink 500ms steps(40) infinite normal;
}/*对动画设置时间和分段，对右边框设置动画样式*/
.it{ animation-delay: 11.2s;}/*下一段延时执行动画*/
@keyframes grow{
  from{width:0; }
  to{width:550px;/*段落长度 */   border-right-color:transparent; }
  0%{  border-right:0;  }/*光标起始位置不显示*/
  1%{ border-right:2px solid #000; }/*光标显示*/
  99%{ border-right:2px solid #000; }
  100%{border-right:0;}/*结束位置光标不显示*/
}
@keyframes blink{
  from{  border-right-color:#000}
  to{ border-right-color:transparent;/*文字透明化*/}
}

</style>