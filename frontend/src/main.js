import {createApp} from 'vue'
import {createPinia} from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import App from './App.vue'
import router from './router'
import './assets/main.scss'
// 导入参数持久化插件
import {createPersistedState} from "pinia-plugin-persistedstate";
import zhCn from 'element-plus/dist/locale/zh-cn.mjs';

const app = createApp(App)

const pinia = createPinia();
const persist = createPersistedState();
pinia.use(persist);

app.use(pinia).use(ElementPlus, {locale: zhCn}).use(router).mount('#app')
