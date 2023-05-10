// 账号
export type AccountType={
    username:string,
    password:string
}


// 用户信息
export type UserType={
    id:number
    username:string,
    password:string,
    name:string,
    email:string,
    major:string,
    role:number,
    avatarURL:string
}

// 用户信息
export type UserRegisterType={
    username:string,
    password:string,
    name:string,
    email:string,
    major:string,
    role:number|null,
    code:string
}

// 邮箱登录
export type EmailLoginType={
    email:string,
    code:string
}

// 更新密码
export type UpdatePasswordType={
    oldPassword:string,
    newPassword:string
}

// 更新邮箱
export type UpdateEmailType={
    password:string,
    email:string,
    code:string
}

// 新建课程
export type CreateLessonType={
    name:string,
    introduction:string,
    teamMode:boolean,
    topicMode:boolean,
    avatarURL:string
}

export type EditLessonType={
    id:number
    name:string,
    introduction:string,
    avatarURL:string
}

// 课程列表
export type LessonListType={
    id:number|null
    name:string,
    ownerName:string,
    avatarURL:string
}

export type LessonType={
    id:number|null
    name:string,
    introduction:string,
    teamMode:boolean
    topicMode:boolean,
    avatarURL:string,
    ownerId:number
    ownerName:string,
}

export type UserListType={
    id:number
    username:string
    name:string
    email:string
    major:string
    role:number
}

export type MilestonesType={
    id:number
    name:string
    beginTime:string
    endTime:string
    introduction:string
    lessonId:number
}

export type TeamMemberListType={
    id:number,
    username:string
    name:string
    avatarURL:string
}

export type TeamListType={
    id:number
    name:string
    ownerId:number
    ownerName:string
    memberList:TeamMemberListType[]
}


export type TopicType={
    id:number|null,
    name:string,
    limit:number,
    difficult:number|null,
    ownerId:number|null,
    lessonId:number
}

export type TopicListType={
    id:number,
    name:string,
    number:number,
    limit:number,
    difficult:number,
    ownerName:string
}

export type TopicTimeType={
    beginTime:string,
    endTime:string,
    lessonId:number
}

export type EditTopicType={
    id:number
    name:string,
    limit:number,
    difficult:number,
    lessonId:number
}

export type NoTopicMemberListType={
    id:number,
    name:string
}


export type TaskType={
    id:number,
    name:string,
    publish:boolean,
    topicId:number,
    beginTime:string,
    endTime:string
}

export type OwnerTaskListType={
    id:number,
    name:string,
    publish:boolean,
    topicId:number,
    topicName:string
    beginTime:string,
    endTime:string
}

export type MemberTaskListType={
    id:number,
    name:string,
    beginTime:string,
    endTime:string,
    submitted:boolean
}

export type QuestionType={
    id:number,
    taskId:number,
    content:string
}

export type ScoreRuleType={
    id:number,
    name:string,
    taskId:number,
    limit:number
}

export type SubmitAnswerType={
    questionId:number,
    answerContent:string
}

export type SubmitTaskType={
    taskId:number,
    answerList:SubmitAnswerType[]
}

export type TaskQuestionAnswerListType={
    questionId:number,
    questionContent:string,
    answerContent:string
}

export type scoreType={
    scoreRuleId:number,
    score:number
}

export type ScoreRecordType={
    taskId:number,
    memberId:number,
    scoreList:scoreType[]
}

export type ScoreEditType={
    scoreRuleId:number,
    name:string,
    limit:number,
    score:number
}


export type MailConfigType={
    username:string,
    password:string,
    host:string,
    port:number,
    protocol:string
}