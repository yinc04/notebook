// axios的基础封装
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

const httpInstance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 30000,
});

// axios响应式拦截器
httpInstance.interceptors.response.use(
    result => {
        if (result.data.code === 1) {
            return result.data;
        }

        // 操作失败
        ElMessage.error(result.data.message ? result.data.message : '服务异常');
        return Promise.reject(result.data);
    },
    error => {
        console.log(error);

        //如果响应状态码时401，代表未登录，给出对应的提示，并跳转到登录页
        if (error.response.status === 401) {
            router.push('/login');
        } else {
            ElMessage.error('跳转登录页异常');
        }
        return Promise.reject(error);//异步的状态转化成失败的状态
    }
)

export default httpInstance
