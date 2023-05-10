<template>
  <el-card class="box-card" :body-style="el_card__body">
    <template #header>
      <div class="card-header">
        <h2 style="margin: auto">我的作业</h2>
      </div>
    </template>
    <el-table :data="taskList" style="height: 100%" @row-click="taskEdit">
      <el-table-column prop="name" label="名称"/>
      <el-table-column prop="beginTime" label="开始时间"/>
      <el-table-column prop="endTime" label="截至时间"/>
      <el-table-column prop="submitted" label="是否提交"/>
    </el-table>
  </el-card>
</template>

<script setup lang="ts">
import {el_card__body} from "../../common/style";
import {onMounted, ref} from "vue";
import {MemberTaskListType, TaskType, TopicType} from "../../common/typeClass";
import {userApi} from "../../api/userApi";
import {Lesson} from "../../common/gloableData";
import {alerterror} from "../../common/alert";
import {storage} from "../../common/storage";
import router from "../../router";
const taskList=ref<MemberTaskListType[]>([])


const getMyTaskList=()=>{
  const topic:TopicType=storage.getItem("topic")
  if (topic?.id){
    userApi.getMemberTasks(topic.id).then(res=>{
      taskList.value=res.data
    }).catch(e=>{
      alerterror("获取作业列表失败")
    })
  }
}

const taskEdit=(task:MemberTaskListType)=>{
  storage.setItem('task',{id:task.id,name:task.name})
  router.push('task-edit')
}
onMounted(()=>{
  getMyTaskList()
})

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