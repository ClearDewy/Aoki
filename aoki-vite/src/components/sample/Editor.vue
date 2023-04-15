<template>
  <MdEditorV3
      ref="editorRef"
      v-model="value"
      :editorId="editorId"
      :toolbars="toolbars"
      showCodeRowNumber
      autoDetectCode
      @onUploadImg="uploadImg"
      :previewOnly="previewOnly"
      @update:modelValue="handleModelValueUpdate"
  >
    <template #defToolbars>
      <NormalToolbar title="上传文件" >
        <template #trigger>
          <el-icon :size="18">
            <el-upload
                action=""
                :auto-upload="false"
                :show-file-list="false"
                :on-change="onUploadFile"
                style="height: 100%;width: 100%;display: flex;align-items: center"
            >
              <UploadFilled />
            </el-upload>
          </el-icon>
        </template>
      </NormalToolbar>
    </template>
  </MdEditorV3>
</template>

<script setup lang="ts">
import MdEditorV3 from "md-editor-v3"
import { ref } from 'vue'
import type { ExposeParam } from 'md-editor-v3';
import {UploadFilled} from "@element-plus/icons-vue";
import {alerterror} from "../../common/alert";
import {FileApi} from "../../api/fileApi";
import {UploadFile} from "element-plus";
const props=defineProps({
  editorId:{
    type:String,
    default:"md-editor"
  },
  modelValue:String,
  previewOnly:{
    type:Boolean,
    default:false
  }
})
const emits=defineEmits(['update:modelValue'])

const value=ref(props.modelValue)

const handleModelValueUpdate = (newValue:any) => {
  emits('update:modelValue',newValue)
}

const NormalToolbar=MdEditorV3.NormalToolbar

const editorRef = ref<ExposeParam>();



const uploadImg = async (files: Array<File>) => {
  const res = await Promise.all(
      files.map((file) => {
        return new Promise((rev, rej) => {
          if (file.size>1024*1024*2){
            alerterror("图片不能大于2M")
            return
          }
          FileApi.uploadImage(file)
              .then((res) => {
                insertImgLink(file.name,res?.data)
              })
              .catch((error) => {
                alerterror("文件名："+file.name+"上传失败！")
              });
        });
      })
  );
};

const onUploadFile = async (uploadFile: UploadFile)=>{
  console.log("触发钩子")
  if (!uploadFile.raw||!uploadFile.size||uploadFile.size>1024*1024*1024){
    alerterror("上传文件不能为空且大小不能超过 1GB")
    return false
  }
  const file=new File([uploadFile.raw], uploadFile.name, {type: uploadFile.raw?.type});
  if (uploadFile.raw?.type.startsWith("image/")){
    await uploadImg([file])
    return false
  }
  FileApi.uploadFile(file).then(res=>{
    insertFileLink(file.name,res?.data,file.type)
  }).catch(e=>{
    alerterror("文件上传失败")
  })
}
const insertImgLink=(name:string,url:string)=>{
  editorRef.value?.insert((selectedText) => {
    return {
      targetValue: '!['+name+']('+url+')',
      select: false,
      deviationStart: 0,
      deviationEnd: 0
    };
  });
}

const insertFileLink=(name:string,url:string,type:string)=>{
  let t:string;
  switch (type) {
    case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
      t="/word.ico"
      break;
    case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
      t="/excel.ico"
      break;
    case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
      t="/pptx.ico"
      break;
    default:
      t="/default.ico"
      break
  }
  editorRef.value?.insert((selectedText) => {
    return {
      targetValue: '[!['+name+']('+t+')]('+url+')',
      select: false,
      deviationStart: 0,
      deviationEnd: 0
    };
  });
}

const toolbars = [
  'bold',
  'underline',
  'italic',
  'strikeThrough',
  '-',
  'title',
  'sub',
  'sup',
  'quote',
  'unorderedList',
  'orderedList',
  'task',
  '-',
  'codeRow',
  'code',
  'link',
  'image',
  0,
  'table',
  'mermaid',
  'katex',
  '-',
  'revoke',
  'next',
  'save',
  '=',
  'prettier',
  'pageFullscreen',
  'fullscreen',
  'preview',
  'htmlPreview',
  'catalog',
];
</script>

<style scoped>
.md-editor-toolbar-item{
  width: 24px;
  height: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>