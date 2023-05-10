import {MailConfigType, UserType} from "../common/typeClass";
import {apiUrl, GET, POST, post} from "./api";

export const adminApi={
    getMailConfig(){
      return GET(apiUrl.getMailConfig)
    },
    updateMailConfig(mailConfig:MailConfigType){
        return post(apiUrl.updateMailConfig,mailConfig)
    },
    getAllUser(){
        return GET(apiUrl.getAllUser)
    },
    addUser(user:UserType){
        return POST(apiUrl.addUser,user)
    },
    updateUser(user:UserType){
        return POST(apiUrl.updateUser,user)
    },
    deleteUser(id:number){
        return post(apiUrl.deleteUser,{id:id})
    },
    addMajor(major:string){
        return post(apiUrl.addMajor,{major:major})
    },
    deleteMajor(major:string){
        return post(apiUrl.deleteMajor,{major:major})
    }
}