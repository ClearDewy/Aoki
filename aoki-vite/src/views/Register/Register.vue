<template>
  <div id="register">
    <div class="register2">

      <div>
        <h2 style="text-align: left">用户注册</h2>
        <el-form ref="form" :model="accountForm" label-width="">
          <el-form-item>
            <el-input v-model="accountForm.email" style="width: 280px;height:40px;margin-top: 20px" placeholder="请输入邮箱">
              <template #prefix>
                <el-icon style=";font-size: 18px;"><Message /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-input v-model="accountForm.code" style="width: 280px;height:40px;margin-top: 20px" placeholder="请输入验证码">
              <template #prefix>
                <el-icon style=";font-size: 18px;"><Loading /></el-icon>
              </template>
            </el-input>
            <el-button style="position: absolute;right: 10px;top: 24px;" type="text" @click="userApi.login(accountForm)">获取验证码
            </el-button>
          </el-form-item>
          <el-form-item>
            <el-input v-model="accountForm.password" style="width: 280px;height:40px;margin-top: 20px" placeholder="密码" show-password>
              <template #prefix>
                <el-icon style="font-size: 18px;"><Lock /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="userApi.login(accountForm)"
                       style="width: 280px;height:40px;margin-top: 20px">下一步
            </el-button>
          </el-form-item>
          <router-link to="/login" type="primary" :underline="false" style="float: left">>返回</router-link>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import type { FormInstance, FormRules } from 'element-plus'
import {Account} from "../../common/constans";
import {userApi} from "../../common/userApi"
import {reactive, ref} from "vue";
import {Message,Lock,Loading} from "@element-plus/icons-vue";

const formSize = ref('default')
const accountFormRef = ref<FormInstance>()
const accountForm=reactive(Account)

</script>

<style scoped>
#register,html,body{
  background-color: #F2F4F7;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.register2 {
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
