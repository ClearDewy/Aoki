<template>
  <el-row justify="space-between" style="height: 100%">
    <el-col span="8" style="display: flex">
      <el-image v-if="router.currentRoute.value.fullPath==='/home'" :src=Logo fit="scale-down" style="height: 58px;"/>
<!--      <el-breadcrumb :separator-icon="ArrowRight" style="display: flex;justify-content: center;align-items: center">-->
<!--        <el-breadcrumb-item v-for="item in router.currentRoute.value.matched" :to="item.path.toString()">{{ item.name }}</el-breadcrumb-item>-->
<!--      </el-breadcrumb>-->
      <el-button v-else @click="router.push(routerPath.Home)" type="primary" link style="color: black;"><el-icon class="el-icon--left">
        <arrow-left />
      </el-icon>返回</el-button>

    </el-col>
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
              <el-dropdown-item v-if="User.role===1" @click="createLessonRef.showDialog()">新建课程</el-dropdown-item>
              <el-dropdown-item @click="userApi.logout()">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

    </el-col>
  </el-row>
  <CreateLesson ref="createLessonRef"/>
</template>

<script lang="ts" setup>
import Logo from "../../assets/logo.png"
import {userApi} from "../../api/userApi"
import {ArrowDown, ArrowLeft, ArrowRight} from '@element-plus/icons-vue'
import {User} from "../../common/gloableData";
import router, {routerPath} from "../../router";
import {reactive, ref} from "vue";
import CreateLesson from "../lesson/CreateLesson.vue"

if (!User.value.avatarURL)User.value.avatarURL='https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'


// 绑定到上方绑定子组件
const createLessonRef=ref(null)




</script>

<style scoped>

</style>
