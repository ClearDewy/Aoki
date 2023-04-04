<template>
  <div id="certification">
    <div class="certification2">
      <div>
        <h2 style="text-align: left">完善信息</h2>
        <el-form ref="form" :model="UserRegisterForm">
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
            <el-button type="primary" @click="userApi.register(UserRegisterForm)"
                       style="width: 280px;height:40px;margin-top: 20px">完成
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import {UserRegister} from "../../common/constans";
import {userApi} from "../../common/userApi"

const formSize = ref('default')
const UserRegisterForm=reactive(UserRegister)
const majorList=ref([])   // 专业列表

// 表单检验
const rule=reactive<FormRules>(
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
/deep/ .select_major  .el-input__inner{
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

