<template>
  <div style="display: flex;width: 100%;height: 100%;">
    <TopicStudent v-if="User.role===2"/>
    <TopicTeacher v-else/>
  </div>
</template>

<script setup lang="ts">
import {el_card__body} from "../../common/style";
import TopicStudent from "../../components/lesson/TopicStudent.vue";
import {User} from "../../common/gloableData"
import TopicTeacher from "../../components/lesson/TopicTeacher.vue";

</script>

<style scoped>


</style>