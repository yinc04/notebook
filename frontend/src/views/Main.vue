<script setup>
import {useUserInfoStore} from "@/stores/userInfo.js";
import defaultAvatar from "@/assets/default-avator.png";
import {CaretBottom, Management, Promotion, SwitchButton, User} from "@element-plus/icons-vue";
import router from "@/router/index.js";
import {ElMessage, ElMessageBox} from "element-plus";

const userInfoStore = useUserInfoStore();

// 条目被点击后调用的函数
const handleCommand = (command) => {
  // 判断指令
  if (command === 'logout') {
    ElMessageBox.confirm(
        '您确认退出吗？',
        '温馨提示',
        {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning',
        })
        .then(() => {
          //退出登录
          userInfoStore.removeInfo();
          // 跳转到登录
          router.push('/login');
          ElMessage.success('退出登录成功');
        }).catch(() => {
      ElMessage.info('您已取消退出登录')
    });
  } else {
    // 路由
    router.push('/user');
  }
}
</script>

<template>
  <el-container class="layout-container">
    <!-- 左侧菜单 -->
    <el-aside width="200px">
      <div class="el-aside__logo">记事本</div>
      <el-menu active-text-color="#ffd04b" background-color="#232323" text-color="#fff"
               router>
        <el-menu-item index="/category">
          <el-icon>
            <Management/>
          </el-icon>
          <span>事件分类</span>
        </el-menu-item>

        <el-menu-item index="/event">
          <el-icon>
            <Promotion/>
          </el-icon>
          <span>事件管理</span>
        </el-menu-item>

        <el-menu-item index="/user">
          <el-icon>
            <User/>
          </el-icon>
          <span>基本资料</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <!-- 右侧主区域 -->
    <el-container>
      <!-- 头部区域 -->
      <el-header>
        <div>欢迎您：<strong>{{ userInfoStore.info.username }}</strong></div>
        <el-dropdown placement="bottom-end" @command="handleCommand">
                    <span class="el-dropdown__box">
                        <el-avatar :src="defaultAvatar"/>
                        <el-icon>
                            <CaretBottom/>
                        </el-icon>
                    </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="info" :icon="User">基本资料</el-dropdown-item>
              <el-dropdown-item command="logout" :icon="SwitchButton">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>

      <!-- 中间区域 -->
      <el-main>
        <router-view></router-view>
      </el-main>

      <!-- 底部区域 -->
      <el-footer>简易记事本 </el-footer>
    </el-container>
  </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
  height: 100vh;

  .el-aside {
    background-color: #232323;

    &__logo {
      color: white;
      font-size: 30px;
      font-family: 幼圆, serif;
      font-weight: bold;
      letter-spacing: 5px;
      margin-left: 20px;
      margin-top: 20px;
      margin-bottom: 20px;
    }

    .el-menu {
      border-right: none;
    }
  }

  .el-header {
    background-color: #fff;
    display: flex;
    align-items: center;
    justify-content: space-between;

    .el-dropdown__box {
      display: flex;
      align-items: center;

      .el-icon {
        color: #999;
        margin-left: 10px;
      }

      &:active,
      &:focus {
        outline: none;
      }
    }
  }

  .el-footer {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 14px;
    color: #666;
  }
}
</style>
