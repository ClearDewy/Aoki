<template>
  <el-card :body-style="el_card__body" >
    <template #header>
      <div class="card-header">
        <span>邮箱配置</span>
      </div>
    </template>
    <el-form ref="ruleFormRef" :model="mailConfigForm" label-width="120px" :rules="rules" label-position="left">
      <el-form-item label="主机" prop="host">
        <el-input v-model="mailConfigForm.host" />
      </el-form-item>
      <el-form-item label="端口" prop="port">
        <el-input v-model="mailConfigForm.port" />
      </el-form-item>
      <el-form-item label="邮箱" prop="username">
        <el-input v-model="mailConfigForm.username" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="mailConfigForm.password" type="password"/>
      </el-form-item>
      <el-form-item label="协议" prop="protocol">
        <el-switch v-model="mailConfigForm.protocol" active-value="smtps" inactive-value="smtp"/>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="updateMailConfig(ruleFormRef)">保存</el-button>
      </el-form-item>
    </el-form>
  </el-card>


</template>

<script setup lang="ts">
import {el_card__body} from "../../common/style";
import {FormInstance, FormRules} from "element-plus";
import {onMounted, reactive, ref} from "vue";
import {MailConfigType} from "../../common/typeClass";
import {adminApi} from "../../api/adminApi";
import {alerterror, alertsuccess} from "../../common/alert";
import {userApi} from "../../api/userApi";

const ruleFormRef = ref<FormInstance>()
const mailConfigForm = reactive({
  username: '',
  password: '',
  host: '',
  port: null,
  protocol:'smtp'
})

const rules = reactive<FormRules>({
  username: [
    { required: true,type: "email", message: '请输入正确邮箱', trigger: 'blur' },
  ],
  password: [
    {
      required: true,
      message: '请输入密码',
      trigger: 'blur',
    },
  ],
  host: [
    {
      required: true,
      message: '请输入主机',
      trigger: 'blur',
    },
  ],
  port: [
    {

      required: true,
      message: '请输入端口号',
      trigger: 'blur',
    },
  ]
})

const getMailConfig = ()=>{
  adminApi.getMailConfig().then(res=>{
    mailConfigForm.username=res.data.username
    mailConfigForm.password=res.data.password
    mailConfigForm.host=res.data.host
    mailConfigForm.port=res.data.port
    mailConfigForm.protocol=res.data.protocol
  }).catch(e=>{
    alerterror("邮箱配置获取失败:"+e.toString())
    console.log(e)
  })
}

const updateMailConfig = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      adminApi.updateMailConfig(mailConfigForm as unknown as MailConfigType).then(res=>{
        alertsuccess("邮箱配置更新成功")
      }).catch(e=>{
        alerterror("邮箱配置更新失败:"+e.toString())
        console.log(e)
      })
    }
  })
}




onMounted(()=>{
  getMailConfig()
})

</script>

<style scoped>

</style>