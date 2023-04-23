import {
    CreateLessonType,
    EditLessonType,
    EditTopicType,
    LessonType,
    MilestonesType, TaskType,
    TopicTimeType
} from "../common/typeClass";
import {apiUrl, POST} from "./api";

export const teacherApi={
    createLesson(cl:CreateLessonType){
        return POST(apiUrl.createLesson,cl)
    },
    updateLesson(el:EditLessonType){
        return POST(apiUrl.updateLesson,el)
    },
    deleteLesson(lessonId:number,code:string){
        return POST(apiUrl.deleteLesson,{lessonId:lessonId,code:code})
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
    updateTopicTime(topicTime:TopicTimeType){
        return POST(apiUrl.updateTopicTime,topicTime)
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
    },
    createTask(task:TaskType){
        return POST(apiUrl.createTask,task)
    },
    updateTask(task:TaskType){
        return POST(apiUrl.updateTask,task)
    },
    deleteTask(taskId:number){
        return POST(apiUrl.deleteTask,{taskId:taskId})
    },
    getOwnerTasks(lessonId:number){
        return POST(apiUrl.getOwnerTasks,{lessonId:lessonId})
    },
    toggleTaskPublish(taskId:number){
        return POST(apiUrl.toggleTaskPublish,{taskId:taskId})
    }
}