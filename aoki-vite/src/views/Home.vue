<template>
  <div class="container">
    <el-card v-for="lesson in lessonList" :body-style="{ padding: '0px'}" style="width: 240px;height: 206px" shadow="hover" @click="enterLesson(lesson)">
      <el-image :src="lesson.avatarURL" fit="fill"
                style="height: 130px;width: 240px"/>
      <div style="padding: 14px">
        <span class="inline-span" style="display: block; text-align: left;font-size: var(--el-font-size-extra-large);width: 100%">{{lesson.name}}</span>
        <span class="inline-span" style="display: block; text-align: left;font-size: var(--el-font-size-small);width: 100%">{{lesson.ownerName}}</span>
      </div>
    </el-card>
  </div>
  <el-empty v-if="lessonList.length===0"/>
  <div v-else class="demo-pagination-block" style="padding:1%;float: right">
    <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="pageSizeList"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="lessonTotalNum"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import {userApi} from "../api/userApi";
import {alerterror} from "../common/alert";
import {LessonListType} from "../common/typeClass";
import {emitter, Lesson, User} from "../common/gloableData";
import router, {routerPath} from "../router";
import {storage} from "../common/storage";
const currentPage = ref(1)
const pageSizeList=ref([20, 40, 60])
const pageSize = ref(pageSizeList.value[0])

const small = ref(false)
const disabled = ref(false)
const background = ref(false)
const handleSizeChange = (val: number) => {
  refreshPageLessonList()
}
const handleCurrentChange = (val: number) => {
  refreshPageLessonList()
}

const lessonList=ref<LessonListType[]>([])
const currentLessonList=ref<LessonListType[]>([])
const lessonTotalNum=ref(0)

const refreshPageLessonList=()=>{
  currentLessonList.value=lessonList.value.slice((currentPage.value-1)*pageSize.value,Math.min(lessonList.value.length,currentPage.value*pageSize.value))
  console.log(currentLessonList.value.length)
}

const getLessonList = () => {
  userApi.getLessonList().then(res=>{
    res&& (lessonList.value=res.data.reverse())
    lessonTotalNum.value=lessonList.value.length
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
      storage.remove('topic')
      storage.remove('task')
      router.push(routerPath.Lesson)
    }
  }).catch(e=>{
    alerterror("进入课程失败")
  })
}

</script>

<style scoped>
.inline-span {
  display: inline-block;
  width: 100%;
  overflow: hidden;
  white-space: nowrap;  /* 避免换行 */
  text-overflow: ellipsis;
}

.container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}
</style>