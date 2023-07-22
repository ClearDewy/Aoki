<template>
  <div style="display: flex;width: 100%;height: 100%;">

    <div style="width: 30%;height: 100%;margin-right: 10px">
      <el-card style="height: 20%;width: 100%;margin-bottom: 10px" :body-style="el_card__body">
        <template #header>
          <div class="card-header">
            <span>选课时间设置</span>
            <el-button class="button" :disabled="Lesson.topicMode" @click="updateTopicTime" type="primary">保存</el-button>
          </div>
        </template>
        <el-date-picker
            v-model="topicTime"
            type="datetimerange"
            start-placeholder="Start Date"
            end-placeholder="End Date"
            value-format="YYYY-MM-DD HH:mm:ss"
            :disabled="Lesson.topicMode"
        />
      </el-card>
      <el-card style="height: 80%;width: 100%;margin-top: 10px" :body-style="el_card__body">
        <template #header>
          <div class="card-header" style="justify-content: center">
            <span>删除课程</span>
          </div>
        </template>
        <div style="height: 100%;width: 100%;display: flex;justify-content: center;align-items: center">
          <div style="width: 100%">
            <el-input v-model="User.email" :disabled="true" style="width: 100%;height: 40px;margin-top: 20px" placeholder="请输入邮箱">
              <template #prefix>
                <el-icon style="font-size: 18px;"><Message /></el-icon>
              </template>
            </el-input>

            <div style="position: relative;">
              <el-input v-model="emailCode" style="width: 100%;height: 40px;margin-top: 20px" placeholder="请输入验证码">
                <template #prefix>
                  <el-icon style="font-size: 18px;"><Loading /></el-icon>
                </template>
                <template #append>
                  <el-countdown v-if="startGrab.value" format="ss" :value="endTime" value-style="color:#409eff;font-size: 14px" @finish="resetGetVerifyCode"/>
                  <el-button v-else type="text" @click="getVerifyCode">获取验证码
                  </el-button>
                </template>
              </el-input>

            </div>
            <el-button type="danger" @click="deleteLesson" style="width: 80%;height: 40px;margin: 10%" :disabled="emailCode.length!==6">删除</el-button>
          </div>
        </div>
      </el-card>
    </div>
    <el-card style="width: 70%;height: 100%;margin-left: 10px" :body-style="el_card__body">
      <template #header>
        <div class="card-header">
          <span>课程信息设置</span>
          <el-button class="button" @click="updateLesson" type="primary">保存</el-button>
        </div>
      </template>
      <el-form ref="ruleFormRef" :model="editLessonForm" label-width="120px" :rules="rule" label-position="top" :inline="true">
        <el-form-item label="课程名" prop="name">
          <el-input v-model="editLessonForm.name" placeholder="请输入课程名"/>
        </el-form-item>
        <el-form-item label="课程头像">

          <el-row style="width: 100%">
            <el-col :span="11" style="display: flex;align-items: center;justify-content: center">
              <el-image :src="editLessonForm.avatarURL"
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
        <el-form-item label="课程描述">
          <Editor v-model="editLessonForm.introduction"/>
        </el-form-item>

      </el-form>

    </el-card>
  </div>
</template>

<script setup lang="ts">
import {el_card__body} from "../../common/style";
import {userApi} from "../../api/userApi";
import {Lesson,User} from "../../common/gloableData";
import {CreateLessonType, EditLessonType, TopicTimeType} from "../../common/typeClass";
import {alerterror, alertsuccess} from "../../common/alert";
import {onMounted, reactive, ref} from "vue";
import {ElMessageBox, FormInstance, FormRules, UploadFile, UploadFiles} from "element-plus";
import VueCropper,{cropper} from "vue-picture-cropper";
import {FileApi} from "../../api/fileApi";
import {Check,Refresh,RefreshRight,RefreshLeft,UploadFilled,Message,Loading} from "@element-plus/icons-vue";
import Editor from "../../components/sample/Editor.vue"
import {teacherApi} from "../../api/teacherApi";
import router, {routerPath} from "../../router";
import {storage} from "../../common/storage";


const topicTime=ref(['',''])
const emailCode=ref('')
const startGrab=ref(false)
const endTime=ref(Date.now())

const getTopicTime = () => {
  userApi.getTopicTime(Lesson.value.id as number).then(res=>{
    topicTime.value=[res.data.beginTime,res.data.endTime]
  }).catch(e=>{
    alerterror("获取选课时间失败");
  })
}

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
const editLessonForm = reactive<EditLessonType>({
  id:Lesson.value.id as number,
  name: Lesson.value.name,
  introduction: Lesson.value.introduction,
  avatarURL: Lesson.value.avatarURL,
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

// 裁剪组件的基础配置option
const avatarSrc=ref("")
const avatarOption={
  viewMode:1,    // 在图片区域内剪裁
  dragMode:'move',
  width: 240,
  height: 130,
  cropBoxResizable: false,
  aspectRatio: 24 / 13,
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
      editLessonForm.avatarURL=res.data
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

const updateLesson=()=>{
  teacherApi.updateLesson(editLessonForm).then(res=>{
    refreshCurrentLesson()
    alertsuccess("保存成功")
  }).catch(e=>{
    alerterror("保存失败")
  })
}

const updateTopicTime=()=>{
  teacherApi.updateTopicTime({
    beginTime: topicTime.value[0],
    endTime: topicTime.value[1],
    lessonId: Lesson.value.id as number
  }).then(res=>{
    alertsuccess("保存成功")
  }).catch(e=>{
    alerterror("保存失败")
  })
}
const getVerifyCode=()=>{
  userApi.getVerifyCode(User.value.email).then(res=>{
    endTime.value=Date.now()+1000*60
    startGrab.value=true
  })
}
const resetGetVerifyCode=()=>{
  startGrab.value=false
}
const deleteLesson=()=>{
  teacherApi.deleteLesson(Lesson.value.id as number,emailCode.value).then(res=>{
    alertsuccess("删除成功")
    router.replace("/")
  }).catch(e=>{
    alerterror("删除失败")
  })
}

const refreshCurrentLesson=()=>{
  userApi.getLesson(Lesson.value.id as number).then(res=>{
    if (res){
      Lesson.value=res.data
      Lesson.value.ownerName=storage.getItem("Lesson").ownerName
      storage.setItem("Lesson",Lesson.value)
      router.push(routerPath.Lesson)
    }
  }).catch(e=>{
    alerterror("刷新课程失败")
  })
}

onMounted(()=>{
  if (!Lesson.value.topicMode)getTopicTime()
})

</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

</style>