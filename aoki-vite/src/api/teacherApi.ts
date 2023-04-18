import {CreateLessonType, LessonType, MilestonesType} from "../common/typeClass";
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
    },
    addMilestones(milestones: MilestonesType){
        return POST(apiUrl.addMilestones,milestones);
    },
    updateMilestones(milestones: MilestonesType){
        return POST(apiUrl.updateMilestones,milestones)
    },
    deleteMilestones(lessonId:number,idList:number[]){
        return POST(apiUrl.deleteMilestones,{lessonId:lessonId,idList:idList})
    }
}