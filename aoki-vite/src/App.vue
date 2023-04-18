

<template>
  <div id="app">
    <el-backtop :right="30" :bottom="20" />
    <el-container style="height: 100%">
      <el-header v-if="router.currentRoute.value.meta.showHeader" style="background: white;box-shadow: var(--el-box-shadow-lighter);" ><NavBar/></el-header>
      <el-container>
        <SideBar v-if="router.currentRoute.value.meta.showAside"/>
        <el-main><router-view/></el-main>
      </el-container>
    </el-container>
  </div>
<!--    <el-button @click="milestonesEditRef.showCreateDialog()">show</el-button>-->
<!--    <MilestonesEdit ref="milestonesEditRef" @refreshMilestonesList="refreshMilestonesList"/>-->
</template>

<script setup lang="ts">
import NavBar from "./components/bar/NavBar.vue"
import SideBar from "./components/bar/SideBar.vue"
import router from "./router";
import account from "./components/setting/Account.vue"
import Test from "./Test.vue";
import Setting from "./views/user/Setting.vue"
import CreateLesson from "./components/lesson/CreateLesson.vue";
import MilestonesEdit from "./components/lesson/MilestonesEdit.vue";
import {ref} from "vue";
import {alertsuccess} from "./common/alert";

// const milestonesEditRef=ref(null)
// const refreshMilestonesList=()=>{
//   alertsuccess("refresh")
// }

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
