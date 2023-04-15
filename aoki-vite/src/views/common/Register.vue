<template>
  <div id="register" v-if="!emailVarified">
    <div class="register2">

      <div>
        <h2 style="text-align: left">用户注册</h2>
        <el-form ref="ruleFormRef1" :model="UserRegisterForm" label-width="" :rules="ruleR">
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
            <el-button type="primary" @click="nextStep(ruleFormRef1)"
                       style="width: 280px;height:40px;margin-top: 20px">下一步
            </el-button>
          </el-form-item>
          <router-link to="/login" type="primary" :underline="false" style="float: left">>返回</router-link>
        </el-form>
      </div>
    </div>
  </div>
  <div id="certification" v-else>
    <div class="certification2">
      <div>
        <h2 style="text-align: left">完善信息</h2>
        <el-form ref="ruleFormRef2" :model="UserRegisterForm" :rules="ruleC">
          <el-form-item prop="name">
            <el-input v-model="UserRegisterForm.name" style="width: 280px;height:40px;margin-top: 20px" placeholder="请输入真实姓名"></el-input>
          </el-form-item>
          <el-form-item prop="rule">
            <el-radio-group v-model="UserRegisterForm.role" style="margin-top: 20px">
              <el-radio :label=1>教师</el-radio>
              <el-radio :label=2>学生</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="username">
            <el-input v-model="UserRegisterForm.username" style="width: 280px;height:40px;margin-top: 20px" placeholder="请输入学工号"></el-input>
          </el-form-item>
          <el-form-item prop="major">
            <el-select v-model="UserRegisterForm.major" placeholder="请选择专业" style="width: 280px;margin-top: 20px" class="select_major">
              <el-option v-for="(x,index) in majorList" :key="index" :value="x"
                         :label="x"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="register(ruleFormRef2)"
                       style="width: 280px;height:40px;margin-top: 20px">完成
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import type { FormInstance, FormRules } from 'element-plus'
import {userApi} from "../../api/userApi"
import {onMounted, reactive, ref} from "vue";
import {Message,Lock,Loading} from "@element-plus/icons-vue";
import {UserRegisterType} from "../../common/typeClass";
import {teacherApi} from "../../api/teacherApi";
import {alerterror, alertsuccess} from "../../common/alert";

// 验证成功显示完善信息页面
const emailVarified=ref(false)

const UserRegisterForm=reactive<UserRegisterType>({
  code: "", email: "", major: "", name: "", password: "", role: 2, username: ""
})
// 表单检验
const ruleFormRef1 = ref<FormInstance>()
const ruleR=reactive<FormRules>(
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
        {min:6,max:31,message:"密码不少于6位不大于31位"}
      ]
    }
)

// 验证验证码，验证码正确则跳转到完善信息页面
const nextStep=async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      userApi.verifyCode(UserRegisterForm.email,UserRegisterForm.code).then(res=>{
        emailVarified.value=true
      })
    }
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

const majorList=ref([])   // 专业列表

// 表单检验
const ruleFormRef2 = ref<FormInstance>()
const ruleC=reactive<FormRules>(
    {
      name:[
        {required: true, message: '请输入姓名', trigger: 'blur'}
      ],
      rule:[
        {required: true, message: '请选择身份', trigger: 'blur'}
      ],
      username:[
        {required: true, message: '请输入学工号', trigger: 'blur'}
      ],
      major:[
        {required:true,message:"请选择专业",trigger:"blur"}
      ]
    }
)

const register=async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      userApi.register(UserRegisterForm)
    }
  })
}


// 组件挂载时运行
onMounted(()=>{
  userApi.getAllMajor().then(res=>{
    if (res)
      majorList.value=res.data
    console.log(majorList.value)
  })
})

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

:deep(.select_major  .el-input__inner){
  height:40px;
}
#certification,html,body{
  background-color: #F2F4F7;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.certification2 {
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
