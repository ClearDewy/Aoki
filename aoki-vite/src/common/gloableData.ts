// 全局时间
import mitt from "mitt";
export const emitter=mitt()










// 账号
import {ref} from "vue";
import {LessonType, UserType} from "./typeClass";
import {storage} from "./storage";




export const User=ref<UserType>(storage.getItem("User")||{
    avatarURL: "", email: "", major: "", name: "", password: "", role: null, username: ""
})

export const Lesson=ref<LessonType>(storage.getItem("Lesson")||{
    introduction: "", teamMemberLimit: null, topicMod: false,
    avatarURL: "", id: null, name: "", ownerName: ""
})