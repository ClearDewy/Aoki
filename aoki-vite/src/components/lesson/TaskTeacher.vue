<template>
  <el-card class="box-card" :body-style="el_card__body">
    <template #header>
      <div class="card-header">
        <span style="margin: auto">我的作业</span>
        <el-button type="primary" @click="getMyTopics()||(flag=true)||(showEditTaskDialog=true)">新建</el-button>
      </div>
    </template>
    <el-table :data="taskList" style="height: 100%">
      <el-table-column prop="name" label="名称"/>
      <el-table-column prop="topicName" label="所属课题"/>
      <el-table-column prop="beginTime" label="开始时间"/>
      <el-table-column prop="endTime" label="截至时间"/>
      <el-table-column label="发布" prop="publish"/>
      <el-table-column label="操作">
        <template #default="{row}">
          <el-tooltip
              :content="row.publish?'取消发布':'发布作业'"
              placement="bottom"
          >
            <el-button :type="row.publish?'info':'success'" :icon="row.publish?SemiSelect:Select" circle @click="toggleTaskPublish(row.id)"/>
          </el-tooltip>
          <el-tooltip
              content="编辑"
              placement="bottom"
          >
            <el-button type="primary" :icon="Edit" circle @click="updateTask(row)"/>
          </el-tooltip>
          <el-popconfirm title="Are you sure to delete this?">
            <template #reference>
              <el-button type="danger" :icon="Delete" circle @click="deleteTask(row.id)"/>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
  </el-card>



  <el-dialog
      v-model="showEditTaskDialog"
      :title="flag?'新建作业':'修改作业'"
      :before-close="handleClose"
      align-center
      center
      width="513px"
  >
    <el-form ref="ruleFormRef" :model="editTaskForm" label-width="120px" :rules="rule" label-position="top" :inline="true">
      <el-form-item label="作业名称" prop="name">
        <el-input v-model="editTaskForm.name" placeholder="请输入作业名"/>
      </el-form-item>
      <el-form-item v-if="flag" label="所属课题" prop="topicId">
        <el-select v-model="editTaskForm.topicId" placeholder="请选择所属课题" style="width: 200px">
          <el-option v-for="(x,index) in myTopics" :key="index" :value="x.id"
                     :label="x.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始与结束时间" prop="time">
        <el-date-picker
            v-model="editTaskForm.time"
            type="datetimerange"
            start-placeholder="Start Date"
            end-placeholder="End Date"
            value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showEditTaskDialog = false">取消</el-button>
        <el-button type="primary" @click="finish(ruleFormRef)">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import {el_card__body} from "../../common/style";
import {reactive, ref} from "vue";
import {ElMessageBox, FormInstance, FormRules} from "element-plus";
import {teacherApi} from "../../api/teacherApi";
import {alerterror, alertsuccess} from "../../common/alert";
import {OwnerTaskListVo, TaskType, TopicListType} from "../../common/typeClass";
import {userApi} from "../../api/userApi";
import {Lesson} from "../../common/gloableData";
import {Edit,Select,SemiSelect,Delete} from "@element-plus/icons-vue";

const showEditTaskDialog=ref(false)
const flag=ref(true)

const editTaskForm=ref({
  id:0,
  name:'',
  publish:false,
  topicId:null as number | null,
  time:['','']
})

const handleClose = (done: () => void) => {
  ElMessageBox.confirm('是否确认取消?')
      .then(() => {
        done()
      })
      .catch(() => {
        // catch error
      })
}

const ruleFormRef = ref<FormInstance>()
const rule=reactive<FormRules>(
    {
      name:[
        {required: true, message: '请输入课题名', trigger: 'blur'}
      ],
      topicId:[
        {required: true, message: '请选择所属课程', trigger: 'blur'}
      ],
      time:[
        {required: true, message: '请选择作业开始与结束时间', trigger: 'blur'}
      ],
    }
)

const finish = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      const task:TaskType= {
        id: editTaskForm.value.id,
        name: editTaskForm.value.name,
        publish: editTaskForm.value.publish,
        topicId: editTaskForm.value.topicId as unknown as number,
        beginTime: editTaskForm.value.time[0],
        endTime: editTaskForm.value.time[1]
      }
      if (flag.value){
        teacherApi.createTask(task).then(res=>{
          alertsuccess("创建成功")
        }).catch(e=>{
          alerterror("创建失败")
        })
      }else{
        teacherApi.updateTask(task).then(res=>{
          alertsuccess("更新成功")
        }).catch(e=>{
          alerterror("更新失败")
        })
      }
      getOwnerTasks()
      showEditTaskDialog.value=false
    }
  })
}

const myTopics=ref<TopicListType[]>([])

const getMyTopics=()=>{
  teacherApi.getMyTopics(Lesson.value.id as number).then(res=>{
    myTopics.value=res.data
  }).catch(e=>{
    alerterror("获取我的课题失败")
    alerterror(e.message)
  })
}

const taskList=ref<OwnerTaskListVo[]>([])

const getOwnerTasks=()=>{
  teacherApi.getOwnerTasks(Lesson.value.id as number).then(res=>{
    taskList.value=res.data
  }).catch(e=>{
    alerterror("获取作业列表失败")
  })
}

const updateTask=(row:OwnerTaskListVo)=>{
  flag.value=false
  editTaskForm.value.id=row.id
  editTaskForm.value.name=row.name
  editTaskForm.value.topicId=row.topicId
  editTaskForm.value.time=[row.beginTime,row.endTime]
  showEditTaskDialog.value=true
}

const deleteTask=(id:number)=>{
  teacherApi.deleteTask(id).then(res=>{
    getOwnerTasks()
    alertsuccess("删除成功")
  }).catch(e=>{
    alerterror("删除失败")
  })
}

const toggleTaskPublish=(id:number)=>{
  teacherApi.toggleTaskPublish(id).then(res=>{
    getOwnerTasks()
  }).catch(e=>{
    alerterror("错误")
  })
}

getOwnerTasks()
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: center;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 100%;
  height: 100%;
}
</style>