<template>
  <SideBar style="position: fixed;z-index: 10;left: 0;top: 0"/>
  <div style="width: calc(100% - 63px);height: 100%;margin-left: 63px">
    <router-view/>
  </div>

</template>

<script setup lang="ts">
import SideBar from "../../components/bar/SideBar.vue";
import {userApi} from "../../api/userApi";
import {Lesson, User} from "../../common/gloableData";
import {alerterror} from "../../common/alert";
import {storage} from "../../common/storage";
import {onMounted} from "vue";

onMounted(()=>{
  if (User.value.role===2){
    userApi.getMyTopic(Lesson.value.id as number).then(res=>{
      storage.setItem("topic",res.data)
    }).catch(e=>{
      alerterror(e.message)
    })
  }
})
</script>

<style scoped>

</style>