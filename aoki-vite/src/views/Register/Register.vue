<template>
  <div id="register">
    <div class="register2">

      <div>
        <h2 style="text-align: left">用户注册</h2>
        <el-form ref="form" :model="UserRegisterForm" label-width="" :rules="rule">
          <el-form-item prop="email">
            <el-input v-model="UserRegisterForm.email" style="width: 280px;height:40px;margin-top: 20px" placeholder="请输入邮箱">
              <template #prefix>
                <el-icon style=";font-size: 18px;"><Message /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="code">
            <el-input v-model="UserRegisterForm.code" style="width: 280px;height:40px;margin-top: 20px" placeholder="请输入验证码">
              <template #prefix>
                <el-icon style=";font-size: 18px;"><Loading /></el-icon>
              </template>
            </el-input>
            <el-countdown v-if="startGrab" format="ss" :value="endTime" style="position: absolute;right: 10px;top: 24px;" value-style="color:#409eff;font-size: 14px" @finish="resetGetVerifyCode"/>
            <el-button v-else style="position: absolute;right: 10px;top: 24px;" type="text" @click="getVerifyCode">获取验证码
            </el-button>
          </el-form-item>
          <el-form-item prop="password">
            <el-input v-model="UserRegisterForm.password" style="width: 280px;height:40px;margin-top: 20px" placeholder="密码" show-password>
              <template #prefix>
                <el-icon style="font-size: 18px;"><Lock /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="verifyCode(UserRegisterForm.email,UserRegisterForm.code)"
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
import {UserRegister} from "../../common/constans";
import {userApi} from "../../common/userApi"
import {reactive, ref} from "vue";
import {Message,Lock,Loading} from "@element-plus/icons-vue";
import {POST} from "../../common/api";
import router from "../../router";

const formSize = ref('default')
const UserRegisterForm=reactive(UserRegister)
// 表单检验
const rule=reactive<FormRules>(
    {
      email:[
        {required: true, message: '请输入邮箱', trigger: 'blur'},
        {type:"email",message:"请输入正确的邮箱"}
      ],
      code:[
        {required: true, message: '请输入验证码', trigger: 'blur'},
        {len:6,message:"请输入正确验证码"}
      ],
      password:[
        {required: true, message: '请输入密码', trigger: 'blur'},
        {min:6,max:20,message:"密码不少于6位不大于20位"}
      ]
    }
)

// 验证验证码，验证码正确则跳转到完善信息页面
const verifyCode=(email:string,code:string)=>{
  userApi.verifyCode(email,code).then(res=>{

    router.push("/certification")
  })
}

const startGrab=ref(false)
const endTime=ref(Date.now())

const getVerifyCode=()=>{
  userApi.getVerifyCode(UserRegisterForm.email).then(res=>{
    endTime.value=Date.now()+1000*60
    startGrab.value=true
  })
}
const resetGetVerifyCode=()=>{
  startGrab.value=false
}

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
