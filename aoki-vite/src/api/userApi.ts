import {GET, post, POST_FILE} from "./api"
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
        post(apiUrl.login,account).then(res=>{
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
        return post(apiUrl.getVerifyCode,{email:email})
    },
    verifyCode(email:string,code:string){
        return post(apiUrl.verifyCode,{email:email,code:code})
    },
    register(user:UserRegisterType){
        post(apiUrl.register,user).then(res=>{
            alertsuccess("注册成功")
            router.replace(routerPath.Login)
        })
    },
    getAllMajor(){
        return GET(apiUrl.getAllMajor)
    },
    emailLogin(emailLogin:EmailLoginType){
        post(apiUrl.emailLogin,emailLogin).then(res=>{
            loginSuccess(res)
        })
    },
    updatePassword(uPassword:UpdatePasswordType){
        return post(apiUrl.updatePassword,uPassword)
    },
    updateEmail(uEmail:UpdateEmailType){
        return post(apiUrl.updateEmail,uEmail)
    },
    updateAvatar(avatarURL:string){
        return post(apiUrl.updateAvatar, {avatarURL:avatarURL})
    },
    getLessonList(){
        return GET(apiUrl.getLessonList)
    },
    getLesson(id:number){
        return post(apiUrl.getLesson,{id:id})
    },
    getLessonMember(id:number){
        return post(apiUrl.getLessonMember,{id:id})
    },
    getMilestones(id:number){
        return post(apiUrl.getMilestones,{id:id})
    },
    createTeam(name:string,lessonId:number){
        return post(apiUrl.createTeam,{name:name,lessonId:lessonId})
    },
    getTeams(lessonId:number){
        return post(apiUrl.getTeams,{lessonId:lessonId})
    },
    getNoTeamMembers(lessonId:number){
        return post(apiUrl.getNoTeamMembers,{lessonId:lessonId})
    },
    addTeamMember(teamId:number,username:string){
        return post(apiUrl.addTeamMember,{teamId:teamId,username:username})
    },
    removeTeamMember(teamId:number,username:string){
        return post(apiUrl.removeTeamMember,{teamId:teamId,username:username})
    },
    getTopicTime(lessonId:number){
        return post(apiUrl.getTopicTime,{lessonId:lessonId})
    },
    getTopics(lessonId:number){
        return post(apiUrl.getTopics,{lessonId:lessonId})
    },
    getMyTopic(lessonId:number){
        return post(apiUrl.getMyTopic,{lessonId:lessonId})
    },
    addTopicMember(topicId:number){
        return post(apiUrl.addTopicMember,{topicId:topicId})
    },
    removeTopicMember(topicId:number){
        return post(apiUrl.removeTopicMember,{topicId:topicId})
    },
    getMemberTasks(topicId:number){
        return post(apiUrl.getMemberTasks,{topicId:topicId})
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
