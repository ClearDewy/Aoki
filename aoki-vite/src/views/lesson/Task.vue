<template>
  <div style="display: flex;width: 100%;height: 100%;">
          <TopicStudent v-if="User.role===2"/>
          <TopicTeacher v-else/>
  </div>
</template>

<script setup lang="ts">
import {User} from "../../common/gloableData"
import TopicTeacher from "../../components/lesson/TaskTeacher.vue"
import TopicStudent from "../../components/lesson/TopicStudent.vue"


</script>

<style scoped>

</style>