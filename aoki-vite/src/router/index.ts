import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import { defineAsyncComponent } from 'vue'
import {storage} from "../common/storage";

const routes:Array<RouteRecordRaw>=[
    {
        path:'/',
        redirect:"home"
    },
    {
        path:"/login",
        name:"Login",
        component:()=>import("../views/Login/Login.vue"),
        meta:{
            title:"登录",
            requiresAuth:false
        }
    },

    {
        path:"/home",
        name:"Home",
        component:()=>import("../views/Home.vue"),
        meta:{
            title:"首页",
            requiresAuth:false
        }
    }
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
                path:"/login",
            })
        }
    }else{
        next()
    }
})

export default router
