// 账号
import {reactive} from "vue";
import {UserType} from "./typeClass";

export const User=reactive<UserType>({
    avatarURL: "", email: "", major: "", name: "", password: "", role: -1, username: ""
})