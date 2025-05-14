<script setup>
import {Delete, Edit} from '@element-plus/icons-vue'
import {ref} from 'vue'
import {ElMessage, ElMessageBox} from "element-plus";
import {
  addCatagoryAPI,
  deleteCategoryAPI,
  selectAllCategoryAPI,
  selectCategoryByTypeAPI,
  updateCategoryAPI
} from "@/api/category.js";
import {useUserInfoStore} from "@/stores/userInfo.js";
import {selectEventByType} from "@/api/event.js";

// 展示分类列表的数据
const categorys = ref([]);

// 获取表单对象
const rulesFormRef = ref(null);

// 请求获取分类列表
const getAllCatagory = async () => {
  const result = await selectAllCategoryAPI({username: useUserInfoStore().info.username});
  categorys.value = result.data;
}

const oldType = ref('');

// 弹窗标题
const dialogTitle = ref('');

//控制分类弹窗显示
const dialogVisible = ref(false)

// 弹窗中的数据模型
const categoryModel = ref({
  type: '',
  username: useUserInfoStore().info.username,
})
// 添加分类表单校验
const rules = {
  type: [
    {required: true, message: '请输入分类的类型名称', trigger: 'blur'},
  ],
}

// 添加文章分类
const addCategory = () => {
  rulesFormRef.value.validate(async (valid) => {
    // 校验成功
    if (!valid) {
      return;
    }

    const result = await selectCategoryByTypeAPI(categoryModel.value);
    if (result.data) {
      ElMessage.warning('该分类已存在');
      return;
    }

    const result2 = await addCatagoryAPI(categoryModel.value);
    if (result2.code === 1) {
      ElMessage.success('添加成功');
      //隐藏弹窗
      dialogVisible.value = false;
      //再次访问后台接口，查询所有分类
      await getAllCatagory();
    }
  });
}

// 编辑分类弹窗的回显
const updateCategoryEcho = (row) => {
  dialogTitle.value = '修改分类';
  dialogVisible.value = true;
  categoryModel.value.type = row.type;
  oldType.value = row.type;
}

// 确认编辑分类，调用接口
const updateCategory = () => {
  rulesFormRef.value.validate(async (valid) => {
    // 校验成功
    if (!valid) {
      return;
    }

    const result = await selectCategoryByTypeAPI(categoryModel.value);
    if (result.data) {
      ElMessage.warning('该分类已存在');
      return;
    }

    const res1 = await updateCategoryAPI({
      oldType: oldType.value,
      newType: categoryModel.value.type,
      username: categoryModel.value.username
    });
    if (res1.code === 1) {
      ElMessage.success('编辑成功');
      //隐藏弹窗
      dialogVisible.value = false;
      //再次访问后台接口，查询所有分类
      await getAllCatagory();
    }
  })
}

//删除分类  给删除按钮绑定事件
const deleteCategory = (row) => {
  categoryModel.value = row;

  ElMessageBox.confirm(
      `你确认删除该分类【${row.type}】吗？`,
      '温馨提示',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning',
      })
      .then(async () => {
        // 查询该分类下是否还有事件
        const res = await selectEventByType(categoryModel.value);
        console.log(res);
        if (res.data.length >= 1) {
          ElMessage.warning('该分类下还有事件，无法删除');
          return;
        }

        //用户点击了确认
        const result = await deleteCategoryAPI(categoryModel.value);
        ElMessage.success('删除成功');
        // 刷新分类列表
        await getAllCatagory();
      })
      .catch(() => {
        //用户点击了取消
        ElMessage.info('取消删除');
      })
}

// 清空弹窗数据模型数据
const clearCatagory = () => {
  // 关闭弹窗
  dialogVisible.value = false;
  categoryModel.value = {
    type: '',
  }
};

// 页面加载完毕，执行获取分类函数
getAllCatagory();
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span style="font-weight: bold">事件分类</span>
        <div class="extra">
          <el-button type="primary" @click="dialogTitle='添加分类';dialogVisible = true;categoryModel.type = '';">
            添加分类
          </el-button>
        </div>
      </div>
    </template>

    <el-table :data="categorys" style="width: 100%">
      <el-table-column label="编号" width="200" type="index"></el-table-column>
      <el-table-column label="分类名称" prop="type"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary"
                     @click="updateCategoryEcho(row)"></el-button>
          <el-button :icon="Delete" circle plain type="danger" @click="deleteCategory(row)"></el-button>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据"/>
      </template>
    </el-table>
  </el-card>

  <!-- 弹窗 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="30%">
    <el-form ref="rulesFormRef" :model="categoryModel" :rules="rules" label-width="100px" style="padding-right: 30px">
      <el-form-item label="分类名称" prop="categoryName">
        <el-input v-model="categoryModel.type" minlength="1" maxlength="10"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
        <span class="dialog-footer">
            <el-button @click="clearCatagory()">取消</el-button>
            <el-button type="primary" @click="dialogTitle==='添加分类'? addCategory():updateCategory()">确认</el-button>
        </span>
    </template>
  </el-dialog>
</template>

<style lang="scss" scoped>
.page-container {
  min-height: 100%;
  box-sizing: border-box;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}
</style>
