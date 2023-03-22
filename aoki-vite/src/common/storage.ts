// 封装本地存储器
export const storage={
    getItem(key:string){
        return JSON.parse(window.localStorage.getItem(key) as string)||null
    },
    setItem(key:string,value:object|string){
        window.localStorage.setItem(key,JSON.stringify(value))
    },
    remove(key:string){
        window.localStorage.removeItem(key)
    },
    clear(){
        window.localStorage.clear()
    }
}