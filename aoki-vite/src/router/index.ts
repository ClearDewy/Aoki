import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import {storage} from "../common/storage";

export const routerPath={
    Login:"/login",
    Home:"/home",
    Register:"/register",
    Setting:"/setting",

    Lesson:"/lesson",
    Admin:"/admin"
}

const routes:Array<RouteRecordRaw>=[
    {
        path:'/',
        redirect:routerPath.Home
    },
    {
        path:routerPath.Login,
        name:"登录",
        component:()=>import("../views/common/Login.vue"),
        meta:{
            requiresAuth:false,     // 是否需要登录
            showHeader:false,
        }
    },

    {
        path:routerPath.Home,
        name:"首页",
        component:()=>import("../views/Home.vue"),
        meta:{
            requiresAuth:true,
            showHeader:true,
        }
    },
    {
        path:routerPath.Register,
        name:"注册",
        component:()=>import("../views/common/Register.vue"),
        meta:{
            requiresAuth:false,
            showHeader:false,
        }
    },
    {
        path:routerPath.Setting,
        name:"用户设置",
        component:()=>import("../views/user/Setting.vue"),
        meta:{
            requiresAuth:true,
            showHeader:true,
        }
    },
    {
        path:routerPath.Lesson,
        name:"课程",
        component:()=>import("../views/lesson/Lesson.vue"),
        children:[
            {
                path:"",
                name:"主页",
                component:()=>import("../views/lesson/LessonHome.vue"),
            },
            {
                path:"task",
                name:"作业",
                component:()=>import("../views/lesson/Task.vue"),
            },
            {
                path:"topic",
                name:"选题",
                component:()=>import("../views/lesson/Topic.vue"),
            },
            {
                path:"team",
                name:"团队",
                component:()=>import("../views/lesson/Team.vue"),
            },
            {
                path:"lesson-setting",
                name:"课程设置",
                component:()=>import("../views/lesson/LessonSetting.vue"),
            },
            {
                path:"question",
                name:"题目",
                component:()=>import("../components/lesson/TaskQuestionEdit.vue"),
            },
            {
                path:"task-edit",
                name:"作业编辑",
                component:()=>import("../components/lesson/TaskAnswerEdit.vue"),
            },
            {
                path:"task-score",
                name:"作业评分",
                component:()=>import("../components/lesson/TaskScoreEdit.vue"),
            }
        ],
        meta:{
            requiresAuth:false,
            showHeader:true,
        }
    },
    {
        path:routerPath.Admin,
        name:"后台",
        component:()=>import("../views/admin/Admin.vue"),
        children:[
            {
                path:"",
                redirect:"/admin/dashboard"
            },
            {
                path:"dashboard",
                name:"仪表盘",
                component:()=>import("../views/admin/Dashboard.vue"),
            },
            {
                path:"conf",
                name:"配置",
                component:()=>import("../views/admin/Config.vue"),
            },
            {
                path:"user",
                name:"用户",
                component:()=>import("../views/admin/UserConfig.vue"),
            },
        ],
        meta:{
            requiresAuth:false,     // 是否需要登录
            showHeader:true,
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
            to.name&&(document.title =to.name.toString()+" | Aoki")
            next()
        }else{
            storage.setItem("redirectPath",to.fullPath)
            to.name&&(document.title ="登录 | Aoki")
            next({
                path:routerPath.Login,
            })
        }
    }else{
        to.name&&(document.title =to.name.toString()+" | Aoki")
        next()
    }
})


export default router
