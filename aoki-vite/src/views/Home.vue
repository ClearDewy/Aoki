<template>
  <el-row type="flex" style="flex-wrap: wrap;flex-direction: row;padding-left: 4%;padding-right: 4%" gutter="20">
    <el-col
        v-for="lesson in currentLessonList"
        :key="lesson.id"
        :span="4"
    >
      <el-card :body-style="{ padding: '0px'}" shadow="hover" @click="enterLesson(lesson)">
        <el-image :src="lesson.avatarURL"
                  style="height: 130px;width: 240px"/>
        <div style="padding: 14px">
          <span style="display: block; text-align: left;font-size: var(--el-font-size-extra-large);width: 100%">{{lesson.name}}</span>
          <span style="display: block; text-align: left;font-size: var(--el-font-size-small);width: 100%">{{lesson.ownerName}}</span>
        </div>
      </el-card>
    </el-col>

  </el-row>
  <div class="demo-pagination-block" style="padding:1%;float: right">
    <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="pageSizeList"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="lessonTotleNum"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

</template>

<script setup lang="ts">
import {ref} from "vue";
import {userApi} from "../api/userApi";
import {alerterror} from "../common/alert";
import {LessonListType} from "../common/typeClass";
import {emitter, Lesson, User} from "../common/gloableData";
import router, {routerPath} from "../router";
import {storage} from "../common/storage";
const currentPage = ref(1)
const pageSizeList=[24, 48, 96]
const pageSize = ref(pageSizeList[0])
const small = ref(false)
const disabled = ref(false)
const background = ref(false)
const handleSizeChange = (val: number) => {
  refreshPageLessonList()
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  refreshPageLessonList()
  console.log(`current page: ${val}`)
}

const lessonList=ref<LessonListType[]>([])
const currentLessonList=ref<LessonListType[]>([])
const lessonTotleNum=ref(0)

const refreshPageLessonList=()=>{
  currentLessonList.value=lessonList.value.slice((currentPage.value-1)*pageSize.value,Math.min(lessonList.value.length,currentPage.value*pageSize.value))
  console.log(currentLessonList.value.length)
}

const getLessonList = () => {
  userApi.getLessonList().then(res=>{
    res&& (lessonList.value=res.data.reverse())
    lessonTotleNum.value=lessonList.value.length
    console.log(lessonList.value)
    refreshPageLessonList()
  }).catch(e=>{
    alerterror("获取课程列表失败")
  })
}
getLessonList()
// 添加课程后重新获取课程列表
emitter.on("refreshLessonList",getLessonList)

const enterLesson=(les:LessonListType)=>{
  les.id&& userApi.getLesson(les.id).then(res=>{
    if (res){
      Lesson.value=res.data
      Lesson.value.ownerName=les.ownerName
      storage.setItem("Lesson",Lesson.value)
      router.push(routerPath.Lesson)
    }
  }).catch(e=>{
    alerterror("进入课程失败")
  })
}

</script>

<style scoped>

</style>