<template>
  <SideBar style="position: fixed;z-index: 999;left: 0;top: 60px"/>
  <div style="width: calc(100% - 63px);;height: 100%;margin-left: 63px">
    <router-view/>
  </div>

</template>

<script setup lang="ts">
import SideBar from "../../components/bar/SideBar.vue";

</script>

<style scoped>

</style>