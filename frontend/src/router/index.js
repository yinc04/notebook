// 导入组件
import {createRouter, createWebHistory} from "vue-router";
import {useUserInfoStore} from "@/stores/userInfo.js";

// 定义路由关系
const routes = [
    {
        path: '/login', component: () => import('@/views/Login.vue'),
    },
    {
        path: '/', component: () => import('@/views/Main.vue'),
        children: [
            {
                path: '/', component: () => import('@/views/Category.vue'),
            },
            {
                path: '/category', component: () => import('@/views/Category.vue'),
            },
            {
                path: '/event', component: () => import('@/views/Event.vue'),
            },
            {
                path: '/user', component: () => import('@/views/User.vue'),
            }
        ]
    },
    {
        path: '/:pathMatch(.*)*', redirect: '/login',
    }
]

// 创建路由器
const router = createRouter(({
    history: createWebHistory(),
    routes: routes,
}));

// 添加导航守卫，判断是否登录
router.beforeEach((to, from, next) => {
    const userInfo = useUserInfoStore().info;
    if (to.path === '/login') {
        next();
    } else if (userInfo && userInfo.username) {
        next();
    } else {
        next('/login');
    }
});

// 导出路由
export default router;
