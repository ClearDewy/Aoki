<template>
  <el-card :body-style="el_card__body" style="max-height: 100%">
    <template #header>
      <div class="card-header">
        <span>用户管理</span>
        <el-button @click="addUser">添加用户</el-button>
      </div>
    </template>
    <el-table :data="users" style="max-height: 100%;width: 100%">
      <el-table-column label="头像" style="width: 5%">
        <template #default="{row}">
          <el-avatar :src="row.avatarURL" fit="scale-down" :size="'small'"/>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="姓名" :show-overflow-tooltip="true"/>
      <el-table-column prop="username" label="学号" :show-overflow-tooltip="true"/>
      <el-table-column prop="email" label="邮箱" :show-overflow-tooltip="true"/>
      <el-table-column prop="major" label="专业" :show-overflow-tooltip="true"/>
      <el-table-column label="身份" :show-overflow-tooltip="true">
        <template #default="{row}">
          {{ row.role===0?'管理员':row.role===1?'教师':'学生' }}
        </template>
      </el-table-column>
      <el-table-column label="操作" >
        <template #default="{row,$index}">
          <el-button-group>
            <el-button type="primary" :icon="Edit" @click.stop="editUser(row,$index)"/>
            <el-button type="danger" :icon="Delete" @click.stop="deleteUser(row.id,$index)"/>
          </el-button-group>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
  <el-card :body-style="el_card__body" style="max-height: 100%;margin-top: 20px">
    <template #header>
      <span>导入用户</span>
    </template>
    <div v-if="importUserList.length===0">
      <p>1. 用户数据导入仅支持xls、xlsx格式的用户数据。</p>
      <p>2. 共六列数据：姓名、学号、密码、邮箱、专业、身份</p>
      <p>3. 学工号、密码、姓名和身份不能为空，邮箱、专业可选填，否则该行数据可能导入失败。</p>
      <p>4. 专业请确保数据库中有该专业，不然上传结果为空</p>
      <p>5. 身份一栏：0-管理员；1-教师；2-学生</p>
      <p>6. 请导入保存为UTF-8编码的文件，否则中文可能会乱码。</p>
    </div>
    <el-table v-else :data="importUserList" style="max-height: 100%;width: 100%">
      <el-table-column prop="name" label="姓名" :show-overflow-tooltip="true"/>
      <el-table-column prop="username" label="学号" :show-overflow-tooltip="true"/>
      <el-table-column prop="password" label="密码" :show-overflow-tooltip="true"/>
      <el-table-column prop="email" label="邮箱" :show-overflow-tooltip="true"/>
      <el-table-column prop="major" label="专业" :show-overflow-tooltip="true"/>
      <el-table-column label="身份" :show-overflow-tooltip="true">
        <template #default="{row}">
          {{ row.role===0?'管理员':row.role===1?'教师':'学生' }}
        </template>
      </el-table-column>
    </el-table>
    <el-space style="margin-bottom: 10px">
      <el-upload
          action=""
          accept="xlsx,xls"
          :auto-upload="false"
          :on-change="handleChangeUpload"
          :show-file-list="false"
      >
        <el-button type="primary">选择文件</el-button>
      </el-upload>
      <el-button :disabled="importUserList.length===0" type="success" @click="uploadUsers">上传</el-button>
    </el-space>
  </el-card>
  <el-card style="max-height: 100%;margin-top: 20px">
    <template #header>
      <span>专业列表</span>
    </template>
    <el-tag
        v-for="major in majorList"
        :key="major"
        class="tag"
        closable
        :disable-transitions="false"
        @close="handleCloseTag(major)"
    >
      {{ major }}
    </el-tag>
    <el-input
        v-if="inputVisible"
        ref="InputRef"
        v-model="inputValue"
        class="tag-input"
        size="small"
        @keyup.enter="handleInputConfirm"
        @blur="handleInputConfirm"
    />
    <el-button v-else class="button-new-tag ml-1" size="small" @click="showInput">
      + New Tag
    </el-button>
  </el-card>
  <el-dialog
      v-model="showEditUserDialog"
      :title="flag?'添加用户':'修改用户'"
      :before-close="handleClose"
      align-center
      center
      width="513px"
  >
    <el-form :model="userEditForm" label-width="120px" ref="editUserRef" :rules="rule" label-position="left">
      <el-form-item label="学工号" prop="username">
        <el-input v-model="userEditForm.username" placeholder="请输入学工号"/>
      </el-form-item>
      <el-form-item label="密  码" prop="password">
        <el-input v-model="userEditForm.password" placeholder="请输入密码"/>
      </el-form-item>
      <el-form-item label="姓  名" prop="name">
        <el-input v-model="userEditForm.name" placeholder="请输入姓名"/>
      </el-form-item>
      <el-form-item label="邮  箱" prop="email">
        <el-input v-model="userEditForm.email" placeholder="请输入邮箱"/>
      </el-form-item>
      <el-form-item label="专  业" prop="major">
        <el-select v-model="userEditForm.major" placeholder="请选择专业" style="width: 280px;margin-top: 20px" class="select_major">
          <el-option v-for="(x,index) in majorList" :key="index" :value="x"
                     :label="x"/>
        </el-select>
      </el-form-item>
      <el-form-item label="身  份" prop="role">
        <el-select v-model="userEditForm.role" placeholder="请选择身份" style="width: 280px;margin-top: 20px" class="select_major">
          <el-option :key="0" :value="0" :label="'管理员'"/>
          <el-option :key="1" :value="1" :label="'教  师'"/>
          <el-option :key="2" :value="2" :label="'学  生'"/>
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="showEditUserDialog = false">取消</el-button>
        <el-button type="primary" @click="finishEditUser(editUserRef)">
          确认
        </el-button>
      </span>
    </template>
  </el-dialog>

</template>

<script setup lang="ts">
import {el_card__body} from "../../common/style";
import {nextTick, onMounted, reactive, ref} from "vue";
import {userApi} from "../../api/userApi";
import {UserType} from "../../common/typeClass";
import {adminApi} from "../../api/adminApi";
import {alerterror, alertsuccess} from "../../common/alert";
import {Delete, Edit} from "@element-plus/icons-vue";
import {ElInput, ElMessageBox, FormInstance, FormRules, UploadFile, UploadFiles} from "element-plus";
import * as xlsx from "xlsx";

const users=ref<UserType[]>([])
const majorList=ref<string[]>([])   // 专业列表
const showEditUserDialog=ref(false)
const flag=ref(true)
// 修改或者删除用户用于获取下标
const activeRow=ref(null as unknown as number)

const userEditForm=reactive({
  id: null as unknown as number,
  username: '',
  password: '',
  name: '',
  email: '',
  major: '',
  role: null as unknown as number,
  avatarURL: ''
})

// 表单检验
const editUserRef = ref<FormInstance>()
const rule=reactive<FormRules>(
    {
      username:[
        {required: true, message: '请输入学工号', trigger: 'blur'}
      ],
      password:[
        {required: true, message: '请输入密码', trigger: 'blur'}
      ],
      name:[
        {required: true, message: '请输入姓名', trigger: 'blur'}
      ],
      role:[
        {required:true,message:"请选择身份",trigger:"blur"}
      ]
    }
)

const finishEditUser=async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      if (flag.value){
        adminApi.addUser(userEditForm).then(res=>{
          users.value.push(res.data)
          showEditUserDialog.value=false
          alertsuccess("添加成功")
        }).catch(e=>{
          alerterror("添加失败")
        })
      }else{
        adminApi.updateUser(userEditForm).then(res=>{
          users.value[activeRow.value]=userEditForm
          showEditUserDialog.value=false
          alertsuccess("修改成功")
        }).catch(e=>{
          alerterror("修改失败")
        })
      }
    }
  })
}

const deleteUser=(id:number,idx:number)=>{
  adminApi.deleteUser(id).then(res=>{
    users.value.splice(idx,1)
  }).catch(e=>{
    alerterror("修改失败")
  })
}

const handleClose = (done: () => void) => {
  ElMessageBox.confirm('是否确认取消?')
      .then(() => {
        done()
      })
      .catch(() => {
        // catch error
      })
}

const addUser=()=>{
  userEditForm.id=userEditForm.role=null as unknown as number
  userEditForm.username=userEditForm.password=userEditForm.email=userEditForm.name=userEditForm.major=userEditForm.avatarURL=''
  flag.value=true
  showEditUserDialog.value=true
}
const editUser=(row:UserType,idx:number)=>{
  userEditForm.id=row.id
  userEditForm.username=row.username
  userEditForm.password=row.password
  userEditForm.email=row.email
  userEditForm.name=row.name
  userEditForm.role=row.role
  userEditForm.major=row.major
  userEditForm.avatarURL=row.avatarURL
  flag.value=false
  activeRow.value=idx
  showEditUserDialog.value=true
}

const importUserList=ref<UserType[]>([])

const handleChangeUpload = async (uploadFile: UploadFile, uploadFiles: UploadFiles) => {
  // 创建一个文件读取器对象
  const reader = new FileReader();

  // 当文件读取完成时触发的回调函数
  reader.onload = (e) => {
    const data=e.target?.result
    const workbook = xlsx.read(data, { type: 'array' });

    // 假设你要读取的是第一个工作表
    const worksheet = workbook.Sheets[workbook.SheetNames[0]];

    // 通过指定单元格范围来读取第一列的数据
    // const columnData = xlsx.utils.sheet_to_json(worksheet, { range: 'A:A', header: 1 });
    importUserList.value=xlsx.utils.sheet_to_json(worksheet, {header: "A"}).map((row: any) => {
      return {
        id: null as unknown as number,
        username: row.B,
        password: row.C,
        name: row.A,
        email: row.E || '',
        major: row.D || '',
        role: parseInt(row.F),
        avatarURL: ''
      } as UserType
    })
  };

  reader.onerror = (e) => {
    console.error('File reading error:', e.target!.error);
  };

  // 开始读取文件
  reader.readAsArrayBuffer(uploadFile.raw!);
};

const uploadUsers=()=>{
  const errorUser:UserType[]=[]
  importUserList.value.forEach(user=>{
    adminApi.addUser(user).then(res=>{
      users.value.push(res.data)
    }).catch(e=>{
      errorUser.push(user)
    })
  })
  importUserList.value=errorUser
}

onMounted(()=>{
  userApi.getAllMajor().then(res=>{
    majorList.value=res.data
    console.log(majorList.value)
  })

  adminApi.getAllUser().then(res=>{
    users.value=res.data
  }).catch(e=>{
    alerterror("获取用户列表失败")
  })
})

const inputValue = ref('')
const inputVisible = ref(false)
const InputRef = ref<InstanceType<typeof ElInput>>()

const handleCloseTag = (major: string) => {
  adminApi.deleteMajor(major).then(res=>{
    majorList.value.splice(majorList.value.indexOf(major), 1)
  }).catch(e=>{
    alerterror("删除失败")
  })

}

const showInput = () => {
  inputVisible.value = true
  nextTick(() => {
    InputRef.value!.input!.focus()
  })
}

const handleInputConfirm = () => {
  if (inputValue.value) {
    adminApi.addMajor(inputValue.value).then(res=>{
      majorList.value.push(inputValue.value)
      inputVisible.value = false
      inputValue.value = ''
    }).catch(e=>{
      alerterror(e.message)
    })
  }
}

</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.tag{
  margin-left: 0.25rem;
  margin-right: 0.25rem;
}
.tag-input{
  margin-left: 0.25rem;
  width: 160px;
}
</style>