import {GET, POST, POST_FILE} from "./api"
import router, {routerPath} from "../router";
import {storage} from "../common/storage";
import {alertsuccess} from "../common/alert";
import {AxiosResponse} from "axios";
import {apiUrl} from "./api";
import {
    AccountType, EditTopicType,
    EmailLoginType,
    UpdateEmailType,
    UpdatePasswordType,
    UserRegisterType,
    UserType
} from "../common/typeClass";
import {User} from "../common/gloableData";


export const userApi={
    login:(account:AccountType)=>{
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
    register(user:UserRegisterType){
        POST(apiUrl.register,user).then(res=>{
            alertsuccess("注册成功")
            router.replace(routerPath.Login)
        })
    },
    getAllMajor(){
        return GET(apiUrl.getAllMajor)
    },
    emailLogin(emailLogin:EmailLoginType){
        POST(apiUrl.emailLogin,emailLogin).then(res=>{
            loginSuccess(res)
        })
    },
    updatePassword(uPassword:UpdatePasswordType){
        return POST(apiUrl.updatePassword,uPassword)
    },
    updateEmail(uEmail:UpdateEmailType){
        return POST(apiUrl.updateEmail,uEmail)
    },
    updateAvatar(avatarURL:string){
        return POST(apiUrl.updateAvatar, {avatarURL:avatarURL})
    },
    getLessonList(){
        return GET(apiUrl.getLessonList)
    },
    getLesson(id:number){
        return POST(apiUrl.getLesson,{id:id})
    },
    getLessonMember(id:number){
        return POST(apiUrl.getLessonMember,{id:id})
    },
    getMilestones(id:number){
        return POST(apiUrl.getMilestones,{id:id})
    },
    createTeam(name:string,lessonId:number){
        return POST(apiUrl.createTeam,{name:name,lessonId:lessonId})
    },
    getTeams(lessonId:number){
        return POST(apiUrl.getTeams,{lessonId:lessonId})
    },
    getNoTeamMembers(lessonId:number){
        return POST(apiUrl.getNoTeamMembers,{lessonId:lessonId})
    },
    addTeamMember(teamId:number,username:string){
        return POST(apiUrl.addTeamMember,{teamId:teamId,username:username})
    },
    removeTeamMember(teamId:number,username:string){
        return POST(apiUrl.removeTeamMember,{teamId:teamId,username:username})
    },
    getTopicTime(lessonId:number){
        return POST(apiUrl.getTopicTime,{lessonId:lessonId})
    },
    getTopics(lessonId:number){
        return POST(apiUrl.getTopics,{lessonId:lessonId})
    },
    getMyTopic(lessonId:number){
        return POST(apiUrl.getMyTopic,{lessonId:lessonId})
    },
    addTopicMember(topicId:number){
        return POST(apiUrl.addTopicMember,{topicId:topicId})
    },
    removeTopicMember(topicId:number){
        return POST(apiUrl.removeTopicMember,{topicId:topicId})
    }
}

// 将返回的结果赋值给User
const loginSuccess=(res:AxiosResponse|void)=>{
    res&&(User.value= res.data)&& storage.setItem("User",res.data)

    const redirect= storage.getItem("redirectPath")
    if (redirect) { //如果存在参数
        storage.remove("redirectPath")
        router.replace(redirect)//则跳转至进入登录页前的路由
    } else {
        router.replace('/')//否则跳转至首页
    }
}
