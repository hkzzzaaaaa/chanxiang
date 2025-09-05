<script setup lang="ts">
import {
  Plus,
  ArrowRight
} from '@element-plus/icons-vue'
import { useRouter} from 'vue-router';
const router=useRouter();
const handleSelectChange = (e: Event) => {
  const selectElement = e.target as HTMLSelectElement;
  const value = selectElement.value;
  if (value) {
    router.push(value);
  }
};
const toWorkManagement=function(){
    router.push('workManagement');
}
const toCreate=function(){
    router.push('Create')
}
const toChapterManagement=function(){
    router.push('ChapterManagement')
}
const toWrite=function(){
    router.push('/write')
}
</script>
<template>
    <div class="top">
        <div class="top-main">中午好，尊敬的xxxxxxxxxx用户</div>
        <div class="top-fan">
            <div class="text-gray" style="font-size: 15px;margin-bottom: 5px;">粉丝数</div>
            <div>21</div>
        </div>
        <div class="text-count">
            <div class="text-gray" style="font-size: 15px; margin-bottom: 5px;">累计创作字数</div>
            <div>26301503</div>
        </div>
    </div>
    <br></br>
    <div class="card-main">
         <div class="card-main-top">
            <div class="card-main-top-text">小说</div>
            <el-button class="card-main-top-button" :icon="Plus" text="true" @click="toCreate">创建新书&nbsp;</el-button>
            <div class="text-gray" style="margin-top: 15px;">|</div>
            <el-button class="card-main-top-button" :icon="ArrowRight" text="true" @click="toWorkManagement">查看全部&nbsp;</el-button>
         </div>
         <div class="card-main-novel">
            <div class="book-picture">
                 <img style="width: 125px; height: 175px;" src="../assets/1.jpg"></img>
            </div>
            <div class="book-text">
                 <div class="book-name">我不是戏神</div>
                 <div class="text-gray" style="font-size: 15px;margin-bottom: 100px;margin-left: 2px;">最近更新:暂未发布章节</div>
                 <div class="text-gray" style="font-size: 15px;margin-left: 2px;">0章&nbsp;&nbsp;|&nbsp;&nbsp;0字</div>
            </div>
            <div class="button">
                    <select class="button-select" placeholder="作品相关" @change="handleSelectChange">
                        <option value="" selected disabled class="hidden-option">作品相关</option>
                        <option value="/author/Book-Info/settings">作品设置</option> 
                        <option value="/author/Book-Info/contract">签约管理</option>
                    </select>
                   <el-button class="button-style-1" round style="margin-right: 5px;" @click="toChapterManagement">章节管理</el-button>
                   <el-button class="button-style-2" round @click="toWrite">创建章节</el-button>
            </div>
         </div>
    </div>
</template>

<style scoped>
.button-style-2:hover {
    background-color: #ff9900;
}
.button-style-2{
    width: 100px;
    height: 35px;
    background-color:darkorange;
    color:rgb(255, 255, 255)
}
.button-style-1:hover{
    background-color: #f0f2f5;
}
.button-style-1{
    width: 100px;
    height: 35px; 
    background-color:  #fafafa;
    color: #606266;
}
.button-select option.hidden-option {
  display: none;
}
.button-select:hover{
    background-color: #f0f2f5;
}
.button-select{
    width: 120px;
    height: 35px;
    padding: 6px 12px; 
    border: 1px solid #dcdfe6;
    border-radius: 20px; 
    background-color: #fafafa;;
    font-size: 14px; 
    color: #606266; 
    appearance: none; 
    background-image: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' viewBox='0 0 12 12'%3E%3Cpath fill='%23606266' d='M3.4 5l2.6 2.6 2.6-2.6z'/%3E%3C/svg%3E");
    background-repeat: no-repeat;
    background-position: right 10px center; 
    outline: none;
    margin-right: 20px;
    
}
.button{
    width: 400px;
    height: 40px;
    margin-top: 25px;
    margin-left: 100px;
    padding-top: 135px;
}
.book-name{
    width: 350px;
    font-size: 18px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    margin-bottom: 10px;
}
.book-text{
    width: 400px;
    height: 175px;
    margin-top: 25px;
}
.book-picture{
    margin-top: 25px;
    margin-left: 20px;
    margin-right: 25px;
    width: 125px;
    height: 175px;
}
.card-main-novel{
    display: flex;
    width: 1000px;
    height: 200px;
}
.card-main-top-button:hover{
    color: chocolate;
}
.card-main-top-button{
flex-direction: row-reverse;
margin-top: 10px;
}
.card-main-top-text{
    margin-top: 10px;
    font-size: 20px;
    margin-left: 20px;
    margin-right: 700px;
}
.card-main-top{
    width: 1000px;
    height: 50px;
    display: flex;
}
.card-main{
    width: 1000px;
    height: 250px;
    background-color: rgb(255, 255, 255);
}
.text-count{
    margin-top: 20px;
    text-align: center;
    font-size: 18px;
}
.top{
    display: flex;
    width: 1000px;
    height: 100px;
    background-color:rgb(255, 255, 255);
}
.top-main{
    margin-left: 60px;
    margin-top: 30px;
    margin-right: 300px;
    font-size: 18px;
}
.top-fan{
    margin-top: 20px;
    margin-right: 140px;
    font-size: 18px;
    text-align: center;
}
</style>