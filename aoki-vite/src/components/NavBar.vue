<template>

  <el-row justify="space-between">
    <el-col span="4"><el-image :src=Logo fit="scale-down" style="height: 58px;"></el-image></el-col>
    <el-col span="4">
        <el-dropdown style="height: 100%;">
          <el-space style="height: 100%;">
              <el-avatar :src=Avatar fit="scale-down" size="100"/>
              <el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
          </el-space>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="userApi.logout()">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

    </el-col>
  </el-row>

</template>

<script lang="ts" setup>
import Logo from "../assets/logo.png"
import Avatar from "../assets/avatar.png"
import {userApi} from "../common/userApi"
import { ArrowDown } from '@element-plus/icons-vue'

import { ref } from 'vue'


</script>

<style scoped>



</style>
