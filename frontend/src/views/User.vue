<script setup>
import {ref} from 'vue'
import {useUserInfoStore} from '@/stores/userInfo.js'
import {updatePasswordAPI, updateUserAPI} from "@/api/user.js";
import {ElMessage} from "element-plus";

// 获取pinia中的用户信息
const userInfoStore = useUserInfoStore();

// 用户基本信息模型
const userInfo = ref({
  username: userInfoStore.info.username,
  trueName: userInfoStore.info.trueName,
  age: userInfoStore.info.age,
  phone: userInfoStore.info.phone,
  email: userInfoStore.info.email,
  password: ''
});

const rules = {
  trueName: [
    {
      pattern: /^\S{1,10}$/,
      message: '昵称必须是2-10位的非空字符串',
      trigger: 'blur'
    }
  ],
  age: [
    {
      pattern: /^\d{1,3}$/,
      message: '年龄必须是1-3位的数字',
      trigger: 'blur'
    }
  ],
  phone: [
    {
      pattern: /^1\d{10}$/,
      message: '电话必须是11位的数字',
      trigger: 'blur'
    }
  ],
  email: [
    {type: 'email', message: '邮箱格式不正确', trigger: 'blur'}
  ],
  password: [
    {
      pattern: /^\S{6,16}$/,
      message: '密码必须是6-18位的非空字符串',
      trigger: 'blur'
    }
  ]
}

// 更新个人信息
const updateUserInfo = async () => {
  let result = null;
  if (userInfo.value.password !== '') {
    result = await updatePasswordAPI(userInfo.value);
  } else result = await updateUserAPI(userInfo.value);

  if (result.code === 1) {
    ElMessage.success('个人信息更新成功');
    userInfo.value.password = '';
  } else {
    ElMessage.error('个人信息更新失败');
  }
  // 更新pinia中的用户信息
  userInfoStore.setInfo(userInfo.value);
}
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header" style="display: flex;justify-content: space-between;font-weight: 700;">
        <span>基本资料</span>
      </div>
    </template>

    <el-row>
      <!--  左边部分    -->
      <el-col :span="12">
        <el-form :model="userInfo" :rules="rules" label-width="100px" size="large">
          <el-form-item label="用户名">
            <el-input v-model="userInfo.username" disabled></el-input>
          </el-form-item>
          <el-form-item label="用户姓名" prop="trueName">
            <el-input v-model="userInfo.trueName"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-input v-model="userInfo.age"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="userInfo.phone"></el-input>
          </el-form-item>
          <el-form-item label="用户邮箱" prop="email">
            <el-input v-model="userInfo.email"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="userInfo.password" type="password" show-password></el-input>
          </el-form-item>
          <div style="display: flex;">
            <el-form-item style="margin-left: 35%;">
              <el-button type="success" @click="updateUserInfo">修改</el-button>
            </el-form-item>
          </div>
        </el-form>
      </el-col>
    </el-row>
  </el-card>
</template>

<style lang="scss" scoped>

</style>
