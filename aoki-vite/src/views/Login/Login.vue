<template>
  <div id="login">
    <div class="login2 alignment-container">
      <el-space :size="100">
        <div>
          <h2 style="text-align: left">用户登录</h2>
          <el-form ref="form" :model="accountForm" :rules="rule">
            <el-form-item prop="username">
              <el-input v-model="accountForm.username" style="width: 280px;height:40px;margin-top: 20px" placeholder="用户名" >
                <template #prefix>
                  <el-icon style="font-size: 18px;"><User /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="accountForm.password" style="width: 280px;height:40px;margin-top: 20px" placeholder="密码" show-password>
                <template #prefix>
                  <el-icon style="font-size: 18px;"><Lock /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="userApi.login(accountForm)" style="width: 280px;height:40px;margin-top: 20px">登录
              </el-button>
            </el-form-item>
          </el-form>
          <router-link to="/home" type="primary" :underline="false" style="margin-top: 20px;float: left">新用户注册</router-link>
          <router-link to="/emaillogin" type="primary" :underline="false" style="margin-top: 20px;float: right">邮箱验证码登录</router-link>
        </div>
          <div>
            <p style="color:#9b9b9b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QQ群：1233445</p>
            <img  width="200px">
            <p style="color:#9b9b9b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有疑问加群咨询</p>
          </div>
      </el-space>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import {Account} from "../../common/constans";
import {userApi} from "../../common/userApi"
import {User,Lock} from "@element-plus/icons-vue";

const formSize = ref('default')
const accountFormRef = ref<FormInstance>()
const accountForm=reactive(Account)

const rule=reactive<FormRules>(
    {
      username:[
        {required: true, message: '请输入用户名', trigger: 'blur'}
      ],
      password:[
        {required: true, message: '请输入密码', trigger: 'blur'}
      ]
    }
)


</script>

<style scoped>
#login,html,body{
  background-color: #F2F4F7;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.login2 {
  border-radius: 20px;
  background: white;
  box-shadow: 4px 4px 4px #ccc;
  width: 850px;
  height: 550px;
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
