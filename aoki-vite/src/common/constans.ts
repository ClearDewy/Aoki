import {reactive} from "vue";

export const constans={

}

// 账号
export const Account=reactive({
    username:'',
    password:''
})

// 用户信息     用于登录后存储用户信息
export const User=reactive({
    username:'',
    password:'',
    name:'',
    email:"",
    major:"",
    role:-1,
    avatarURL:""
})

// 用户信息
export const UserRegister=reactive({
    username:'',
    password:'',
    name:'',
    email:"",
    major:"",
    role:-1,
    code:""
})

export const EmailLogin=reactive({
    email:"",
    code:""
})