// 全局时间
import mitt from "mitt";
export const emitter=mitt()










// 账号
import {ref} from "vue";
import {LessonType, UserType} from "./typeClass";
import {storage} from "./storage";




export const User=ref<UserType>(storage.getItem("User")||{
    id:0,avatarURL: "", email: "", major: "", name: "", password: "", role: null, username: ""
})

export const Lesson=ref<LessonType>(storage.getItem("Lesson")||{
    introduction: "", teamMode: false, topicMode: false,
    avatarURL: "", id: null, name: "", ownerName: ""
})