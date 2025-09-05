<script setup lang="ts">
import {Lock} from '@element-plus/icons-vue'
import { useRouter } from 'vue-router';
const router=useRouter();
const toMain=function(){
    router.push('/')
}
const toRead=function(){
    router.push('/read')
}
</script>

<template>
<div class="main">
    <div style="width: 100%; height: 72px;"></div>
    <div class="top">
        <div class="top-1">
            <div class="top-1-main" @click="toMain">首页</div>
            <div class="text-gray">/</div>
            <div class="top-1-name">我不是戏神</div>
        </div>
        <div class="top-2">
            <div class="picture">
                 <img style="width: 180px;height: 234px;" src="../assets/1.jpg"></img>
            </div>
            <div class="text">
                 <div class="book-name">我不是戏神</div>
                 <div class="book-tag">
                      <div class="tag1">连载中</div>
                      <div class="tag2">都市高武</div>
                 </div>
                 <div class="book-count">213.6万字</div>
                 <div class="recently-update">
                    <div>最近更新：</div>
                    <div>第988章 载酒基建&技能修改</div>
                    <div style="font-size: 14px;margin-left: 10px;">2025-08-23 06:00</div>
                 </div>
                 <div class="bottom">
                    <el-button class="button-style-2" round @click="toRead">开始阅读</el-button>
                    <el-button class="button-style-1" round>加入书架</el-button>
                 </div>
            </div>
            <div class="author">
                <div class="author-name">作者：三音九域</div>
                <div class="author-sign">没有简介就是最好的简介</div>
            </div>
        </div>
    </div>
    <div class="container">
         <div class="container-introduction">
            <div class="introduction-top">作品简介</div>
            <hr></hr>
            <div class="introduction-text">
赤色流星划过天际后，人类文明陷入停滞。 从那天起，人们再也无法制造一枚火箭，一颗核弹，一架飞机，一台汽车……近代科学堆砌而成的文明金字塔轰然坍塌，而灾难，远不止此。 灰色的世界随着赤色流星降临，像是镜面后的鬼魅倒影，将文明世界一点点拖入无序的深渊。 在这个时代，人命渺如尘埃； 在这个时代，人类灿若星辰。 大厦将倾，有人见一戏子屹立文明废墟之上，红帔似血，时笑时哭， 时代的帘幕在他身后缓缓打开，他张开双臂，
            </div>
         </div>
         <div class="container-content">
            <div class="content-top">目录</div>
            <hr></hr>
            <div class="content-main">
                <div class="content-main-1" v-for="value in 100">
                    <div class="chapter-text">第{{value}}章 戏鬼回家</div>
                    <div><el-icon><Lock /></el-icon></div>
                </div>
            </div>
         </div>
    </div>
</div>
</template>

<style scoped>
.chapter-text{
    width: 280px;
    white-space: nowrap;
    overflow: hidden; 
    text-overflow: ellipsis;   
    margin-right: 20px;
}
.content-main-1{
    display: flex;
    align-items: center;
}
.content-main{
    display: grid;
    grid-template-columns:  repeat(3, 1fr);
    gap: 20px 0px;
}
.content-top{
    font-size: 24px;
}
.container-introduction{
    margin-bottom: 60px;
}
.introduction-text{
    overflow: hidden; 
    text-overflow: ellipsis;
    box-sizing: border-box;
    line-clamp: 3;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    padding: 20px;
    height: 85px;
}
.introduction-top{
    font-size: 24px;
}
.container{
    box-sizing: border-box;
    padding: 40px;
    margin:auto;
    margin-top: 40px;
    width: 90%;
    background-color:azure; 
}
.author-sign{
    overflow-wrap: break-word;
    white-space: normal;
    max-width: 200px;
    font-size: 14px;
    color: darkgray;
}
.author-name{
    font-size: 20px;
}
.author{
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 20px 0px;
    padding: 20px;
    box-sizing: border-box;
    width: 400px;
    height: 100%;
    border-left: 2px solid #ccc;
}
.button-style-1:hover{
    background-color: #f0f2f5;
}
.button-style-1{
    width: 150px;
    height: 35px; 
    background-color:  #fafafa;
    color: #606266;
}
.button-style-2:hover {
    background-color: #ff9900;
}
.button-style-2{
    width: 150px;
    height: 35px;
    background-color:darkorange;
    color:rgb(255, 255, 255)
}
.bottom{
    display: flex;
    gap: 0px 20px;
}
.recently-update{
    align-items: center;
    display: flex;
    margin-bottom: 20px;
}
.book-count{
    font-size: 20px;
    margin-bottom: 20px;
}
.tag2{
    color: darkgray;
    border: 1px solid;
     border-radius: 5px;
}
.tag1{
    color:#fdae31;
    border: 1px;
    border-style:solid;
    border-radius: 5px;
}
.book-tag{
    margin-bottom: 20px;
    display: flex;
    gap: 0px 20px;
}
.book-name{
    margin-bottom: 20px;
    font-size: 24px;
}
.text{
    padding: 20px;
    box-sizing: border-box;
    width: 600px;
    height: 100%;
}
.top-2{
    gap: 0px 20px;
    display: flex;
}
.top-1-main:hover{
    color: coral;
}
.top-1-main{
    cursor: pointer;
}
.top-1-name:hover{
    color: coral;
}
.top-1-name{
    color: darkgray;
    cursor: pointer;
}
.top-1{
    display: flex;
    gap: 0px 10px;
}
.top{
    display: flex;
    flex-direction: column;
    gap: 60px 0px;
    width: 100%;
    height: 400px;
    box-sizing: border-box;
    padding: 20px;
    background-color: azure; 
}
.main{
    width: 100%;
    height: 100vh;
}
</style>