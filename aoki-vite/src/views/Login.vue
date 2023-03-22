<template>
  <el-form
      ref="accountFormRef"
      :model="accountForm"
      :rules="rules"
      label-width="120px"
      class="demo-ruleForm"
      :size="formSize"
      status-icon
  >
    <el-form-item label="用户名">
      <el-input v-model="accountForm.username" placeholder="请输入用户名" type="username"/>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="accountForm.password" placeholder="请输入密码" type="password" show-password/>
    </el-form-item>
  </el-form>
  <el-form-item>
    <el-button round @click="userApi.login(accountForm)">登录</el-button>
  </el-form-item>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import {Account} from "../common/constans";
import {userApi} from "../common/userApi"

const formSize = ref('default')
const accountFormRef = ref<FormInstance>()
const accountForm=reactive(Account)

const rules={
  username:[
    {required: true, message: '请输入用户名', trigger: 'blur'}
  ],
  password:[
    {required: true, message: '请输入密码', trigger: 'blur'}
  ]
}


</script>

<style scoped>

</style>