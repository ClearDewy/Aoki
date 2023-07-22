<template>
  <el-card class="box-card" :body-style="{
    height:'calc(100% - 69px)',
    paddingTop:'0',
    paddingBottom:'0',
}">
    <template #header>
      <div class="card-header">
        <h2 style="margin: auto">{{task.name || '作业评分'}}</h2>
      </div>
    </template>
    <div style="width: 100%" v-for="(item,index) in taskQuestionAnswerList">
      <el-card style="width: 100%" :header="'Question-'+(index+1)" :body-style="el_card__body">
        <Editor :editor-id="'Question-'+index" v-model="item.questionContent" :preview-only="true"/>
      </el-card>
      <el-card style="width: 100%" :header="'Answer-'+(index+1)" :body-style="el_card__body">
        <Editor :editor-id="'Answer-'+index" v-model="item.answerContent" :preview-only="true"/>
      </el-card>
    </div>
  </el-card>
  <el-drawer v-model="showDrawer">
    <template #header="{ close, titleId, titleClass }">
      <div style="display: flex;justify-content: space-between;align-items: center">
        <h4>评分</h4>
        <el-button type="primary" @click="submitScoreRecord">提交</el-button>
      </div>

    </template>
    <el-table :data="scoreRecordList" style="height: 100%">
      <el-table-column prop="name" label="名称"/>
      <el-table-column prop="limit" label="分数上限"/>
      <el-table-column label="分数">
        <template #default="{row}">
          <el-input-number v-model="row.score" :precision="1" :step="0.1" :max="row.limit" />
        </template>
      </el-table-column>
    </el-table>
  </el-drawer>

  <el-button :icon="ArrowLeft" circle style="position: fixed;top: 50%;right: -10px" @click="showDrawer=true"/>

</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {storage} from "../../common/storage";
import {ScoreEditType, ScoreRecordType, ScoreRuleType, TaskQuestionAnswerListType} from "../../common/typeClass";
import {userApi} from "../../api/userApi";
import Editor from "../sample/Editor.vue"
import {el_card__body} from "../../common/style";
import {Lesson} from "../../common/gloableData";
import {teacherApi} from "../../api/teacherApi";
import {alerterror, alertsuccess} from "../../common/alert";
import router from "../../router";
import {ArrowLeft} from '@element-plus/icons-vue'
const showDrawer=ref(true)

const taskQuestionAnswerList=ref<TaskQuestionAnswerListType[]>([])

type TaskType={id:number,name:string,key:number|string}
const task=ref<TaskType>(router.currentRoute.value.query as unknown as TaskType)

const getQuestionAnswer=()=>{
  teacherApi.getTaskQuestionAnswer(task.value.id,task.value.key as number).then(res=>{
    taskQuestionAnswerList.value=res.data
  }).catch(e=>{
    alerterror("获取作业详情失败")
  })
}

const scoreRecordList=ref<ScoreEditType[]>([])
const getScoreRule=()=>{
  teacherApi.getScoreRecord(task.value.id,task.value.key as number).then(res=>{
    scoreRecordList.value=res.data
  }).catch(e=>{
    alerterror("获取分数记录失败")
  })
}

const submitScoreRecord=()=>{
  const scoreRecord:ScoreRecordType= {
    taskId: task.value.id,
    memberId: task.value.key as number,
    scoreList: scoreRecordList.value.map(({scoreRuleId,score})=>({scoreRuleId,score}))
  }
  teacherApi.submitScoreRecord(scoreRecord).then(res=>{
    alertsuccess("保存成功")
    router.back()
  }).catch(e=>{
    alerterror("保存失败")
  })
}
onMounted(()=>{
  getScoreRule()
  getQuestionAnswer()
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