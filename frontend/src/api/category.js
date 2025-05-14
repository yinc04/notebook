import httpInstance from "@/utils/axiosUtil.js";

export function selectAllCategoryAPI(user) {
    return httpInstance.post('/category/selectAllCategory', user);
}

export function addCatagoryAPI(category) {
    return httpInstance.post('/category/add', category);
}

export function deleteCategoryAPI(category) {
    return httpInstance.post('/category/delete', category);
}

export function updateCategoryAPI(map) {
    return httpInstance.post('/category/update', map);
}

export function selectCategoryByTypeAPI(category) {
    return httpInstance.post('/category/selectCategoryByType', category);
}
