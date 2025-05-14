import httpInstance from "@/utils/axiosUtil.js";

// 登录
export function loginAPI(user) {
    return httpInstance.post('/user/login', user);
}

// 注册
export function registerAPI(user) {
    return httpInstance.post('/user/register', user);
}

// 更新用户信息
export function updateUserAPI(user) {
    return httpInstance.post('/user/update', user);
}

// 获取用户信息
export function selectUserAPI(user) {
    return httpInstance.post('/user/select', user);
}

// 修改密码
export function updatePasswordAPI(user) {
    return httpInstance.post('/user/updatePassword', user);
}

// 根据用户名查询用户
export function selectByUsername(user) {
    return httpInstance.post('/user/selectByUsername', user);
}
