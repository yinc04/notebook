<script setup>
import {Lock, User} from '@element-plus/icons-vue'
import {h, ref} from "vue";
import {loginAPI, registerAPI, selectByUsername} from "@/api/user.js";
import {ElMessage, ElMessageBox} from "element-plus";
import {useRouter} from "vue-router";
import {useUserInfoStore} from "@/stores/userInfo.js";

// 路由
const router = useRouter();

// 给表单绑定数据
const formData = ref({
  username: '',
  password: '',
  surePassword: ''
});

// 选择登录表单还是注册表单
const choseForm = ref(true);

// 获取表单对象
const rulesFormRef = ref(null);

// 改变表单
const choseFormData = () => {
  clearForm();
  // 改变表单
  choseForm.value = !choseForm.value
}

// 重置表单
const resetForm = () => {
  clearForm();
};

// 清空表单
const clearForm = () => {
  formData.value = {
    username: '',
    password: '',
    surePassword: ''
  }
}

// 确认密码的校验
const surePasswordValid = (rule, value, callback) => {
  if (value == null || value === '') {
    callback(new Error('请输入密码'));
  } else if (formData.value.password !== value) {
    callback(new Error('两次密码不一致'));
  } else {
    callback();
  }
}

// 表单校验规则
const rules = ({
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'},
    {min: 1, max: 10, message: '用户名的长度必须为1~10位', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 18, message: '密码长度必须为6~18位', trigger: 'blur'}
  ],
  surePassword: [{validator: surePasswordValid, trigger: 'change'}]
});

// 点击提交表单按钮，整体校验
const submitForm = (type) => {
  if (type === 'login') {
    // 登录请求
    (async () => {
      const result = await loginAPI({username: formData.value.username, password: formData.value.password});
      if (result.code === 1) {
        if (result.data) {
          ElMessage.success('登录成功!');
          useUserInfoStore().setInfo(result.data);
          router.push('/');
        } else {
          ElMessage.info('账号或密码错误!');
        }
      } else {
        ElMessage.error('登录错误!');
      }
    })();
  } else {
    // 注册请求
    (async () => {
      const res = await selectByUsername({username: formData.value.username});
      if (res.data) {
        ElMessage.warning('该用户名已存在');
        return;
      }

      const result = await registerAPI({username: formData.value.username, password: formData.value.password});
      if (result.code === 1) {
        ElMessageBox({
          title: '注册成功',
          confirmButtonText: '点击复制',
          message: h('p', null, [
            h('p',
                {style: {marginTop: '20px', fontWeight: '700'}},
                [h('i', {class: 'el-icon-user'}), '账号: ' + formData.value.username]),

            h('p',
                {style: {marginTop: '20px', fontWeight: '700'}},
                [h('i', {class: 'el-icon-lock'}), '密码: ' + formData.value.password]),
          ]),
          callback: () => {
            navigator.clipboard.writeText(result.data + '\n' + formData.value.password)
                .catch((error) => {
                  ElMessage.error('当前浏览器不支持复制');
                });
          },
        })
      } else {
        ElMessage.error('注册失败');
      }
    })();
  }
}
</script>

<template>
  <el-row class="login-page">
    <!--背景图片-->
    <el-col :span="12" class="bg"></el-col>

    <!--登录表单-->
    <el-col :span="6" :offset="3" class="form" v-if="choseForm">
      <el-form ref="rulesFormRef" :model="formData" size="large" :rules="rules" status-icon>
        <el-form-item>
          <h1 class="form-title">登&nbsp;&nbsp;&nbsp;&nbsp;录</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入用户名" autofocus v-model="formData.username"
                    autocomplete="uername"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :prefix-icon="Lock" placeholder="请输入密码" v-model="formData.password" type="password"
                    show-password autocomplete="current-password"></el-input>
        </el-form-item>
        <el-form-item>
          <div class="flex-box">
            <el-checkbox>记住密码</el-checkbox>
            <el-link type="primary" :underline="false">忘记密码？
            </el-link>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button class="login-button" type="primary" @click="submitForm('login')">登录</el-button>
        </el-form-item>
        <el-form-item>
          <el-link @click="choseFormData">注册 →</el-link>
        </el-form-item>
      </el-form>
    </el-col>

    <!--注册表单-->
    <el-col :span="6" :offset="3" class="form" v-else>
      <el-form ref="ruleFormRef" :model="formData" size="large" :rules="rules" status-icon>
        <el-form-item>
          <h1 class="form-title">注&nbsp;&nbsp;&nbsp;&nbsp;册</h1>
        </el-form-item>
        <el-form-item prop="username">
          <el-input :prefix-icon="User" placeholder="请输入昵称" autofocus v-model="formData.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input :prefix-icon="Lock" placeholder="请输入密码" v-model="formData.password" type="password"
                    show-password></el-input>
        </el-form-item>
        <el-form-item prop="surePassword">
          <el-input :prefix-icon="Lock" placeholder="请确认密码" v-model="formData.surePassword" show-password
                    type="password"></el-input>
        </el-form-item>

        <el-form-item>
          <div class="register-button">
            <el-button type="primary" @click="submitForm('register')">注册</el-button>
            <el-button class="reset-button" @click="resetForm">重置</el-button>
          </div>
        </el-form-item>
        <el-form-item>
          <el-link @click="choseFormData">← 登录</el-link>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<style lang="scss" scoped>
.login-page {
  height: 100vh;

  .bg {
    background: url('@/assets/login-bg.png') no-repeat center / cover;
    border-radius: 0 20px 20px 0;
  }

  .form {
    display: flex;
    flex-direction: column;
    justify-content: center;

    .form-title {
      width: 100%;
      text-align: center;
    }

    .flex-box {
      width: 100%;
      display: flex;
      justify-content: space-between;
    }

    .login-button {
      width: 100%;
    }

    .register-button {
      width: 100%;

      button {
        width: 7.5rem;
      }

      .reset-button {
        margin-left: 1.875rem;
      }
    }
  }
}
</style>
