<template>
  <el-card class="box-card" style="margin-right: 10px" :body-style="el_card__body">
    <template #header>
      <div class="card-header">
        <h2>我的课题</h2>
        <el-button text @click="flag=true;showEditTopicDialog=true">新建课题</el-button>
      </div>
    </template>
    <el-table :data="myTopics" style="height: 100%" @row-click="showTopicMembers">
      <el-table-column prop="name" label="课题名称"/>
      <el-table-column label="难度">
        <template #default="{row}">
          <el-rate v-model="row.difficult" :colors="colors" :disabled="true"/>
        </template>
      </el-table-column>
      <el-table-column prop="ownerName" label="老师"/>
      <el-table-column label="人数">
        <template #default="{row}">
          <span>{{row.number}} / {{row.limit}}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="{row}">
          <el-button type="primary" :icon="Edit" @click.stop="updateTopic(row)" circle />
          <el-button type="danger" :icon="Delete" @click.stop="deleteTopic(row.id)" circle />
        </template>
      </el-table-column>
    </el-table>
  </el-card>
  <el-card class="box-card" style="margin-left: 10px" :body-style="el_card__body">
    <template #header>
      <div class="card-header">
        <span style="height: 32px;display: flex;align-items: center">课题列表</span>
      </div>
    </template>
    <el-table :data="topicList" style="height: 100%">
      <el-table-column prop="name" label="课题名称"/>
      <el-table-column label="难度">
        <template #default="{row}">
          <el-rate v-model="row.difficult" :colors="colors" :disabled="true"/>
        </template>
      </el-table-column>
      <el-table-column prop="ownerName" label="老师"/>
      <el-table-column label="人数">
        <template #default="{row}">
          <span>{{row.number}} / {{row.limit}}</span>
        </template>
      </el-table-column>
      <el-table-column label="人数">
        <template #default="{row}">
          <span>{{row.number}} / {{row.limit}}</span>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

  <el-dialog
      v-model="showEditTopicDialog"
      :title="flag?'新建课题':'修改课题'"
      :before-close="handleClose"
      align-center
      center
      width="750px"
  >
    <el-form ref="ruleFormRef" :model="editTopicForm" label-width="120px" :rules="rule" label-position="top" :inline="true">
      <el-form-item label="课题名称" prop="name">
        <el-input v-model="editTopicForm.name" placeholder="请输入课题名"/>
      </el-form-item>

      <el-form-item label="人数上限" prop="limit">
        <el-input-number v-model="editTopicForm.limit" :min="flag?1:minLimit" />
      </el-form-item>
      <el-form-item label="难度" prop="introduction">
        <el-rate v-model="editTopicForm.difficult" :colors="colors" allow-half/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showEditTopicDialog = false">取消</el-button>
        <el-button type="primary" @click="finish(ruleFormRef)">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
  <el-drawer v-model="showDrawer">
    <template #header="{ titleId, titleClass }">
      <h4 :id="titleId" :class="titleClass">{{drawerTitle}}</h4>
      <el-button @click="initAddTopicMemberDialog">
        添加成员
      </el-button>
    </template>
    <div v-if="Lesson.teamMode">
      <el-collapse v-model="activeCollapseId" >
        <el-collapse-item v-for="team in topicMembers"  :name="team.id">
          <template #title>
            <div style="width: 100%;display: flex;justify-content: space-between;align-items: center">
              <span>{{team.name}}</span>
              <el-button @click="removeTopicMemberTeacher(team.id)" type="danger" circle :icon="Delete"/>
            </div>
          </template>
          <el-table :data="team.memberList" :show-header="false" style="height: 100%">
            <el-table-column prop="avatarURL" label="头像">
              <template #default="scope">
                <el-avatar :src="scope.row.avatarURL" fit="scale-down" :size="'small'"/>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="姓名"/>
            <el-table-column prop="username" label="学号"/>
          </el-table>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div v-else>
      <el-table :data="topicMembers" :show-header="false" style="height: 100%">
        <el-table-column prop="avatarURL" label="头像">
          <template #default="{row}">
            <el-avatar :src="row.avatarURL" fit="scale-down" :size="'small'"/>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="username" label="学号"/>
        <el-table-column>
          <template #default="{row}">
            <el-button @click="removeTopicMemberTeacher(row.id)" type="danger" circle :icon="Delete"/>
          </template>

        </el-table-column>
      </el-table>
    </div>
  </el-drawer>
  <el-dialog
      v-model="showAddMemberDialog"
      title="添加成员"
      :before-close="handleClose"
      align-center
      center
      width="450px"
  >
    <el-table :data="noTopicMembers" style="height: 100%">
      <el-table-column prop="name" label="名称" width="300px"/>
      <el-table-column label="操作">
        <template #default="{row}">
          <el-button :icon="Plus" type="primary" circle @click="addTopicMemberTeacher(row.id)"/>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
</template>

<script setup lang="ts">
import {el_card__body,colors} from "../../common/style"
import {onMounted, reactive, ref} from "vue";
import {
  EditTopicType,
  MilestonesType,
  NoTopicMemberListType,
  TeamListType,
  TeamMemberListType,
  TopicListType
} from "../../common/typeClass";
import {userApi} from "../../api/userApi";
import {Lesson} from "../../common/gloableData";
import {alerterror, alertsuccess, alertwarning} from "../../common/alert";
import {ElMessageBox, FormInstance, FormRules} from "element-plus";
import {teacherApi} from "../../api/teacherApi";
import {Edit,Delete,Plus} from "@element-plus/icons-vue"

const showEditTopicDialog=ref(false)
const flag=ref(true)
const minLimit=ref(1)
const activeTopicId=ref(-1)

const showDrawer=ref(false)
const drawerTitle=ref('')
const activeCollapseId= ref<string[]>([])
const topicMembers=ref<TeamMemberListType[]|TeamListType[]>([])
const noTopicMembers=ref<NoTopicMemberListType[]>([])
const showAddMemberDialog=ref(false)

const handleClose = (done: () => void) => {
  ElMessageBox.confirm('是否确认取消?')
      .then(() => {
        done()
      })
      .catch(() => {
        // catch error
      })
}

const editTopicForm=reactive<EditTopicType>({
  id:-1,
  name: '',
  limit: 1,
  difficult: 0,
  lessonId: Lesson.value.id as number
})
const ruleFormRef = ref<FormInstance>()
const rule=reactive<FormRules>(
    {
      name:[
        {required: true, message: '请输入课题名', trigger: 'blur'}
      ]
    }
)

const finish = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      if (flag.value){
        teacherApi.createTopic(editTopicForm).then(res=>{
          getMyTopics()
          getTopics()
          alertsuccess("创建课题成功")
          showEditTopicDialog.value=false
        }).catch(e=>{
          alerterror("创建课题失败")
        })
      }else{
        teacherApi.updateTopic(editTopicForm).then(res=>{
          getMyTopics()
          getTopics()
          alertsuccess("更改课题成功")
          showEditTopicDialog.value=false
        }).catch(e=>{
          alerterror("更改课题失败")
        })
      }
    }
  })
}

const updateTopic=(row:TopicListType)=>{
  flag.value=false
  minLimit.value=Math.max(1,row.number)
  editTopicForm.name=row.name
  editTopicForm.id=row.id
  editTopicForm.difficult=row.difficult
  editTopicForm.limit=row.limit
  showEditTopicDialog.value=true
}
const deleteTopic=(id:number)=>{
  teacherApi.deleteTopic(id).then(res=>{
    getMyTopics()
    getTopics()
  }).catch(e=>{
    alerterror("删除课程失败")
  })
}

const getTopicMembers = (topicId:number) => {
  teacherApi.getTopicMembers(topicId).then(res=>{
    topicMembers.value=res.data||[]
  }).catch(e=>{
    alerterror("获取课题成员列表失败")
  })
}

const showTopicMembers=(row:TopicListType)=>{
  drawerTitle.value=row.name
  activeTopicId.value=row.id
  getTopicMembers(row.id)
  showDrawer.value=true
}

const getNoTopicMembers=()=>{
  teacherApi.getNoTopicMembers(Lesson.value.id as number).then(res=>{
    noTopicMembers.value=res.data
  }).catch(e=>{
    alerterror("获取无课题成员失败")
  })
}

const initAddTopicMemberDialog = () => {
  getNoTopicMembers()
  showAddMemberDialog.value=true
}

const addTopicMemberTeacher=(memberId:number)=>{
  teacherApi.addTopicMemberTeacher(activeTopicId.value,memberId).then(res=>{
    getNoTopicMembers()
    getTopicMembers(activeTopicId.value)
    getTopics()
    alertsuccess("添加成功")
  }).catch(e=>{
    alerterror("添加失败")
  })
}
const removeTopicMemberTeacher=(memberId:number)=>{
  teacherApi.removeTopicMemberTeacher(activeTopicId.value,memberId).then(res=>{
    getTopicMembers(activeTopicId.value)
    getTopics()
    alertsuccess("移除成员成功")
  }).catch(e=>{
    alerterror("移除成员失败")
  })
}

const topicList=ref<TopicListType[]>([])
const myTopics=ref<TopicListType[]>([])

const getTopics = () => {
  userApi.getTopics(Lesson.value.id as number).then(res=>{
    topicList.value=res.data
  }).catch(e=>{
    alerterror("获取选题列表失败");
  })
}

const getMyTopics=()=>{
  teacherApi.getMyTopics(Lesson.value.id as number).then(res=>{
    myTopics.value=res.data
  }).catch(e=>{
    alerterror("获取我的课题失败")
    alerterror(e.message)
  })
}

onMounted(()=>{
  getMyTopics()
  getTopics()
})

</script>

<style scoped>
.el-form-item{
  width: 200px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.box-card {
  width: 50%;
  height: 100%;
}
</style>