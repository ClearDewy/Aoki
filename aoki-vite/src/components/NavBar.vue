<template>
  <el-row justify="space-between">
    <el-col span="4"><el-image :src=Logo fit="scale-down" style="height: 58px;"></el-image></el-col>
    <el-col span="4">
        <el-dropdown style="height: 100%;">
          <el-space style="height: 100%;">
              <el-avatar :src="User.avatarURL" fit="scale-down"/>
              <el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
          </el-space>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="router.push('/setting')">个人信息</el-dropdown-item>
<!--              老师则显示新建课程-->
              <el-dropdown-item v-if="User.role===1">新建课程</el-dropdown-item>
              <el-dropdown-item @click="userApi.logout()">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

    </el-col>
  </el-row>

</template>

<script lang="ts" setup>
import Logo from "../assets/logo.png"
import {userApi} from "../common/userApi"
import {ArrowDown } from '@element-plus/icons-vue'
import {User} from "../common/constans";
import router from "../router";

import {onMounted, ref} from 'vue'

if (!User.avatarURL)User.avatarURL='https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'
// User.avatarURL="/api/public/avatar/ef2cc205a3c248009929502acd9560d7.png"

</script>

<style scoped>
.el-dropdown:hover{
  color: #409eff;
}

</style>
