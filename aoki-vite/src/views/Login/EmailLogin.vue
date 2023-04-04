<template>
  <div id="email-login">
    <div class="email-login2 alignment-container">
      <el-space :size="100">
        <div>
          <h2 style="text-align: left;">用户登录</h2>
          <el-form ref="form" :model="emailLoginForm" :rules="rule">
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
              </el-input>
              <el-countdown v-if="startGrab" format="ss" :value="endTime" style="position: absolute;right: 10px;top: 24px;" value-style="color:#409eff;font-size: 14px" @finish="resetGetVerifyCode"/>
              <el-button v-else style="position: absolute;right: 10px;top: 24px;" type="text" @click="getVerifyCode">获取验证码
              </el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" style="width: 280px;height: 40px;margin-top: 20px" @click="userApi.emaillogin(emailLoginForm)">登录</el-button>
            </el-form-item>
          </el-form>
          <router-link to="/login" type="primary" :underline="false" style="margin-top: 20px;float: left">返回></router-link>
        </div>
        <div>
          <p style="color:#9b9b9b;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QQ群：1233445</p>
          <img  width="200px">
          <p style="color:#9b9b9b;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有疑问加群咨询</p>
        </div>
      </el-space>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import {userApi} from "../../common/userApi"
import {Account, EmailLogin} from "../../common/constans";
import {Loading,Message} from "@element-plus/icons-vue";

const formSize = ref('default')
const emailLoginForm=reactive(EmailLogin)

const startGrab=ref(false)
const endTime=ref(Date.now())

const rule=reactive<FormRules>(
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

const getVerifyCode=()=>{
  userApi.getVerifyCode(emailLoginForm.email).then(res=>{
    endTime.value=Date.now()+1000*60
    startGrab.value=true
  })
}
const resetGetVerifyCode=()=>{
  startGrab.value=false
}
</script>

<style scoped>
#email-login,html,body{
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.email-login2 {
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
