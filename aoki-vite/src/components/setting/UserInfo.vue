<template>
  <div class="section-title">头像设置</div>
  <div class="section-main">

    <el-row>
      <el-col :span="11" style="display: flex;align-items: center;justify-content: center">
        <el-avatar
            :inline="true"
            :size="150"
            style="margin-bottom:15px"
            :src="User.avatarURL"
        ></el-avatar>
      </el-col>
      <el-col :span="2">
        <div class="separator hidden-md-and-down"></div>
        <p></p>
      </el-col>
      <el-col :span="11">
        <el-upload
            v-if="!avatarSrc"

            class="upload-demo"
            drag
            action=""
            :auto-upload="false"
            :show-file-list="false"
            :on-change="handleChangeUpload"
        >
          <el-icon :size="67"><upload-filled /></el-icon>
          <div class="el-upload__text">
            把图像拖放到此处 <em>单击此处</em>
          </div>
          <!--      <template #tip>-->
          <!--        <div class="el-upload__tip">-->
          <!--          jpg/png files with a size less than 500kb-->
          <!--        </div>-->
          <!--      </template>-->
        </el-upload>
        <div v-else style="width: 200px;height: 200px;display: inline-block">
          <VueCropper
              ref="cropper"
              :options="avatarOption"
              :img="avatarSrc"
              :preset-mode="presetMode"
          />
          <el-space>
            <el-tooltip
                class="item"
                effect="dark"
                content="向左旋转90°"
                trigger="hover"
                placement="bottom"
            >
              <el-button
                  @click="rotateLeft"
                  :icon="RefreshLeft"
                  size="small"
              ></el-button>
            </el-tooltip>
            <el-tooltip
                class="item"
                effect="dark"
                content="向右旋转90°"
                trigger="hover"
                placement="bottom"
            >
              <el-button
                  @click="rotateRight"
                  :icon="RefreshRight"
                  size="small"
              ></el-button>
            </el-tooltip>
            <el-tooltip
                class="item"
                effect="dark"
                content="关闭图像截取"
                trigger="hover"
                placement="bottom"
            >
              <el-button
                  @click="reselect"
                  :icon="Refresh"
                  size="small"
              ></el-button>
            </el-tooltip>
            <el-tooltip
                class="item"
                effect="dark"
                trigger="hover"
                content="确定图像截取"
                placement="bottom"
            >
              <el-button
                  @click="finishCrop"
                  :icon="Check"
                  size="small"
                  :loading="loading"
              ></el-button>
            </el-tooltip>
          </el-space>
        </div>
      </el-col>
    </el-row>

  </div>


</template>

<script setup lang="ts">
import {User} from "../../common/constans";
import {UploadFilled} from '@element-plus/icons-vue'
import {reactive, ref} from "vue";
import {UploadFile,UploadFiles} from "element-plus";
import {alerterror, alertsuccess} from "../../common/alert";
import VueCropper,{cropper} from "vue-picture-cropper"
import {RefreshLeft,RefreshRight,Refresh,Check} from "@element-plus/icons-vue";
import {build} from "vite";
import {userApi} from "../../common/userApi";

// 裁剪组件的基础配置option
const avatarSrc=ref("")
const avatarOption={
  viewMode:1,    // 在图片区域内剪裁
  dragMode:'move',
  aspectRatio:1,
  cropBoxResizable: false,
}
const presetMode={
  mode: 'round',
  width: 200,
  height: 200,
}
// 防止重复提交
const loading=ref(false)

const handleChangeUpload=(uploadFile: UploadFile, uploadFiles: UploadFiles)=>{
  if (!uploadFile.raw?.type.startsWith("image/")){
    alerterror("上传必须为图片")
    return false
  }
  if (uploadFile.size&&uploadFile.size>1024*1024*2){
    alerterror("上传头像图片大小不能超过 2MB")
    return false
  }
  avatarSrc.value=URL.createObjectURL(uploadFile.raw)
}

const rotateLeft = () => {
  cropper?.rotate(-90)
}
const rotateRight = () => {
  cropper?.rotate(90)
}
const reselect = () => {
  avatarSrc.value=''
}
const finishCrop = () => {
  loading.value=true
  cropper?.getFile().then((avatar)=>{
    if (!avatar){
      alerterror("图片获取为空")
      return
    }
    userApi.uploadAvatar(avatar).then((res)=>{
      alertsuccess("头像上传成功")
      res && (User.avatarURL=res.data.avatarURL)
      // User.avatarURL="https://scmzu-acm.com/api/public/img/c77bcfed17e846ffa6c1e415550d9d8c.png"
      reselect()
    })
  }).catch(e=>{
    alerterror(e.toString())
    alerterror("图片加载失败")
  })

  loading.value=false
}



</script>


<style scoped>
.section-title {
  font-size: 21px;
  font-weight: 500;
  padding-top: 10px;
  padding-bottom: 20px;
  line-height: 30px;
  text-align: center;
}
.upload-demo:hover{
  color: #409eff;
}

.separator {
  display: block;
  position: absolute;
  top: 0;
  bottom: 0;
  left: 50%;
  border: 1px dashed #eee;
}

</style>