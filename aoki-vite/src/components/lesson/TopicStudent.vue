<template>
  <el-card v-if="Lesson.topicMode||myTopic" class="box-card" :body-style="el_card__body">
    <template #header>
      <div class="card-header">
        <h2 style="margin: auto">我的选题</h2>
        <el-button v-if="!Lesson.topicMode" type="danger" @click="removeTopicMember(myTopic?.id)">退选</el-button>
      </div>
    </template>
    <div style="display: flex;justify-content: center;align-items: center;height: 100%;width: 100%;flex-direction: column">
      <h1 style="font-size:100px;margin: 0">{{myTopic?.name}}</h1>
      <el-rate :size="'large'" v-model="myTopic.difficult" :colors="colors" :disabled="true"/>
      <h3>{{myTopic?.ownerName}}</h3>
    </div>
  </el-card>
  <el-card v-else class="box-card" :body-style="el_card__body">
    <template #header>
      <div class="card-header">
        <span>选题列表</span>
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
      <el-table-column label="操作">
        <template #default="{row}">
          <el-button type="primary" @click="addTopicMember(row.id)" plain
                     :disabled="row.number>=row.limit||disabled"
          >选择</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>


</template>

<script setup lang="ts">
import {el_card__body,colors} from "../../common/style";
import {ref} from "vue";
import {TopicListType, TopicTimeType} from "../../common/typeClass";
import {Lesson} from "../../common/gloableData";
import {userApi} from "../../api/userApi";
import {alerterror} from "../../common/alert";
import {storage} from "../../common/storage";

const myTopic=ref<TopicListType>()
const topicList=ref<TopicListType[]>([])
const beginTime=ref(new Date(0))
const endTime=ref(new Date(0))

const getTopics = () => {
  userApi.getTopics(Lesson.value.id as number).then(res=>{
    topicList.value=res.data
  }).catch(e=>{
    alerterror("获取选题列表失败");
  })
}

const getMyTopic=()=>{
  userApi.getMyTopic(Lesson.value.id as number).then(res=>{
    storage.setItem("topic",res.data)
    myTopic.value=res.data
  }).catch(e=>{
    alerterror(e.message)
  })
}

const getTopicTime = () => {
  userApi.getTopicTime(Lesson.value.id as number).then(res=>{
    beginTime.value=new Date(res.data.beginTime)
    endTime.value=new Date(res.data.endTime)
  }).catch(e=>{
    console.log(e)
    alerterror("获取选课时间失败");
  })
}

const addTopicMember = (id:number) => {
  userApi.addTopicMember(id).then(res=>{
    getMyTopic()
  }).catch(e=>{
    alerterror("选题失败:"+e.message)
  })
}
const removeTopicMember = (id:number) => {
  userApi.removeTopicMember(id).then(res=>{
    myTopic.value=undefined
    getTopics()
  }).catch(e=>{
    alerterror("退选失败:"+e.message)
  })
}



getMyTopic()
if (!myTopic.value){
  if (Lesson.value.topicMode){
    myTopic.value={
      id:0,
      name:'暂无课题',
      ownerName:'未知',
      difficult:0,
      number:0,
      limit:0
    }
  }else{
    getTopicTime()
    getTopics()
  }
}

const disabled=ref(!(new Date() >= beginTime.value && new Date() <= endTime.value))

// 定时检查当前时间是否在范围内
setInterval(() => {
  const now = new Date();
  disabled.value = !(now >= beginTime.value && now <= endTime.value);
}, 1000); // 每秒执行一次

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