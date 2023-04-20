import axios, {AxiosHeaders, AxiosRequestConfig} from "axios";
import Vue from 'vue'
import {storage} from "../common/storage";
import {constans} from "../common/constans";
import {alerterror} from "../common/alert";
import formData from "form-data";
import router from "../router";

// 请求超时时间
axios.defaults.timeout = 90000;

// 请求拦截器
axios.interceptors.request.use(
    config=>{
        const token:string|null=storage.getItem("authorization")
        token && (config.headers.Authorization = token)         // token不为空则携带
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

// 响应拦截器
axios.interceptors.response.use(
    response =>{
        if (response?.headers?.["refresh-authorization"]){
            storage.setItem("authorization",response.headers["authorization"])      // 刷新token
        }
        if (response.data?.status===200){
            return response.data
        }else {
            return Promise.reject(response.data)
        }
    },
    error => {
        if (error?.headers?.["refresh-authorization"]){
            storage.setItem("authorization",error.headers["authorization"])      // 刷新token
        }
        errorHandel(error)
        return Promise.reject(error)
    }
)

const errorHandel = (error:any) => {
    switch (error.status){
        case 402:
        case 403:
            console.log(error.status)
            storage.clear()
            storage.setItem("redirectPath",router.currentRoute.value.fullPath)
            router.replace('/login')
            break
        default:
            break
    }
}

export const GET=(url:string)=>{
    return axios.get(url)
}

export const POST=(url:string, data:object)=>{
    return axios.post(url,objectToFormData(data))
}

export const POST_FILE=(url:string, data:object)=>{
    return axios.post(url,objectToFormData(data),{headers:{ 'content-type': 'multipart/form-data' }})
}

// 将对象转化为formData用于传参
export const objectToFormData=(params:object)=>{
    const data=new formData()
    Object.keys(params).forEach(key=>{
        data.append(key,params[key as keyof typeof params])
    })
    return data;
}

export const apiUrl={
    login:"/api/login",
    logout:"/api/logout",
    getVerifyCode:"/api/get-verify-code",
    verifyCode:"/api/verify-code",
    register:"/api/register",
    getAllMajor:"/api/get-all-major",
    emailLogin:"/api/email-login",



    uploadAvatar:"/api/file/upload-avatar",
    uploadImage:"/api/file/upload-image",
    uploadFile:"/api/file/upload-file",

    updatePassword:"/api/update-password",
    updateEmail:"/api//update-email",
    updateAvatar:"/api//update-avatar",


    createLesson:"/api/create-lesson",
    getLessonList:"/api/get-lesson-list",
    getLesson:"/api/get-lesson",
    getLessonMember:"/api/get-lesson-member",
    addLessonMember:"/api/add-lesson-member",
    removeLessonMembers:"/api/remove-lesson-member",

    addMilestones:"/api/add-milestones",
    getMilestones:"/api//get-milestones",
    deleteMilestones:"/api/delete-milestones",
    updateMilestones:"/api//update-milestones",

    createTeam:"/api/create-team",
    getTeams:"/api/get-teams",
    getNoTeamMembers:"/api/get-no-team-members",
    addTeamMember:"/api/add-team-member",
    removeTeamMember:"/api/remove-team-member"
}