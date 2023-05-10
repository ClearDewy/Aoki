<template>
  <el-card class="box-card" :body-style="{
    height:'calc(100% - 69px)',
    paddingTop:'0',
    paddingBottom:'0',
}">
    <template #header>
      <div class="card-header">
        <h2 style="margin: auto">{{task.name}}</h2>
        <div><el-button @click="saveTask">保存</el-button>
          <el-button type="primary" @click="submitTask">提交</el-button></div>
      </div>
    </template>
    <div style="width: 100%" v-for="(item,index) in taskQuestionAnswerList">
      <el-card style="width: 100%" :header="'Question-'+(index+1)" :body-style="el_card__body">
        <Editor :editor-id="'Question-'+index" v-model="item.questionContent" :preview-only="true"/>
      </el-card>
      <el-card style="width: 100%" :header="'Answer-'+(index+1)" :body-style="el_card__body">
        <Editor :editor-id="'Answer-'+index" v-model="item.answerContent"/>
      </el-card>
    </div>
  </el-card>
</template>

<script  setup lang="ts">
import {el_card__body} from "../../common/style";
import {storage} from "../../common/storage.js";
import {MemberTaskListType, SubmitAnswerType, TaskQuestionAnswerListType} from "../../common/typeClass";
import {onMounted, ref} from "vue";
import {userApi} from "../../api/userApi.js";
import {alerterror, alertsuccess} from "../../common/alert";
import Editor from "../sample/Editor.vue";
import {Lesson, User} from "../../common/gloableData";

const taskQuestionAnswerList=ref<TaskQuestionAnswerListType[]>([])

const task=ref(storage.getItem('task')||{id:null,name:"作业编辑"})
const getQuestionAnswer=()=>{
  userApi.getQuestionAnswer(task.value.id).then(res=>{
    taskQuestionAnswerList.value=res.data
  })
}

const saveTask=()=>{
  userApi.saveTask({
    taskId: task.value.id,
    answerList: taskQuestionAnswerList.value.map(({questionId,answerContent})=>({
      questionId,
      answerContent
    } as unknown as SubmitAnswerType))
  }).then(res=>{
    alertsuccess("保存成功")
  }).catch(e=>{
    alerterror("保存失败")
  })
}

const submitTask=()=>{
  userApi.submitTask({
    taskId: task.value.id,
    answerList: taskQuestionAnswerList.value.map(({questionId,answerContent})=>({
      questionId,
      answerContent
    } as unknown as SubmitAnswerType))
  }).then(res=>{
    alertsuccess("提交成功")
  }).catch(e=>{
    alerterror("提交失败")
  })
}

onMounted(()=>{
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