import {CreateLessonType, LessonType} from "../common/typeClass";
import {apiUrl, POST} from "./api";

export const teacherApi={
    createLesson(cl:CreateLessonType){
        return POST(apiUrl.createLesson,cl)
    },
    addLessonMember(username:string,id:number){
        return POST(apiUrl.addLessonMember,{username:username,id:id})
    },
    removeLessonMembers(id:number,idList:number[]){
        return POST(apiUrl.removeLessonMembers,{id:id,idList:idList})
    }
}