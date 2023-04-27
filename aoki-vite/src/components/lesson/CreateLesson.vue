<template>
  <el-dialog
      v-model="showCreateLesson"
      title="新建课程"
      :before-close="handleClose"
      align-center
      center
      width="1005px"
  >
    <el-form ref="ruleFormRef" :model="createLessonForm" label-width="120px" :rules="rule" label-position="top" :inline="true">
      <el-form-item label="课程名" prop="name">
        <el-input v-model="createLessonForm.name" placeholder="请输入课程名"/>
      </el-form-item>
      <el-form-item label="团队功能" prop="teamMode">
        <el-switch
            v-model="createLessonForm.teamMode"
            class="mb-2"
            active-text="开启"
            inactive-text="关闭"
        />
      </el-form-item>

      <el-form-item label="选题模式">
        <el-switch
            v-model="createLessonForm.topicMode"
            class="mb-2"
            active-text="自建课题"
            inactive-text="选择课题"
        />
      </el-form-item>
      <el-form-item label="课程描述">
        <Editor v-model="createLessonForm.introduction"/>
      </el-form-item>
      <el-form-item label="课程头像">
        <el-switch
            v-model="selectLessonAvatar"
            class="mb-2"
            active-text="自选头像"
            inactive-text="默认头像"
            style="width: 100%"
        />
        <el-row v-if="selectLessonAvatar" style="width: 100%">
          <el-col :span="11" style="display: flex;align-items: center;justify-content: center">
            <el-image :src="createLessonForm.avatarURL"
                      style="height: 130px;width: 240px"/>
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
                accept="image/*"
            >
              <el-icon :size="67"><upload-filled /></el-icon>
              <div class="el-upload__text">
                把图像拖放到此处 <em>单击此处</em>
              </div>
            </el-upload>
            <div v-else style="width: 288px;height: 156px;display: inline-block">
              <VueCropper
                  ref="cropper"
                  :options="avatarOption"
                  :img="avatarSrc"
                  :type="'image/png'"
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
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showCreateLesson = false">取消</el-button>
        <el-button type="primary" @click="finish(ruleFormRef)">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import {reactive, ref} from "vue";
import {userApi} from "../../api/userApi";
import {ElMessageBox, FormInstance, FormRules, UploadFile, UploadFiles} from "element-plus";
import {CreateLessonType, LessonType} from "../../common/typeClass";
import {teacherApi} from "../../api/teacherApi";
import {alerterror, alertsuccess} from "../../common/alert";
import {RefreshLeft,RefreshRight,Refresh,Check,UploadFilled,Picture} from "@element-plus/icons-vue";
import VueCropper,{cropper} from "vue-picture-cropper"
import {emitter} from "../../common/gloableData";
import {FileApi} from "../../api/fileApi";
import Editor from "../sample/Editor.vue";

const showDialog=()=>{
  showCreateLesson.value=true
}
// 暴露给父组件
defineExpose({
  showDialog
})

const showCreateLesson=ref(false)

const selectLessonAvatar=ref(false)
const handleClose = (done: () => void) => {
  ElMessageBox.confirm('是否确认取消?')
      .then(() => {
        done()
      })
      .catch(() => {
        // catch error
      })
}
const createLessonForm = reactive<CreateLessonType>({
  name: '',
  introduction: '',
  teamMode: false,
  topicMode: false,
  avatarURL: '',
})

const ruleFormRef = ref<FormInstance>()
const rule=reactive<FormRules>(
    {
      name:[
        {required: true, message: '请输入课程名', trigger: 'blur'}
      ],
      teamMode:[
        {required: true, message: '请选择团队模式', trigger: 'blur'}
      ]
    }
)

const finish = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      teacherApi.createLesson(createLessonForm).then(res=>{
        emitter.emit("refreshLessonList")
        alertsuccess("课程创建成功")
        showCreateLesson.value = false
      }).catch(e=>{
        alerterror(e.toString())
      })
    }
  })
}




// 裁剪组件的基础配置option
const avatarSrc=ref("")
const avatarOption={
  viewMode:1,    // 在图片区域内剪裁
  dragMode:'move',
  width: 240,
  height: 130,
  cropBoxResizable: false,
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
  cropper?.getBlob().then((avatarBlob)=>{
    if (!avatarBlob){
      alerterror("图片获取为空")
      return
    }
    console.log(avatarBlob.type)
    let file = new window.File(
        [avatarBlob],
        'LessonAvatar.png'
    );
    FileApi.uploadAvatar(file).then((res)=>{
      createLessonForm.avatarURL=res.data
      alertsuccess("头像上传成功")
      reselect()
    }).catch(e=>{
      alerterror("头像上传失败")
    })
  }).catch(e=>{
    alerterror(e.toString())
    alerterror("图片加载失败")
  })
  loading.value=false
}
</script>

<style scoped>
.el-dropdown:hover{
  color: #409eff;
}
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>