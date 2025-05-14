import httpInstance from "@/utils/axiosUtil.js";

// 获取所有事件
export function selectAllEventAPI(user) {
    return httpInstance.post('/event/selectAll', user);
}

// 新增事件
export function addEventAPI(event) {
    return httpInstance.post('/event/add', event);
}

// 删除事件
export function deleteEventAPI(event) {
    return httpInstance.post('/event/delete', event);
}

// 编辑事件
export function updateEventAPI(event) {
    return httpInstance.post('/event/update', event);
}

// 根据分类获取事件
export function selectEventByType(category) {
    return httpInstance.post('/event/selectEventByType', category);
}
