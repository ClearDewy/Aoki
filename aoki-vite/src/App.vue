

<template>
  <div id="app">
    <el-backtop :right="30" :bottom="20" />
    <el-container style="height: 100%">
      <el-header v-if="router.currentRoute.value.meta.showHeader" style="background: white;box-shadow: var(--el-box-shadow-lighter);z-index: 2" ><NavBar/></el-header>
      <el-container>
        <el-main><router-view/></el-main>
      </el-container>
    </el-container>
  </div>
<!--<TaskAnswerEdit/>-->
</template>

<script setup lang="ts">
import router from "./router";
import NavBar from "./components/bar/NavBar.vue"
import TaskQuestionEdit from "./components/lesson/TaskQuestionEdit.vue";
import TaskAnswerEdit from "./components/lesson/TaskAnswerEdit.vue";


const Loading=document.getElementById("loading")

if(Loading) Loading.remove()

</script>

<style scoped>
#app{
  background-color: #F2F4F7;
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
