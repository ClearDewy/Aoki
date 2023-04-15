import {apiUrl, POST_FILE} from "./api";


export const FileApi={
    uploadAvatar(avatar:File){
        return POST_FILE(apiUrl.uploadAvatar,{avatar:avatar})
    },
    uploadImage(image:File){
        return POST_FILE(apiUrl.uploadImage,{image:image})
    },
    uploadFile(file:File){
        return POST_FILE(apiUrl.uploadFile,{file:file})
    }
}