<script setup lang="ts">
import {ArrowLeft,Edit} from '@element-plus/icons-vue'
import { ref } from 'vue';
import type { ComponentSize } from 'element-plus'
import { useRouter } from 'vue-router';
const router=useRouter();
const ischapter=ref(true);
const tableData = [
  {
      name: '戏鬼回家',
      count: 2057,
      state: '已发布',
      publictime:'2023-09-28',
  }
]

const currentPage1 = ref(1)
const size = ref<ComponentSize>('default')
const background = ref(false)
const disabled = ref(false)
const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
}
const toWorkManagement=function(){
    router.push('workManagement');
}
const toWrite=function(){
    router.push('/write')
}
</script>
<template>
<div class="main">
    <div class="main-top">
        <div style="width: 1000px;height: 1px;"></div>
        <div class="main-top-text" @click="toWorkManagement">
            <el-icon><ArrowLeft /></el-icon>
            <div>我不是戏神</div>
        </div>
        <div class="main-top-info">
            <div :class="{
                'main-top-ischapter':ischapter,
                'main-top-nochapter':!ischapter
            }" @click="ischapter=true">章节管理</div>
            <div :class="{
                'main-top-ischapter':!ischapter,
                'main-top-nochapter':ischapter
            }" @click="ischapter=false">草稿箱</div>
             <el-button class="button-style-2" round @click="toWrite">创建章节</el-button>
        </div>
    </div>
    <div class="main-container">
         <el-table :data="tableData" style="width: 90%;margin-left: 30px;height: 680px;">
            <el-table-column prop="name" label="章节名称" width="300" />
            <el-table-column prop="count" label="字数" width="100" />
            <el-table-column prop="state" label="状态" width="100" />
            <el-table-column prop="publictime" label="发布时间" width="300"/>
             <el-table-column  label="操作">
                <template #default="scope">
                    <el-button type="default" :icon="Edit" circle size="small"/>
                </template>
             </el-table-column>
        </el-table>   
        <div class="main-container-page">
            <div>
            <el-pagination
                v-model:current-page="currentPage1"
                :page-size="10"
                size="large"
                :disabled="disabled"
                :background="background"
                layout="prev, pager, next"
                :total="100"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                />
            </div>   
        </div>
    </div>
</div>
</template>
<style scoped>
.main-container-page{
    width: 990px;
    height: 50px;
    display: flex;
    justify-content: center;
    background-color:rgb(255, 255, 255)
}
.table{
    width: 800px;
    height: 100px;
    background-color: #ff9900;
}
.main-container{
    width: 990px;
    height: 400px;
    margin-top: 50px;
    background-color: rgb(255, 255, 255);
}
.button-style-2:hover {
    background-color: #ff9900;
}
.button-style-2{
    margin-left: 650px;
    width: 100px;
    height: 35px;
    background-color:darkorange;
    color:rgb(255, 255, 255)
}
.main-top-nochapter{
    cursor: pointer;
    color: darkgrey;
}
.main-top-ischapter{
    cursor: pointer;
}
.main-top-info{
    align-items: center;
    display: flex;
    gap: 0px 40px;
    margin-left: 20px;
}
.main-top-text:hover{
    color: coral;
    cursor: pointer;
}
.main-top-text{
    margin-left: 20px;
    margin-top: 20px;
    margin-bottom: 30px;
    display: flex;
    align-items: center;
    font-size: 20px;
}
.main-top{
    width: 990px;
}
.main{
    width: 990px;
    height: 900px;
    background-color: rgb(255, 255, 255);
}
</style>