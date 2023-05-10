<template>
  <el-menu
      router
      :default-active="router.currentRoute.value.path"
      class="el-menu-vertical-demo"
      style="height: 100%"
  >

    <div style="margin-top: 60px">
      <el-image :src="Logo" style="width: 90%;padding-left: 5%;padding-right: 5%"/>
    </div>
    <el-menu-item index="/admin/dashboard">
      <template #title>仪表盘</template>
    </el-menu-item>
    <el-menu-item index="/admin/conf">
      <template #title>配置</template>
    </el-menu-item>
    <el-menu-item index="/admin/user">
      <template #title>用户</template>
    </el-menu-item>
  </el-menu>

</template>

<script lang="ts" setup>
import { ref } from 'vue'
import {Lesson,User} from "../../common/gloableData";
import router from "../../router";
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import Logo from "../../assets/logo.png"

const isCollapse = ref(true)

</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.inline-span {
  display: inline-block;
  width: 100%;
  overflow: hidden;
  white-space: nowrap;  /* 避免换行 */
  text-overflow: ellipsis;
}
</style>
