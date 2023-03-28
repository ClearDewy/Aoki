<template>
  <div id="certification">
    <div class="certification2">
      <div>
        <h2 style="text-align: left">完善信息</h2>
        <el-form ref="form" :model="accountForm">
          <el-form-item>
            <el-input v-model="accountForm.name" style="width: 280px;height:40px;margin-top: 20px" placeholder="请输入真实姓名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-radio-group v-model="accountForm.role" style="margin-top: 20px">
              <el-radio :label="1">教师</el-radio>
              <el-radio :label="2">学生</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-input :disabled="btnstatus" v-model="accountForm.username" style="width: 280px;height:40px;margin-top: 20px" placeholder="请输入学工号"></el-input>
          </el-form-item>
          <el-form-item>
            <el-select v-model="accountForm.major" placeholder="请选择专业" style="width: 280px;margin-top: 20px" class="select_major">
              <el-option v-for="(x,index) in major" :key="index" :value="x.majorname"
                         :label="x.majorname"></el-option>
            </el-select>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="userApi.login(accountForm)"
                       style="width: 280px;height:40px;margin-top: 20px">完成
            </el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import {Account} from "../../common/constans";
import {userApi} from "../../common/userApi"

const formSize = ref('default')
const accountFormRef = ref<FormInstance>()
const accountForm=reactive(Account)

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

