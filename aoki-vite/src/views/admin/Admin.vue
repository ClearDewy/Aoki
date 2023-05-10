<template>
  <SideBar style="position: fixed;z-index: 10;left: 0;top: 0"/>
  <div style="width: calc(100% - 200px);height: 100%;margin-left: 200px;">
    <router-view/>
  </div>
</template>

<script setup lang="ts">
import SideBar from "../../components/admin/SideBar.vue";

</script>

<style scoped>

</style>