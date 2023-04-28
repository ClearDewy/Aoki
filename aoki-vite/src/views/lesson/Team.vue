<template>
  <div style="display: flex;width: 100%;height: 100%;">
    <div class="box-card" style="padding-right: 1%">
      <el-card class="box-card-half" style="margin-bottom: 2%" :body-style="el_card__body">
        <template #header>
          <div class="card-header">
            <span>我的团队</span>
            <el-button v-if="myTeam" class="button" text @click="showAddTeamMemberDialog=true">添加成员</el-button>
            <el-button v-else class="button" text @click="showCreateTeamDialog=true">创建团队</el-button>
          </div>
        </template>
        <el-table :data="myTeam?.memberList" :show-header="false" style="height: 100%">
          <el-table-column prop="avatarURL" label="头像">
            <template #default="scope">
              <el-avatar :src="scope.row.avatarURL" fit="scale-down" :size="'small'"/>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="姓名"/>
          <el-table-column prop="username" label="学号"/>
          <el-table-column v-if="myTeam?.ownerName===User.name" label="编辑" width="60" >
            <template #default="{row}">
              <el-button type="danger" :icon="Delete" circle @click="removeTeamMember(row.id)"/>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
      <el-card class="box-card-half" style="margin-top: 2%" :body-style="el_card__body">
        <template #header>
          <div class="card-header">
            <span>未加入团队</span>
            <el-button class="button" text @click="refreshNoTeamMembersList">刷新</el-button>
          </div>
        </template>
        <el-table :data="noTeamMembersList" :show-header="false" style="height: 100%">
          <el-table-column prop="avatarURL" label="头像">
            <template #default="scope">
              <el-avatar :src="scope.row.avatarURL" fit="scale-down" :size="'small'"/>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="姓名"/>
          <el-table-column prop="username" label="学号"/>
        </el-table>
      </el-card>
    </div>
    <el-card class="box-card" :body-style="el_card__body">
      <template #header>
        <div class="card-header">
          <span>团队列表</span>
        </div>
      </template>
      <el-collapse v-model="activeTeamId" @change="handleChange">
        <el-collapse-item v-for="team in teamList" :title="team.name" :name="team.id">
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
    </el-card>
  </div>
  <el-dialog v-model="showCreateTeamDialog" title="创建团队" width="30%" center align-center>
    <el-input v-model="createTeamName" placeholder="请输入团队名" clearable />
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showCreateTeamDialog = false">Cancel</el-button>
        <el-button type="primary" @click="createTeam" :disabled="createTeamName.length===0">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="showAddTeamMemberDialog" title="添加成员" width="30%" center align-center style="min-width: 500px;max-height: 80%">
    <el-table :data="noTeamMembersList" :show-header="false" style="height: 100%">
      <el-table-column prop="avatarURL" label="头像">
        <template #default="scope">
          <el-avatar :src="scope.row.avatarURL" fit="scale-down" :size="'small'"/>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="姓名"/>
      <el-table-column prop="username" label="学号"/>
      <el-table-column label="编辑" width="60" >
        <template #default="scope">
          <el-button :icon="Plus" type="primary" circle @click="addTeamMember(scope.row.id)"/>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
</template>


<script setup lang="ts">
import {ref} from "vue";
import {TeamListType, TeamMemberListType} from "../../common/typeClass";
import {Lesson, User} from "../../common/gloableData";
import {userApi} from "../../api/userApi";
import {alerterror} from "../../common/alert";
import {Plus,Delete} from "@element-plus/icons-vue"
import {el_card__body} from "../../common/style";

const activeTeamId = ref<string[]>([])
const handleChange = (val: string[]) => {
  console.log(val)
}

const showCreateTeamDialog=ref(false)
const createTeamName=ref('')
const showAddTeamMemberDialog=ref(false)

const myTeam=ref<TeamListType>()
const teamList=ref<TeamListType[]>([])
const noTeamMembersList=ref<TeamMemberListType[]>([])

const refreshTeamList=()=>{
  userApi.getTeams(Lesson.value.id as number).then(res=>{
    teamList.value=res.data
    for (const item of teamList.value){
      for(const mem of item.memberList){
        if (mem.username===User.value.username){
          myTeam.value=item
          return
        }
      }
    }
  }).catch(e=>{
    alerterror(e)
    alerterror("获取团队列表失败")
  })
}

const refreshNoTeamMembersList=()=>{
  userApi.getNoTeamMembers(Lesson.value.id as number).then(res=>{
    noTeamMembersList.value=res.data
  }).catch(e=>{
    alerterror("获取未加入团队成员列表失败")
  })
}

const createTeam=()=>{
  userApi.createTeam(createTeamName.value,Lesson.value.id as number).then(res=>{
    showCreateTeamDialog.value=false
    refreshTeamList()

  }).catch(e=>{
    alerterror("创建团队失败")
  })
}

const addTeamMember=(id:number)=>{
  if (myTeam.value)
    userApi.addTeamMember(myTeam.value.id,id).then(res=>{
      refreshTeamList()
      refreshNoTeamMembersList()
    }).catch(e=>{
      alerterror("添加用户失败")
    })
}

const removeTeamMember=(id:number)=>{
  userApi.removeTeamMember(myTeam.value?.id as number,id).then(res=>{
    refreshTeamList()
    refreshNoTeamMembersList()
  }).catch(e=>{
    alerterror("删除团队成员失败")
  })
}




refreshTeamList()
refreshNoTeamMembersList()
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

.box-card {
  width: 50%;
  height: 100%;
}
.box-card-half{
  width: 100%;
  height: 49%;
}
</style>