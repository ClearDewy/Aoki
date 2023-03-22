import axios ,{InternalAxiosRequestConfig}from "axios";
import Vue from 'vue'
import {storage} from "./storage";
import {constans} from "./constans";
import {alerterror} from "./alert";
import formData from "form-data";

axios.defaults.baseURL="/spring/"

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
        if (error.headers["refresh-authorization"]){
            storage.setItem("authorization",error.headers["authorization"])
        }
        return Promise.reject(error)
    }
)

export const GET=(url:string)=>{
    return axios.get(url).catch(error=>{
        alerterror(error.message)
    })
}

export const POST=(url:string, data:object)=>{
    return axios.post(url,objectToFormData(data)).catch(error=>{
        alerterror(error.message)
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