import {GET,POST} from "./api"
import {Account} from "./constans";
import router from "../router";
import {storage} from "./storage";


export const userApi={
    login:(account:typeof Account)=>{
        POST("/login",account).then(res=>{
            const redirect= storage.getItem("redirectPath")
            if (redirect) { //如果存在参数
                storage.remove("redirectPath")
                router.replace(redirect)//则跳转至进入登录页前的路由
            } else {
                router.replace('/')//否则跳转至首页
            }
        })
    },
    logout:()=>{
        GET("/logout").then(res=>{
            storage.clear()
            router.replace('/login')
        })
    },

    refister:()=>{


    }
}
