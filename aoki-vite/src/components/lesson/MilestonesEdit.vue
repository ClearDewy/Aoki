<template>
  <el-dialog
      v-model="showMilestonesEdit"
      :title="flag?'新建阶段任务':'修改阶段任务'"
      :before-close="handleClose"
      align-center
      center
      width="1005px"
  >
    <el-form ref="ruleFormRef" :model="milestonesForm" label-width="120px" :rules="rule" label-position="top" :inline="true">
      <el-form-item label="阶段目标名称" prop="name" style="width: 40%;margin-right: 10%">
        <el-input v-model="milestonesForm.name" placeholder="请输入任务名"/>
      </el-form-item>
      <el-form-item label="起始时间" prop="date">
        <el-date-picker
            v-model="milestonesForm.date"
            type="daterange"
            start-placeholder="Start Date"
            end-placeholder="End Date"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
      <el-form-item label="任务描述" prop="introduction">
        <Editor v-model="milestonesForm.introduction"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showMilestonesEdit = false">取消</el-button>
        <el-button type="primary" @click="finish(ruleFormRef)">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup lang="ts">


import {reactive, Ref, ref} from "vue";
import {ElMessageBox, FormInstance, FormRules} from "element-plus";
import {teacherApi} from "../../api/teacherApi";
import { Lesson} from "../../common/gloableData";
import {alerterror, alertsuccess} from "../../common/alert";
import {MilestonesType} from "../../common/typeClass";
import Editor from "../sample/Editor.vue";
//  判断当前是新建还是修改 true:新建
const flag=ref(true)

const showMilestonesEdit=ref(false)


const showCreateDialog = () => {
  flag.value=true
  showMilestonesEdit.value=true
}
const showUpdateDialog = (milestones: MilestonesType) => {
  flag.value=false
  milestonesForm.id=milestones.id
  milestonesForm.name=milestones.name
  milestonesForm.introduction=milestones.introduction
  milestonesForm.date=[milestones.beginTime,milestones.endTime]
  showMilestonesEdit.value=true
}
defineExpose({
  showCreateDialog,
  showUpdateDialog
})

const emits=defineEmits(['refreshMilestonesList'])


const handleClose = (done: () => void) => {
  ElMessageBox.confirm('是否确认取消?')
      .then(() => {
        done()
      })
      .catch(() => {
        // catch error
      })
}

const milestonesForm=reactive({
  id: 0,
  name: '',
  date:['',''],
  introduction: ''
})

const ruleFormRef = ref<FormInstance>()
const rule=reactive<FormRules>(
    {
      name:[
        {required: true, message: '请输入任务名', trigger: 'blur'}
      ],
      date:[
        {required: true, message: '请选择起止日期', trigger: 'blur'}
      ]
    }
)

const finish = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      const milestones:MilestonesType={
        id: milestonesForm.id,
        name: milestonesForm.name,
        beginTime: milestonesForm.date[0],
        endTime: milestonesForm.date[1],
        introduction: milestonesForm.introduction,
        lessonId:Lesson.value.id as number
      }
      if (flag.value){
        teacherApi.addMilestones(milestones).then(res=>{
          emits('refreshMilestonesList')
          alertsuccess("新建阶段任务成功")
          showMilestonesEdit.value=false
        }).catch(e=>{
          alerterror("新建阶段任务失败")
        })
      }
      else {
        teacherApi.updateMilestones(milestones).then(res=>{
          emits('refreshMilestonesList')
          alertsuccess("修改阶段任务成功")
          showMilestonesEdit.value=false
        }).catch(e=>{
          alerterror("修改阶段任务失败")
        })
      }
    }
  })
}

</script>

<style scoped>

</style>