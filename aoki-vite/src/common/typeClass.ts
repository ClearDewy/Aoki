// 账号
export type AccountType={
    username:string,
    password:string
}


// 用户信息
export type UserType={
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
    role:number,
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