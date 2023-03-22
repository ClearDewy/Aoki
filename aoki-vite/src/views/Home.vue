<template>

  <el-container style="height: 100%">
    <el-header><nav-bar/></el-header>
    <el-container>
      <el-aside width="200px">Aside</el-aside>
      <el-main>Main</el-main>
    </el-container>
  </el-container>

</template>

<script setup lang="ts">
import {storage} from "../common/storage";
import NavBar from "../components/NavBar.vue"
</script>

<style scoped>

</style>