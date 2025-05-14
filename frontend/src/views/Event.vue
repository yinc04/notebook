<script setup>
import {onMounted, ref} from 'vue';
import {selectAllCategoryAPI} from "@/api/category.js";
import {addEventAPI, deleteEventAPI, selectAllEventAPI, updateEventAPI} from "@/api/event.js";
import {useUserInfoStore} from "@/stores/userInfo.js";
import {Delete, Edit, Search} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";

// 展示分类列表的数据
const categorys = ref([]);

const events = ref([]);

// 编辑、添加
const dialogType = ref('');
const dialogVisable = ref(false);
const eventModel = ref({
  title: '',
  type: '',
  level: '',
  startDate: '',
  endDate: '',
  content: '',
  state: '',
  username: useUserInfoStore().info.username,
})

// 类型筛选
const filtersType = ref([]);

// 请求获取分类列表
const getAllCatagory = async () => {
  const result = await selectAllCategoryAPI({username: useUserInfoStore().info.username});
  categorys.value = result.data;
  filtersType.value = result.data.map(item => ({text: item.type, value: item.type}));
}

// 请求获取所有事件
const getAllEvents = async () => {
  const result = await selectAllEventAPI({username: useUserInfoStore().info.username});
  events.value = result.data;
}

onMounted(() => {
  getAllCatagory();
  getAllEvents();
})

function tableRowClassName({row}) {
  if (row.level === '紧急') {
    return 'emergency-row';
  } else if (row.level === '重要') {
    return 'important-row';
  }
  return '';
}

const addEvent = () => {
  dialogType.value = '添加';
  dialogVisable.value = true;
  eventModel.value = ({
    title: '',
    type: '',
    level: '重要',
    startDate: '',
    endDate: '',
    content: '',
    state: '未完成',
    username: useUserInfoStore().info.username,
  })
}

const updateEvent = (row) => {
  dialogType.value = '查看';
  dialogVisable.value = true;
  eventModel.value = row;
}

const deleteEvent = async (row) => {
  const result = await deleteEventAPI(row);
  if (result.code === 1) {
    ElMessage.success('删除成功');
    await getAllEvents();
  } else {
    ElMessage.error('删除失败');
  }
}

const clickAddButton = async () => {
  const result = await addEventAPI(eventModel.value);
  if (result.code === 1) {
    ElMessage.success('添加成功');
    await getAllEvents();
    dialogVisable.value = false;
  } else {
    ElMessage.error('添加失败');
  }
}

const clickUpdateButton = async () => {
  const result = await updateEventAPI(eventModel.value);
  if (result.code === 1) {
    ElMessage.success('更新成功');
    await getAllEvents();
    dialogVisable.value = false;
  } else {
    ElMessage.error('更新失败');
  }
}

const filterLevel = (value, row) => {
  return row.level === value;
}

const filterState = (value, row) => {
  return row.state === value;
}

const filterType = (value, row) => {
  return row.type === value;
}

const searchValue = ref('');
const tempEvents = ref([]);
const titleSearchEnter = () => {
  tempEvents.value = events.value;
  events.value = events.value.filter(item => item.title.includes(searchValue.value));
}

const titleSearchInput = () => {
  if (searchValue.value === '')
    events.value = tempEvents.value;
}
</script>

<template>
  <el-card class="page-container">
    <template #header>
      <div class="header">
        <span style="font-weight: bold">事件管理</span>
        <el-input placeholder="请输入事件标题" :prefix-icon="Search" clearable @keydown.enter="titleSearchEnter"
                  @input="titleSearchInput" v-model="searchValue" style="width: 50%"/>
        <div class="extra">
          <el-button type="primary" @click="addEvent">添加事件</el-button>
        </div>
      </div>
    </template>

    <el-table :data="events" style="width: 100%" height="420" border :row-class-name="tableRowClassName">
      <el-table-column label="编号" type="index" width="70"></el-table-column>
      <el-table-column prop="level" label="级别" sortable :filter-method="filterLevel" :filters="[
          { text: '紧急', value: '紧急' },{ text: '重要', value: '重要' },{ text: '一般', value: '一般' }]"/>
      <el-table-column prop="title" label="标题"/>
      <el-table-column prop="type" label="类别" sortable :filter-method="filterType" :filters="filtersType"/>
      <el-table-column prop="state" label="状态" sortable :filter-method="filterState" :filters="[
          { text: '完成', value: '完成' },{ text: '未完成', value: '未完成' },{ text: '作废', value: '作废' }]"/>
      <el-table-column prop="startDate" label="开始时间" sortable format="YYYY-MM-DD hh:mm:ss"/>
      <el-table-column prop="endDate" label="结束时间" sortable format="YYYY-MM-DD hh:mm:ss"/>
      <el-table-column label="操作" width="120">
        <template #default="{ row }">
          <el-button :icon="Edit" circle plain type="primary" @click="updateEvent(row)"/>
          <el-popconfirm title="是否确认删除?" @confirm="deleteEvent(row)">
            <template #reference>
              <el-button :icon="Delete" circle plain type="danger"/>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
      <template #empty>
        <el-empty description="没有数据"/>
      </template>
    </el-table>
  </el-card>

  <el-dialog v-model="dialogVisable" width="500" center :close-on-click-modal="false">
    <el-form :model="eventModel" label-width="auto" style="max-width: 600px;margin-top: 20px">
      <el-form-item label="标题" prop="title" :rules="[{ required: true, message: '请输入标题',trigger: 'blur' }]">
        <el-input v-model="eventModel.title" placeholder="请输入事件标题"/>
      </el-form-item>
      <el-form-item label="类别" prop="type" :rules="[{ required: true, message: '请选择类别',trigger: 'blur' }]">
        <el-select v-model="eventModel.type" placeholder="请选择事件类别">
          <el-option v-for="item in categorys" :key="item.type" :label="item.type" :value="item.type"/>
        </el-select>
      </el-form-item>
      <el-form-item label="级别" prop="level" :rules="[{ required: true, message: '',trigger: 'blur' }]">
        <el-select v-model="eventModel.level">
          <el-option label="紧急" value="紧急"/>
          <el-option label="重要" value="重要"/>
          <el-option label="一般" value="一般"/>
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="state" :rules="[{ required: true, message: '',trigger: 'blur' }]">
        <el-select v-model="eventModel.state">
          <el-option label="完成" value="完成"/>
          <el-option label="未完成" value="未完成"/>
          <el-option label="作废" value="作废"/>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="sytartDate">
        <el-date-picker v-model="eventModel.startDate" type="datetime" placeholder="选择日期"
                        format="YYYY-MM-DD hh:mm" value-format="YYYY-MM-DD h:m"/>
      </el-form-item>
      <el-form-item label="结束时间" prop="sytartDate">
        <el-date-picker v-model="eventModel.endDate" type="datetime" placeholder="选择日期"
                        format="YYYY-MM-DD hh:mm" value-format="YYYY-MM-DD h:m"/>
      </el-form-item>
      <el-form-item label="内容" prop="content" :rules="[{ required: true, message: '请输入内容',trigger: 'blur' }]">
        <el-input v-model="eventModel.content" type="textarea" placeholder="请输入事件内容"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogVisable = false">取消</el-button>
        <el-button type="primary" @click="clickAddButton" v-if="dialogType === '添加'">新增</el-button>
        <el-button type="primary" @click="clickUpdateButton" v-else>更新</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style lang="scss">
.page-container {
  min-height: 100%;
  box-sizing: border-box;

  .header {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .el-table {
    .emergency-row {
      background: #ffeeba;
    }

    .important-row {
      background: #d4edda;
    }
  }
}
</style>
