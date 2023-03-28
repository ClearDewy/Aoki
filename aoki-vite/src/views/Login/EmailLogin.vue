<template>
  <div id="email-login">
    <div class="email-login2 alignment-container">
      <el-space :size="100">
        <div>
          <h2 style="text-align: left;">用户登录</h2>
          <el-form ref="form" :model="accountForm" :rules="rule">
            <el-form-item prop="email">
              <el-input v-model="accountForm.email" style="width: 280px;height: 40px;margin-top: 20px" placeholder="请输入邮箱">
                <template #prefix>
                  <el-icon style="font-size: 18px;"><Message /></el-icon>
                </template>
              </el-input>
            </el-form-item>
            <el-form-item prop="code">
              <el-input v-model="accountForm.password" style="width: 280px;height: 40px;margin-top: 20px" placeholder="请输入验证码">
                <template #prefix>
                  <el-icon style="font-size: 18px;"><Loading /></el-icon>
                </template>
              </el-input>
              <el-button style="position: absolute;right: 10px;top: 24px;" type="text" @click="userApi.login(accountForm)">获取验证码
              </el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" style="width: 280px;height: 40px;margin-top: 20px" @click="userApi.login(accountForm)">登录</el-button>
            </el-form-item>
          </el-form>
          <router-link to="/" type="primary" :underline="false" style="margin-top: 20px;float: left">返回></router-link>
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
import {Account} from "../../common/constans";
import {Loading,Message} from "@element-plus/icons-vue";

const formSize = ref('default')
const accountFormRef = ref<FormInstance>()
const accountForm=reactive(Account)

const rule=reactive<FormRules>(
    {
      username:[
        {required: true, message: '请输入邮箱', trigger: 'blur'}
      ],
      password:[
        {required: true, message: '请输入验证码', trigger: 'blur'}
      ]
    }
)
</script>

<style scoped>
#email-login,html,body{
  background-color: #F2F4F7;
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
