<template>
  <el-drawer
      v-model="showTaskMember"
      direction="rtl"
      :before-close="handleClose"
      :title="task.name"
  >
    <el-collapse v-model="activeModId">
      <el-collapse-item title="已完成">
        <div v-if="Lesson.teamMode">
          <el-collapse v-model="activeCollapseId" >
            <el-collapse-item v-for="team in taskMembers"  :name="team.id">
              <template #title>
                <div style="width: 100%;display: flex;justify-content: space-between;align-items: center">
                  <span>{{team.name}}</span>
                  <el-button type="info" @click.stop="scoreTask(team.id)" circle :icon="Search"/>
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
          <el-table :data="taskMembers" :show-header="false" style="height: 100%">
            <el-table-column prop="avatarURL" label="头像">
              <template #default="{row}">
                <el-avatar :src="row.avatarURL" fit="scale-down" :size="'small'"/>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="姓名"/>
            <el-table-column prop="username" label="学号"/>
            <el-table-column>
              <template #default="{row}">
                <el-button type="info" circle @click="scoreTask(row.username)" :icon="Search"/>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-collapse-item>
      <el-collapse-item title="未完成">
        <div v-if="Lesson.teamMode">
          <el-collapse v-model="noActiveCollapseId" >
            <el-collapse-item v-for="team in noTaskMembers"  :name="team.id">
              <template #title>
                <div style="width: 100%;display: flex;justify-content: space-between;align-items: center">
                  <span>{{team.name}}</span>
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
          <el-table :data="noTaskMembers" :show-header="false" style="height: 100%">
            <el-table-column prop="avatarURL" label="头像">
              <template #default="{row}">
                <el-avatar :src="row.avatarURL" fit="scale-down" :size="'small'"/>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="姓名"/>
            <el-table-column prop="username" label="学号"/>
          </el-table>
        </div>
      </el-collapse-item>
    </el-collapse>
  </el-drawer>
</template>

<script setup lang="ts">

import {ref} from "vue";
import {ElMessageBox} from "element-plus";
import {OwnerTaskListType, TeamListType, TeamMemberListType} from "../../common/typeClass";
import {Lesson} from "../../common/gloableData";

const taskMembers=ref<TeamMemberListType[]|TeamListType[]>([])
const noTaskMembers=ref<TeamMemberListType[]|TeamListType[]>([])
const activeCollapseId= ref<string[]>([])
const noActiveCollapseId= ref<string[]>([])
const activeModId=ref<string[]>([])
import {Search} from '@element-plus/icons-vue'
import {teacherApi} from "../../api/teacherApi";
import {alerterror} from "../../common/alert";
import {storage} from "../../common/storage";
import router from "../../router";

const showTaskMember=ref(false)
const task=ref({
  id:0,
  name:'作业名称'
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

const showTaskMemberDrawer=(row:OwnerTaskListType)=>{
  task.value={id:row.id,name:row.name}
  getTaskMember()
  getNoTaskMember()
  showTaskMember.value=true
}

const getTaskMember=()=>{
  teacherApi.getTaskMember(task.value.id).then(res=>{
    taskMembers.value=res.data
  }).catch(e=>{
    alerterror("获取已完成作业的成员失败")
  })
}
const getNoTaskMember=()=>{
  teacherApi.getNoTaskMember(task.value.id).then(res=>{
    noTaskMembers.value=res.data
  }).catch(e=>{
    alerterror("获取未完成作业的成员失败")
  })
}

const scoreTask=(key:number|string)=>{
  router.push({path:'task-score',query:{id:task.value.id,name:task.value.name,key:key}})
}

// 暴露给父组件
defineExpose({
  showTaskMemberDrawer
})

</script>

<style scoped>

</style>