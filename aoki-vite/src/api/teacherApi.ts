import {LessonType} from "../common/typeClass";
import {apiUrl, POST} from "./api";

export const teacherApi={
    createLesson(cl:LessonType){
        return POST(apiUrl.createLesson,cl)
    },
    addLessonMember(username:string,id:number){
        return POST(apiUrl.addLessonMember,{username:username,id:id})
    }
}