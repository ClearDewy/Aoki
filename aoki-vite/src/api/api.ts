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

export const post=(url:string, data:object)=>{
    return axios.post(url,objectToFormData(data))
}

export const POST=(url:string, data:object)=>{
    return axios.post(url,data)
}

export const POST_FILE=(url:string, data:object)=>{
    return axios.post(url,objectToFormData(data),{headers:{ 'content-type': 'multipart/form-data' }})
}

// 将对象转化为formData用于传参
export const objectToFormData=(params:object)=>{
    const data=new formData()
    Object.keys(params).forEach(key=>{
        data.append(key,params[key as keyof typeof params]);
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
    updateLesson:"/api/update-lesson",
    deleteLesson:"/api/delete-lesson",
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
    removeTeamMember:"/api/remove-team-member",

    updateTopicTime:"/api/update-topic-time",
    createTopic:"/api/create-topic",
    updateTopic:"/api/update-topic",
    deleteTopic:"/api/delete-topic",
    getMyTopics:"/api/get-my-topics",
    getTopicMembers:"/api/get-topic-members",

    getNoTopicMembers:"/api/get-no-topic-members",
    addTopicMemberTeacher:"/api/add-topic-member-teacher",
    removeTopicMemberTeacher:"/api/remove-topic-member-teacher",

    getTopicTime:"/api/get-topic-time",
    getTopics:"/api/get-topics",
    getMyTopic:"/api/get-my-topic",
    addTopicMember:"/api/add-topic-member",
    removeTopicMember:"/api/remove-topic-member",

    createTask:"/api/create-task",
    updateTask:"/api/update-task",
    deleteTask:"/api/delete-task",

    getOwnerTasks:"/api/get-owner-tasks",
    getMemberTasks:"/api/get-member-tasks",
    toggleTaskPublish:"/api/toggle-task-publish",

    getTaskQuestion:"/api/get-task-questions",
    editTaskQuestions:"/api/edit-task-questions",

    createScoreRule:"/api/create-score-rule",
    updateScoreRule:"/api/update-score-rule",
    deleteScoreRule:"/api/delete-score-rule",
    getScoreRule:"/api/get-score-rule",
    getQuestionAnswer:"/api/get-task-question-answer",
    submitTask:"/api/submit-task",
    saveTask:"/api/save-task",

    getTaskMember:"/api/get-task-member",
    getNoTaskMember:"/api/get-no-task-member",

    getTaskQuestionAnswerUsername:"/api/get-task-question-answer-username",
    getTaskQuestionAnswerTeam:"/api/get-task-question-answer-team",
    getScoreRecord:"/api/get-score-record",
    getScoreRecordTeam:"/api/get-score-record-team",
    submitScoreRecord:"/api/submit-score-record"
}