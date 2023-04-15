<template>
  <el-row justify="space-between">
    <el-col :span="12">
      <el-card :body-style="{ padding: '0px'}" >
        <template #header>
          <div class="card-header">
            <span>{{Lesson.name}}</span>
            <span style="font-size: var(--el-font-size-small);">{{Lesson.ownerName}}</span>
          </div>
        </template>
        <Editor v-model="Lesson.introduction" :preview-only="true"  style="padding-left: 8px;padding-right: 8px"/>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card :body-style="{ padding: '0px'}" >
        <template #header>
          <div class="card-header">
            <span>老师联系方式</span>
            <div v-if="Lesson.ownerName===User.name" ><el-button class="button" text @click="showAddMemberDialog=true">添加老师</el-button>
              <el-button type="danger" :icon="Delete" circle :disabled="teacherMultipleSelection.length===0"  @click="removeUsers(teacherMultipleSelection)"/></div>
          </div>
        </template>
        <el-table
            :data="teachersData"
            @selection-change="teacherTableSelectionChange"
            style="width: 100%;height: 100%">
          <el-table-column v-if="Lesson.ownerName===User.name" type="selection" width="55" />
          <el-table-column prop="name" label="姓名" style="width: 50%" />
          <el-table-column prop="email" label="邮箱" style="width: 50%" />
        </el-table>
      </el-card>
    </el-col>
  </el-row>
  <el-row justify="space-between">
    <el-col :span="12">
      <el-card :body-style="{ padding: '0px'}" >
        <template #header>
          <div class="card-header">
            <span>阶段任务</span>
            <el-button class="button" v-if="Lesson.ownerName===User.name" text>添加任务</el-button>
          </div>
        </template>
        <div v-for="o in 4" :key="o" class="text item">{{ 'List item ' + o }}</div>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card :body-style="{ padding: '0px'}" >
        <template #header>
          <div class="card-header">
            <span>学生列表</span>
            <div v-if="Lesson.ownerName===User.name"><el-button class="button" text @click="showAddMemberDialog=true">添加学生</el-button>
              <el-button type="danger" :icon="Delete" circle :disabled="studentMultipleSelection.length===0" @click="removeUsers(studentMultipleSelection)"/></div>
          </div>
        </template>
        <el-table
            :data="studentsData"
            style="width: 100%;height: 100%"
            @selection-change="studentTableSelectionChange">
          <el-table-column v-if="Lesson.ownerName===User.name" type="selection" width="55" />
          <el-table-column prop="name" label="姓名" style="width: 25%" />
          <el-table-column prop="username" label="学号" style="width: 25%" />
          <el-table-column prop="email" label="邮箱" style="width: 25%" />
          <el-table-column prop="major" label="专业" style="width: 25%" />
        </el-table>
      </el-card>
    </el-col>
  </el-row>

  <el-dialog v-model="showAddMemberDialog" title="添加用户" width="30%" center align-center>
    <el-form
        ref="addLessonMemberFormRef"
        :model="addLessonMemberForm"
        :rules="addLessonMemberRules"
        label-width="120px"
        class="demo-ruleForm"
        status-icon
    >
      <el-form-item label="学工号" prop="username" label-width="20%">
        <el-input v-model="addLessonMemberForm.username" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showAddMemberDialog = false">Cancel</el-button>
        <el-button type="primary" @click="addLessonMember(addLessonMemberFormRef)" :loading="addLessonMemberLoading">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import {Lesson,User} from "../../common/gloableData";
import {reactive, ref} from "vue";
import {userApi} from "../../api/userApi";
import {alerterror, alertinfo, alertsuccess} from "../../common/alert";
import {UserListType, UserType} from "../../common/typeClass";
import {ElTable, FormInstance, FormRules} from "element-plus";
import {teacherApi} from "../../api/teacherApi";
import Editor from "../../components/sample/Editor.vue";
import {Delete} from '@element-plus/icons-vue'

const teachersData=ref<UserListType[]>([])
const studentsData=ref<UserListType[]>([])

const refreshMemberList = () => {
  userApi.getLessonMember(Lesson.value.id as number).then(res=>{
    teachersData.value.length=studentsData.value.length=0
    res?.data.forEach((u:UserListType) =>{
      switch (u.role){
        case 1:
          teachersData.value.push(u)
          break;
        case 2:
          studentsData.value.push(u)
          break;
        default:
          break
      }
    })
  }).catch(e=>{
    alerterror("获取课程成员失败")
  })
}
refreshMemberList()

const showAddMemberDialog=ref(false)

const addLessonMemberForm=reactive({
  username:""
})

const addLessonMemberFormRef=ref<FormInstance>()
const addLessonMemberRules=reactive<FormRules>({
  username:[
    { required: true, message: '请填写学工号', trigger: 'blur' },
    { min: 3, max: 12, message: '请填写正确的学工号', trigger: 'blur' },
  ],
})

const addLessonMemberLoading=ref(false)
const addLessonMember = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      addLessonMemberLoading.value=true
      teacherApi.addLessonMember(addLessonMemberForm.username,Lesson.value.id as number).then(res=>{
        switch (res?.data?.role){
          case 1:
            teachersData.value.push(res?.data)
            break;
          case 2:
            studentsData.value.push(res?.data)
            break;
          default:
            break
      }
      showAddMemberDialog.value=false
      addLessonMemberLoading.value=false
        alertsuccess("添加用户成功")
      }).catch(e=>{
        alerterror("添加用户失败")
      })
    } else {
      console.log('error submit!', fields)
    }
  })
}

const teacherMultipleSelection = ref<UserListType[]>([])
const studentMultipleSelection = ref<UserListType[]>([])

const teacherTableSelectionChange=(val:UserListType[])=>{
  teacherMultipleSelection.value=val
}
const studentTableSelectionChange=(val:UserListType[])=>{
  studentMultipleSelection.value=val
}


const removeUsers = (userMultipleSelection:UserListType[]) => {
  teacherApi.removeLessonMembers(Lesson.value.id as number,userMultipleSelection.map(ul=>ul.id)).then(res=>{
    refreshMemberList()
  }).catch(e=>{
    alerterror("删除用户失败:"+e.message)
  })
}
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.el-card {
  width: 95%;
  height: 95%;
}
.el-row {
  width: 100%;
  height: 50%;
}
</style>