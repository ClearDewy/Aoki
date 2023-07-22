<template>
  <div id="login" v-if="!eLogin">
    <div class="login2 alignment-container">
      <div style="margin: auto">
        <h2 style="text-align: center">用户登录</h2>
        <el-form ref="ruleFormRef" :model="accountForm" :rules="rule">
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
            <el-button type="primary" @click="login(ruleFormRef)" style="width: 280px;height:40px;margin-top: 20px">登录
            </el-button>
          </el-form-item>
        </el-form>
        <el-button type="primary" link style="float: left" @click="router.push(routerPath.Register)">新用户注册</el-button>
        <el-button type="primary" link style="float: right" @click="eLogin=true">邮箱验证码登录</el-button>
        <!--          <router-link @click="eLogin=true" type="primary" :underline="false" style="margin-top: 20px;float: right"></router-link>-->
      </div>
    </div>
  </div>
  <div id="email-login" v-else>
    <div class="email-login2 alignment-container">
      <div style="margin: auto">
        <h2 style="text-align: left;">用户登录</h2>
        <el-form ref="ruleFormRefE" :model="emailLoginForm" :rules="ruleE">
          <el-form-item prop="email">
            <el-input v-model="emailLoginForm.email" style="width: 280px;height: 40px;margin-top: 20px" placeholder="请输入邮箱">
              <template #prefix>
                <el-icon style="font-size: 18px;"><Message /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="code">
            <el-input v-model="emailLoginForm.code" style="width: 280px;height: 40px;margin-top: 20px" placeholder="请输入验证码">
              <template #prefix>
                <el-icon style="font-size: 18px;"><Loading /></el-icon>
              </template>
              <template #append>
                <el-countdown v-if="startGrab" format="ss" :value="endTime" value-style="color:#409eff;font-size: 14px" @finish="resetGetVerifyCode"/>
                <el-button v-else type="primary" @click="getVerifyCode(ruleFormRefE)">获取验证码
                </el-button>
              </template>
            </el-input>

          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 280px;height: 40px;margin-top: 20px" @click="emailLogin(ruleFormRefE)">登录</el-button>
          </el-form-item>
        </el-form>
        <el-button @click="eLogin=false" type="primary" link style="float: left">返回</el-button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import {userApi} from "../../api/userApi"
import {User,Lock,Loading,Message} from "@element-plus/icons-vue";
import {AccountType, EmailLoginType} from "../../common/typeClass";
import router from "../../router";
import {routerPath} from "../../router";
import {teacherApi} from "../../api/teacherApi";
import {alerterror, alertsuccess} from "../../common/alert";

const eLogin=ref(false)

const accountForm=reactive<AccountType>({
  password: "", username: ""
})

const ruleFormRef = ref<FormInstance>()
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

const emailLoginForm=reactive<EmailLoginType>({
  code: "", email: ""}
)

const startGrab=ref(false)
const endTime=ref(Date.now())

const ruleFormRefE = ref<FormInstance>()
const ruleE=reactive<FormRules>(
    {
      email:[
        {required: true, message: '请输入邮箱', trigger: 'blur'},
        {type:"email",message:"请输入正确的邮箱"}
      ],
      code:[
        {required: true, message: '请输入验证码', trigger: 'blur'}
      ]
    }
)

const getVerifyCode=async (formEl: FormInstance | undefined)=>{
  if (!formEl) return
  await formEl.validateField('email').then(valid => {
    if (valid) {
      userApi.getVerifyCode(emailLoginForm.email).then(res=>{
        endTime.value=Date.now()+1000*60
        startGrab.value=true
      })
    }
  })
}
const resetGetVerifyCode=()=>{
  startGrab.value=false
}

const login=async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      userApi.login(accountForm)
    }
  })
}

const emailLogin=async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      userApi.emailLogin(emailLoginForm)
    }
  })
}

</script>

<style scoped>
#login,#email-login,html,body{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.login2,.email-login2 {
  border-radius: 20px;
  background: white;
  box-shadow: 4px 4px 4px #ccc;
  width: 500px;
  height: 550px;
  margin: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
