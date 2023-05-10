<template>
  <el-row justify="space-between">
    <el-col :span="12">
      <el-card :body-style="el_card__body" >
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
      <el-card :body-style="el_card__body" >
        <template #header>
          <div class="card-header">
            <span>老师联系方式</span>
              <el-button  v-if="Lesson.ownerId===User.id" class="button" text @click="showAddMemberDialog=true">添加老师</el-button>
          </div>
        </template>
        <el-table
            :data="teachersData"
            style="width: 100%;height: 100%">
          <el-table-column prop="name" label="姓名" style="width: 50%" :show-overflow-tooltip="true"/>
          <el-table-column prop="email" label="邮箱" style="width: 50%" :show-overflow-tooltip="true"/>
          <el-table-column label="操作" v-if="Lesson.ownerId===User.id">
            <template #default="{row}">
              <el-button type="danger" v-if="row.id!==User.id" :icon="Delete" circle @click="removeUsers(row.id)"/>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
  </el-row>
  <el-row justify="space-between">
    <el-col :span="12">
      <el-card :body-style="el_card__body" >
        <template #header>
          <div class="card-header">
            <span>阶段任务</span>
            <el-space v-if="Lesson.ownerId===User.id">
              <el-button class="button" text @click="milestonesEditRef.showCreateDialog()">添加任务</el-button>
              <el-button type="danger" :icon="Delete" circle :disabled="milestonesMultipleSelection.length===0"  @click="deleteMilestones(milestonesMultipleSelection)"/></el-space>
          </div>
        </template>
        <el-table
            :data="milestonesData"
            style="width: 100%;height: 100%"
            @selection-change="milestonesTableSelectionChange"
            @row-click="showMilestonesIntroduction"
        >
          <el-table-column v-if="Lesson.ownerName===User.name" type="selection" width="55" />
          <el-table-column prop="name" label="任务名" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column prop="beginTime" label="开始时间" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column prop="endTime" label="结束时间" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column label="状态" style="width: 25%" >
            <template #default="scope">
              <span :style="{
                color: new Date(scope.row.beginTime) > new Date() ? 'gray' :
                       new Date(scope.row.endTime) < new Date() ? 'red' : 'green'
              }">
                {{ new Date(scope.row.beginTime) > new Date() ? '未开始' :
                  new Date(scope.row.endTime) < new Date() ? '已结束' : '进行中'
                }}
              </span>
            </template>
          </el-table-column>
          <el-table-column v-if="Lesson.ownerId===User.id" label="编辑" width="60" >
            <template #default="scope">
              <el-button :icon="Edit" circle @click="milestonesEditRef.showUpdateDialog(scope.row)"/>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card :body-style="el_card__body" >
        <template #header>
          <div class="card-header">
            <span>学生列表</span>
            <el-button  v-if="Lesson.ownerId===User.id" class="button" text @click="showAddMemberDialog=true">添加学生</el-button>
          </div>
        </template>
        <el-table
            :data="studentsData"
            style="width: 100%;height: 100%"
        >
          <el-table-column prop="name" label="姓名" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column prop="username" label="学号" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column prop="email" label="邮箱" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column prop="major" label="专业" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column label="操作" v-if="Lesson.ownerId===User.id">
            <template #default="{row}">
              <el-button type="danger" v-if="row.id!==User.id" :icon="Delete" circle @click="removeUsers(row.id)"/>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-col>
  </el-row>

  <el-dialog v-model="showAddMemberDialog" title="添加用户" width="50%" center align-center>
    <el-form
        ref="addLessonMemberFormRef"
        label-width="120px"
        class="demo-ruleForm"
        status-icon
    >
      <el-form-item label="学工号" label-width="20%">
        <el-input v-model="tempUsername" >
          <template #append><el-button :icon="Plus" @click="appendUsernames([tempUsername])"/></template>
        </el-input>
      </el-form-item>
      <el-form-item  label-width="20%">
        <el-upload
            action=""
            accept="xlsx,xls"
            :auto-upload="false"
            :on-change="handleChangeUpload"
            :show-file-list="false"
        >
          <el-button type="primary">上传</el-button>
          <template #tip>
            <div class="el-upload__tip">
              Excel表格第一列为学工号
            </div>
          </template>
        </el-upload>
      </el-form-item>
      <el-form-item label="用户名列表" label-width="20%">
        <el-table :data="addLessonMemberList" :show-header="addLessonMemberList.length>0" style="max-height: 200px">
          <el-table-column prop="name" label="姓名" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column prop="username" label="学号" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column prop="email" label="邮箱" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column prop="major" label="专业" style="width: 25%" :show-overflow-tooltip="true"/>
          <el-table-column label="操作">
            <template #default="{row,$index}">
              <el-button :icon="Delete" circle @click="addLessonMemberList.splice($index,1)"/>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showAddMemberDialog = false">Cancel</el-button>
        <el-button type="primary" @click="addLessonMember" :loading="addLessonMemberLoading">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
  <MilestonesEdit ref="milestonesEditRef" @refreshMilestonesList="refreshMilestonesList"/>
  <el-drawer
      v-model="showDrawer"
      :title="drawerTitle"
  >
    <Editor :preview-only="true" v-model="milestonesIntroduction"/>
  </el-drawer>
</template>

<script setup lang="ts">
import {Lesson,User} from "../../common/gloableData";
import {onMounted, reactive, ref} from "vue";
import {userApi} from "../../api/userApi";
import {alerterror, alertinfo, alertsuccess} from "../../common/alert";
import {MilestonesType, UserListType, UserType} from "../../common/typeClass";
import {ElTable, FormInstance, FormRules, UploadFile, UploadFiles} from "element-plus";
import {teacherApi} from "../../api/teacherApi";
import Editor from "../../components/sample/Editor.vue";
import {Delete} from '@element-plus/icons-vue'
import MilestonesEdit from "../../components/lesson/MilestonesEdit.vue";
import {Edit} from "@element-plus/icons-vue"
import {el_card__body} from "../../common/style";
import * as xlsx from 'xlsx'
import {Plus} from "@element-plus/icons-vue";

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

const milestonesData=ref<MilestonesType[]>([])
const refreshMilestonesList=()=>{
  userApi.getMilestones(Lesson.value.id as number).then(res=>{
    milestonesData.value=res?.data
  }).catch(e=>{
    alerterror("获取阶段任务失败")
  })
}



const showAddMemberDialog=ref(false)

const tempUsername=ref('')
const addLessonMemberList=ref<UserListType[]>([])

const appendUsernames=(usernames:string[])=>{
  if (usernames.length===0)return
  teacherApi.getUsersByUsernames(usernames).then(res=>{
    addLessonMemberList.value=addLessonMemberList.value.concat(res.data)
    tempUsername.value=''
  }).catch(e=>{
    alerterror("获取用户失败")
  })
}

const handleChangeUpload = async (uploadFile: UploadFile, uploadFiles: UploadFiles) => {
  // 创建一个文件读取器对象
  const reader = new FileReader();

  // 当文件读取完成时触发的回调函数
  reader.onload = (e) => {
    const data=e.target?.result
    const workbook = xlsx.read(data, { type: 'array' });

    // 假设你要读取的是第一个工作表
    const worksheet = workbook.Sheets[workbook.SheetNames[0]];

    // 通过指定单元格范围来读取第一列的数据
    // const columnData = xlsx.utils.sheet_to_json(worksheet, { range: 'A:A', header: 1 });
    const columnData = xlsx.utils.sheet_to_json(worksheet,{header:"A"}).map((row:any)=>row.A)
    appendUsernames(columnData)
  };

  reader.onerror = (e) => {
    console.error('File reading error:', e.target!.error);
  };

  // 开始读取文件
  reader.readAsArrayBuffer(uploadFile.raw!);
};

const addLessonMemberLoading=ref(false)
const addLessonMember = async () => {
  addLessonMemberLoading.value=true
  teacherApi.addLessonMembers(Lesson.value.id as number,addLessonMemberList.value.map(user=>user.id)).then(res=>{
    addLessonMemberList.value.forEach(user=>{
      switch (user.role){
        case 1:
          teachersData.value.push(user)
          break;
        case 2:
          studentsData.value.push(user)
          break;
        default:
          break
      }
    })
    showAddMemberDialog.value=false
    addLessonMemberLoading.value=false
    alertsuccess("添加用户成功")
  }).catch(e=>{
    alerterror("添加用户失败")
  })
}

const milestonesMultipleSelection=ref<MilestonesType[]>([])

const milestonesTableSelectionChange = (val:MilestonesType[]) => {
  milestonesMultipleSelection.value=val
}

const removeUsers = (id:number) => {
  teacherApi.removeLessonMembers(Lesson.value.id as number,id).then(res=>{
    refreshMemberList()
  }).catch(e=>{
    alerterror("删除用户失败:"+e.message)
  })
}

const deleteMilestones=(milestonesMultipleSelection:MilestonesType[])=>{
  teacherApi.deleteMilestones(Lesson.value.id as number,milestonesMultipleSelection.map(ul=>ul.id)).then(res=>{
    refreshMilestonesList()
  }).catch(e=>{
    alerterror("删除阶段任务失败:"+e.message)
  })
}

// 绑定到上方绑定子组件
const milestonesEditRef=ref(null)

const showDrawer=ref(false)
const drawerTitle=ref('')
const milestonesIntroduction=ref('')

const showMilestonesIntroduction = (row:MilestonesType) => {
  drawerTitle.value=row.name
  milestonesIntroduction.value=row.introduction
  showDrawer.value=true
}

onMounted(()=>{
  refreshMemberList()
  refreshMilestonesList()
})

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