import {
    CreateLessonType,
    EditLessonType,
    EditTopicType,
    LessonType,
    MilestonesType, QuestionType, ScoreRecordType, ScoreRuleType, TaskType,
    TopicTimeType
} from "../common/typeClass";
import {apiUrl, POST, post} from "./api";

export const teacherApi={
    createLesson(cl:CreateLessonType){
        return post(apiUrl.createLesson,cl)
    },
    updateLesson(el:EditLessonType){
        return post(apiUrl.updateLesson,el)
    },
    deleteLesson(lessonId:number,code:string){
        return post(apiUrl.deleteLesson,{lessonId:lessonId,code:code})
    },
    addLessonMember(username:string,id:number){
        return post(apiUrl.addLessonMember,{username:username,id:id})
    },
    removeLessonMembers(lessonId:number,id:number){
        return post(apiUrl.removeLessonMembers,{lessonId:lessonId,id:id})
    },
    addMilestones(milestones: MilestonesType){
        return post(apiUrl.addMilestones,milestones);
    },
    updateMilestones(milestones: MilestonesType){
        return post(apiUrl.updateMilestones,milestones)
    },
    deleteMilestones(lessonId:number,idList:number[]){
        return post(apiUrl.deleteMilestones,{lessonId:lessonId,idList:idList})
    },
    updateTopicTime(topicTime:TopicTimeType){
        return post(apiUrl.updateTopicTime,topicTime)
    },
    createTopic(createTopic:EditTopicType){
        return post(apiUrl.createTopic,createTopic)
    },
    updateTopic(updateTopic:EditTopicType){
        return post(apiUrl.updateTopic,updateTopic)
    },
    deleteTopic(id:number){
        return post(apiUrl.deleteTopic,{id:id})
    },
    getMyTopics(lessonId:number){
        return post(apiUrl.getMyTopics,{lessonId:lessonId})
    },
    getTopicMembers(id:number){
        return post(apiUrl.getTopicMembers,{id:id})
    },
    getNoTopicMembers(lessonId:number){
        return post(apiUrl.getNoTopicMembers,{lessonId:lessonId})
    },
    addTopicMemberTeacher(topicId:number,memberId:number){
        return post(apiUrl.addTopicMemberTeacher,{topicId:topicId,memberId:memberId})
    },
    removeTopicMemberTeacher(topicId:number,memberId:number){
        return post(apiUrl.removeTopicMemberTeacher,{topicId:topicId,memberId:memberId})
    },
    createTask(task:TaskType){
        return post(apiUrl.createTask,task)
    },
    updateTask(task:TaskType){
        return post(apiUrl.updateTask,task)
    },
    deleteTask(taskId:number){
        return post(apiUrl.deleteTask,{taskId:taskId})
    },
    getOwnerTasks(lessonId:number){
        return post(apiUrl.getOwnerTasks,{lessonId:lessonId})
    },
    toggleTaskPublish(taskId:number){
        return post(apiUrl.toggleTaskPublish,{taskId:taskId})
    },
    getTaskQuestion(taskId:number){
        return post(apiUrl.getTaskQuestion,{taskId:taskId})
    },
    editTaskQuestions(questions:QuestionType[]){
        return POST(apiUrl.editTaskQuestions,questions)
    },
    createScoreRule(scoreRule: ScoreRuleType){
        return post(apiUrl.createScoreRule,scoreRule)
    },
    updateScoreRule(scoreRule: ScoreRuleType){
        return post(apiUrl.updateScoreRule,scoreRule)
    },
    deleteScoreRule(srId:number){
        return post(apiUrl.deleteScoreRule,{srId:srId})
    },
    getScoreRule(taskId:number){
        return post(apiUrl.getScoreRule,{taskId:taskId})
    },
    getTaskMember(taskId:number){
        return post(apiUrl.getTaskMember,{taskId:taskId})
    },
    getNoTaskMember(taskId:number){
        return post(apiUrl.getNoTaskMember,{taskId:taskId})
    },
    getTaskQuestionAnswer(taskId:number,id:number){
        return post(apiUrl.getTaskQuestionAnswerTeacher,{taskId:taskId,id:id})
    },
    getScoreRecord(taskId:number,id:number){
        return post(apiUrl.getScoreRecord,{taskId:taskId,id:id})
    },
    submitScoreRecord(scoreRecord:ScoreRecordType){
        return POST(apiUrl.submitScoreRecord,scoreRecord)
    }
}