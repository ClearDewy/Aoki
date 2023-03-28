<script setup lang="ts">
import Login from "./views/Login.vue";
</script>

<template>
  <div id="app">
    <el-backtop :right="30" :bottom="20" />
    <router-view/>
  </div>

</template>

<style scoped>
#app{
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}


</style>
