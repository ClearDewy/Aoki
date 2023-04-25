<template>
  <el-card class="box-card" :body-style="{
    height:'calc(100% - 69px)',
    paddingTop:'0',
    paddingBottom:'0'
}">
    <template #header>
      <div class="card-header">
        <h2 style="margin: auto">题目编辑</h2>
        <el-button type="primary" @click="editTaskQuestions">保存</el-button>
      </div>
    </template>
    <div style="max-height: 100%;width: 100%;overflow: auto">
      <el-collapse v-model="activeNames">
        <el-collapse-item v-for="(x,index) in questionList" :name="index">
          <template #title>
            <div style="height: 100%;width: 100%;display: flex;justify-content: space-between;align-items: center">
              <h3 style="margin-right: auto">Question-{{index+1}}</h3>
              <el-button :icon="Delete" circle style="margin-right: 1%" @click="questionList.splice(index,1)"/>
            </div>
          </template>
          <Editor v-model="x.content" :editor-id="'questionEdit'+index"/>

        </el-collapse-item>
      </el-collapse>
      <el-button class="mt-4" style="width: 100%" @click="questionList.push({...question})">Add Question</el-button>
    </div>
  </el-card>

</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {QuestionType} from "../../common/typeClass";
import router from "../../router";
import Editor from "../sample/Editor.vue";
import {Delete} from "@element-plus/icons-vue"
import {teacherApi} from "../../api/teacherApi";
import {alerterror, alertsuccess, alertwarning} from "../../common/alert";
import {useRouter} from "vue-router";
import {storage} from "../../common/storage";

const taskId:number= router.currentRoute.value.query.taskId as unknown as number

const question:QuestionType={
  id:0,
  taskId: taskId,
  content:''
}
const activeNames = ref(['1'])
const questionList=ref<QuestionType[]>([])

const getTaskQuestion=()=>{
  teacherApi.getTaskQuestion(taskId).then(res=>{
    questionList.value=res.data
    if (questionList.value.length===0)
      questionList.value.push({...question})
  }).catch(e=>{
    alerterror("获取题目列表失败")
  })
}
const editTaskQuestions=()=>{
  teacherApi.editTaskQuestions(questionList.value).then(res=>{
    alertsuccess("保存成功")
    router.push(storage.getItem("redirectPath"))
    storage.remove("redirectPath")
  }).catch(e=>{
    alerterror("保存失败")
  })
}

getTaskQuestion()
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