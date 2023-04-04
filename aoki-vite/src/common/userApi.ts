import {GET, POST, POST_FILE} from "./api"
import {Account, EmailLogin, UserRegister,User} from "./constans";
import router, {routerPath} from "../router";
import {storage} from "./storage";
import {alertsuccess} from "./alert";
import {AxiosResponse} from "axios";
import {apiUrl} from "./api";


export const userApi={
    login:(account:typeof Account)=>{
        POST(apiUrl.login,account).then(res=>{
            loginSuccess(res)
        })
    },
    logout:()=>{
        GET(apiUrl.logout).then(res=>{
            storage.clear()
            router.replace(routerPath.Login)
        })
    },
    // 获取验证码
    getVerifyCode:(email:string)=>{
        return POST(apiUrl.getVerifyCode,{email:email})
    },
    verifyCode(email:string,code:string){
        return POST(apiUrl.verifyCode,{email:email,code:code})
    },
    register(user:typeof UserRegister){
        POST(apiUrl.register,user).then(res=>{
            alertsuccess("注册成功")
            router.replace(routerPath.Login)
        })
    },
    getAllMajor(){
        return GET(apiUrl.getAllMajor)
    },
    emaillogin(emailLogin:typeof EmailLogin){
        POST(apiUrl.emailLogin,emailLogin).then(res=>{
            loginSuccess(res)
        })
    },
    uploadAvatar(avatar:File){
        return POST_FILE(apiUrl.uploadAvatar,{avatar:avatar})
    }
}

// 将返回的结果赋值给User
const loginSuccess=(res:AxiosResponse|void)=>{
    if (res){
        Object.keys(User).forEach(key=>{
            User[key as keyof typeof User]=res.data[key] as never
        })
    }
    const redirect= storage.getItem("redirectPath")
    if (redirect) { //如果存在参数
        storage.remove("redirectPath")
        router.replace(redirect)//则跳转至进入登录页前的路由
    } else {
        router.replace('/')//否则跳转至首页
    }
}
