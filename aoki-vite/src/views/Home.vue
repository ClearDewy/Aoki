<template>
  <el-row type="flex" style="flex-wrap: wrap;flex-direction: row;padding-left: 4%;padding-right: 4%" gutter="20">
    <el-col
        v-for="(o, index) in 10"
        :key="o"
        :span="4"
    >
      <el-card :body-style="{ padding: '0px'}" style="margin: 10%">
<!--        <img-->
<!--            src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"-->
<!--            class="image"-->
<!--        />-->
        <div style="padding: 14px">
          <span>Yummy hamburger</span>
          <div class="bottom">
            <time class="time">{{ currentDate }}</time>
            <el-button text class="button">Operating</el-button>
          </div>
        </div>
      </el-card>
    </el-col>

  </el-row>
  <div class="demo-pagination-block" style="padding:1%;float: right">
    <el-pagination
        v-model:current-page="currentPage4"
        v-model:page-size="pageSize4"
        :page-sizes="[100, 200, 300, 400]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total="400"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

</template>

<script setup lang="ts">
import {ref} from "vue";
const currentPage4 = ref(4)
const pageSize4 = ref(100)
const small = ref(false)
const disabled = ref(false)
const background = ref(false)
const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
}

</script>

<style scoped>

</style>