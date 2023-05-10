<template>
  <el-row>
    <el-col :span="11">
      <div>
        <p class="section-title">更改密码</p>
        <el-form ref="ruleFormRefU" :model="updatePasswordForm" :rules="updatePasswordRule" :label-position="'top'">
          <el-form-item label="当前密码" prop="oldPassword">
            <el-input v-model="updatePasswordForm.oldPassword" />
          </el-form-item>
          <el-form-item label="新密码" prop="newPassword">
            <el-input v-model="updatePasswordForm.newPassword" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updatePassword(ruleFormRefU)">更新密码</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-col>
    <el-col :span="2">
      <div class="separator hidden-md-and-down"></div>
      <p></p>
    </el-col>
    <el-col :span="11">
      <div>
        <p class="section-title">更改邮箱</p>
        <el-form ref="ruleFormRefE" :model="updateEmailForm" :rules="updateEmailRule" :label-position="'top'">
          <el-form-item label="当前密码" prop="password">
            <el-input v-model="updateEmailForm.password" />
          </el-form-item>
          <el-form-item label="当前邮箱">
            <el-input v-model="User.email" disabled/>
          </el-form-item>
          <el-form-item label="新邮箱" prop="email">
            <el-input v-model="updateEmailForm.email" />
            <el-countdown v-if="startGrab" format="ss" :value="endTime" style="position: absolute;right: 10px" value-style="color:#409eff;font-size: 14px" @finish="startGrab=false"/>
            <el-button v-else style="position: absolute;right: 10px" type="text" @click="getVerifyCode(ruleFormRefE)">获取验证码
            </el-button>
          </el-form-item>
          <el-form-item label="验证码" prop="code">
            <el-input v-model="updateEmailForm.code" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="updateEmail(ruleFormRefE)">更新邮箱</el-button>
          </el-form-item>
        </el-form>
      </div>

    </el-col>
  </el-row>
</template>

<script setup lang="ts">
import {reactive, ref} from 'vue'
import {FormInstance, FormRules} from "element-plus";
import {User} from "../../common/gloableData";
import {userApi} from "../../api/userApi";
import {alerterror, alertsuccess} from "../../common/alert";
import {UpdateEmailType, UpdatePasswordType} from "../../common/typeClass";
import {teacherApi} from "../../api/teacherApi";

const startGrab=ref(false)
const endTime=ref(Date.now())

const getVerifyCode=async (formEl: FormInstance | undefined)=>{
  if (!formEl) return
  await formEl.validateField('email').then(valid => {
    if (valid) {
      userApi.getVerifyCode(updateEmailForm.email).then(res=>{
        endTime.value=Date.now()+1000*60
        startGrab.value=true
      })
    }
  })
}

// do not use same name with ref
const updatePasswordForm = reactive<UpdatePasswordType>({
  newPassword: "", oldPassword: ""
})

const ruleFormRefU = ref<FormInstance>()
const updatePasswordRule=reactive<FormRules>(
    {
      oldPassword:[
        {required: true, message: '请输入当前', trigger: 'blur'},
        {min:6,max:20,message:"密码不少于6位不大于20位"}
      ],
      newPassword:[
        {required: true, message: '请输入新密码', trigger: 'blur'},
        {min:6,max:20,message:"密码不少于6位不大于20位"}
      ]
    }
)

const updatePassword = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      userApi.updatePassword(updatePasswordForm).then(res=>{
        alertsuccess("密码修改成功")
      })
    }
  })
}


const updateEmail=async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      userApi.updateEmail(updateEmailForm).then(res=>{
            User.email=updateEmailForm.email
            alertsuccess("邮箱修改成功")
          }
      )
    }
  })
}

const updateEmailForm = reactive<UpdateEmailType>({
  code: "", email: "", password: ""
})

const ruleFormRefE = ref<FormInstance>()
const updateEmailRule=reactive<FormRules>(
    {
      password:[
        {required: true, message: '请输入当前', trigger: 'blur'},
        {min:6,max:20,message:"密码不少于6位不大于20位"}
      ],
      email:[
        {required: true, message: '请输入邮箱', trigger: 'blur'},
        {type:"email",message:"请输入正确的邮箱"}
      ],
      code:[
        {required: true, message: '请输入验证码', trigger: 'blur'},
        {len:6,message:"请输入正确验证码"}
      ],
    }
)

</script>


<style scoped>
.section-title {
  font-size: 21px;
  font-weight: 500;
  padding-top: 10px;
  padding-bottom: 20px;
  line-height: 30px;
}
.separator {
  display: block;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 50%;
  border: 1px dashed #eee;
}

</style>