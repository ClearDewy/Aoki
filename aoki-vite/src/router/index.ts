import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import {storage} from "../common/storage";

export const routerPath={
    Login:"/login",
    Home:"/home",
    Register:"/register",
    EmailLogin:"/emaillogin",
    Certification:"/certification",
    Setting:"/setting",
}

const routes:Array<RouteRecordRaw>=[
    {
        path:'/',
        redirect:"home"
    },
    {
        path:routerPath.Login,
        name:"Login",
        component:()=>import("../views/Login/Login.vue"),
        meta:{
            title:"登录",
            requiresAuth:false,     // 是否需要登录
            showHeader:false,
            showAside:false
        }
    },

    {
        path:routerPath.Home,
        name:"Home",
        component:()=>import("../views/Home.vue"),
        meta:{
            title:"首页",
            requiresAuth:true,
            showHeader:true,
            showAside:false
        }
    },
    {
        path:routerPath.Register,
        name:"Register",
        component:()=>import("../views/Register/Register.vue"),
        meta:{
            title:"注册",
            requiresAuth:false,
            showHeader:false,
            showAside:false
        }
    },
    {
        path:routerPath.EmailLogin,
        name:"EmailLogin",
        component:()=>import("../views/Login/EmailLogin.vue"),
        meta:{
            title:"邮箱登录",
            requiresAuth:false,
            showHeader:false,
            showAside:false
        }
    },
    {
        path:routerPath.Certification,
        name:"Certification",
        component:()=>import("../views/Register/Certification.vue"),
        meta:{
            title:"完善信息",
            requiresAuth:false,
            showHeader:false,
            showAside:false
        }
    },
    {
        path:routerPath.Setting,
        name:"Setting",
        component:()=>import("../views/user/Setting.vue"),
        meta:{
            title:"设置",
            requiresAuth:true,
            showHeader:true,
            showAside:false
        }
    },
]

const router=createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to,from,next)=>{
    if (to.meta.requiresAuth){
        const token=storage.getItem("authorization")
        if (token){
            next()
        }else{
            storage.setItem("redirectPath",to.fullPath)
            next({
                path:routerPath.Login,
            })
        }
    }else{
        next()
    }
})


export default router
