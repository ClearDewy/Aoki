<template>
  <el-menu
      router
      :default-active="router.currentRoute.value.path"
      class="el-menu-vertical-demo"
      :collapse="isCollapse"
      style="height: 100%"
      @mouseenter="isCollapse=false"
      @mouseleave="isCollapse=true"
  >
    <div>
      <el-image v-if="Lesson.avatarURL" :src="Lesson.avatarURL" style="padding: 14px"
                />
      <div style="padding: 14px" v-if="!isCollapse">
        <span style="display: block; text-align: left;font-size: var(--el-font-size-extra-large);width: 100%">{{Lesson.name}}</span>
        <span style="display: block; text-align: left;font-size: var(--el-font-size-small);width: 100%">{{Lesson.ownerName}}</span>
      </div>
    </div>
    <el-menu-item index="/lesson">
      <el-icon><icon-menu /></el-icon>
      <template #title>主页</template>
    </el-menu-item>
    <el-menu-item index="/lesson/task">
      <el-icon><document /></el-icon>
      <template #title>作业</template>
    </el-menu-item>
    <el-menu-item index="/lesson/select-topic">
      <el-icon><setting /></el-icon>
      <template #title>选题</template>
    </el-menu-item>
    <el-menu-item index="/lesson/team" v-if="Lesson.teamMemberLimit!==null">
      <el-icon><setting /></el-icon>
      <template #title>团队</template>
    </el-menu-item>
    <el-menu-item index="/lesson/lesson-setting">
      <el-icon><setting /></el-icon>
      <template #title>设置</template>
    </el-menu-item>
  </el-menu>

</template>

<script lang="ts" setup>
import { ref } from 'vue'
import {Lesson} from "../../common/gloableData";
import router from "../../router";
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'

const isCollapse = ref(true)

</script>

<style>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
</style>
