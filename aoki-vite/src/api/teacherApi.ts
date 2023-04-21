import {CreateLessonType, EditTopicType, LessonType, MilestonesType} from "../common/typeClass";
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
    },
    createTopic(createTopic:EditTopicType){
        return POST(apiUrl.createTopic,createTopic)
    },
    updateTopic(updateTopic:EditTopicType){
        return POST(apiUrl.updateTopic,updateTopic)
    },
    deleteTopic(id:number){
        return POST(apiUrl.deleteTopic,{id:id})
    },
    getMyTopics(lessonId:number){
        return POST(apiUrl.getMyTopics,{lessonId:lessonId})
    },
    getTopicMembers(id:number){
        return POST(apiUrl.getTopicMembers,{id:id})
    },
    getNoTopicMembers(lessonId:number){
        return POST(apiUrl.getNoTopicMembers,{lessonId:lessonId})
    },
    addTopicMemberTeacher(topicId:number,memberId:number){
        return POST(apiUrl.addTopicMemberTeacher,{topicId:topicId,memberId:memberId})
    },
    removeTopicMemberTeacher(topicId:number,memberId:number){
        return POST(apiUrl.removeTopicMemberTeacher,{topicId:topicId,memberId:memberId})
    }
}