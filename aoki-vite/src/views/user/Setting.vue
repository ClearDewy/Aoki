<template>
  <el-card class="box-card">
    <el-collapse v-model="activeName" accordion>
      <el-collapse-item name="Account">
        <template #title>
          <el-icon><Setting /></el-icon>
          <i> 账户设置</i>
        </template>
        <Account/>
      </el-collapse-item>
      <el-collapse-item name="UserInfo">
        <template #title>
          <el-icon><Setting /></el-icon>
          <i> 资料设置</i>
        </template>
        <UserInfo/>
      </el-collapse-item>
    </el-collapse>
  </el-card>
</template>
<script setup lang="ts">
import Account from "../../components/setting/Account.vue"
import UserInfo from "../../components/setting/UserInfo.vue"
</script>
<style scoped>
@media screen and (min-width: 1200px) {
  .box-card {
    margin-left: 10%;
    margin-right: 10%;
  }
}
/deep/ .el-collapse-item__header {
  border-radius: 4px;
  font-size: 18px;
  color: #409eff;
}
</style>
