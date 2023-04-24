<template>
  <div style="display: flex;width: 100%;height: 100%;">
          <TaskStudent v-if="User.role===2"/>
          <TaskTeacher v-else/>
  </div>
</template>

<script setup lang="ts">
import {User} from "../../common/gloableData"
import TaskTeacher from "../../components/lesson/TaskTeacher.vue";
import TaskStudent from "../../components/lesson/TaskStudent.vue";


</script>

<style scoped>

</style>