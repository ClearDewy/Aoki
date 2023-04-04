import axios, {AxiosHeaders, AxiosRequestConfig} from "axios";
import Vue from 'vue'
import {storage} from "./storage";
import {constans} from "./constans";
import {alerterror} from "./alert";
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
        if (response.headers["refresh-authorization"]){
            storage.setItem("authorization",response.headers["authorization"])      // 刷新token
        }
        if (response.data.status===200){
            return Promise.resolve(response.data)
        }else {
            return Promise.reject(response.data)
        }
    },
    error => {
        if ("refresh-authorization" in error.headers && error.headers["refresh-authorization"]){
            storage.setItem("authorization",error.headers["authorization"])
        }
        return Promise.reject(error)
    }
)

const eroorHandel = (error:any) => {
    switch (error.status){
        case 402:
            storage.remove("authorization")
            storage.setItem("redirectPath",router.currentRoute.value.fullPath)
            router.push('/')
            break
        default:
            break
    }
}

export const GET=(url:string)=>{
    return axios.get(url).catch(error=>{
        alerterror(error.message)
        eroorHandel(error)
    })
}

export const POST=(url:string, data:object)=>{
    return axios.post(url,objectToFormData(data)).catch(error=>{
        alerterror(error.message)
        eroorHandel(error)
    })
}

export const POST_FILE=(url:string, data:object)=>{
    return axios.post(url,objectToFormData(data),{headers:{ 'content-type': 'multipart/form-data' }}).catch(error=>{
        alerterror(error.message)
        eroorHandel(error)
    })
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



    uploadAvatar:"/api/file/upload-avatar"
}