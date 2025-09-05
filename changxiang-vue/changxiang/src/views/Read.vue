<script setup lang="ts">
import {Reading,Tickets,Moon,Sunny,ArrowLeft} from '@element-plus/icons-vue'
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const router=useRouter();
const ifsun=ref(true);
const bookNameSize=ref(20)
const bookInfoSize=ref(14)
const bookTextSize=ref(16)
const increaseFontSize=function(){
    if(bookTextSize.value<32){
      bookTextSize.value+=2;
      bookInfoSize.value+=2;
      bookNameSize.value+=2;
    }
}
const decreaseFontSize=function(){
    if(bookTextSize.value>16){
      bookTextSize.value-=2;
      bookInfoSize.value-=2;
      bookNameSize.value-=2;
    }
}
const toContent=function(){
    router.push('Content')
}
</script>

<template>
<div class="common-layout">
    <el-container>
      <el-aside width="200px" :class="{
        'aside1':ifsun,
        'aside2':!ifsun
      }">
      </el-aside>
      <el-main :class="{
        'main-container1':ifsun,
        'main-container2':!ifsun
      }">
        <div class="main-top" @click="toContent">
          <el-icon><ArrowLeft /></el-icon>
          <div>我不是戏神</div>
        </div>
        <hr style="border: 1px solid #555555;">
        <div class="main-name">
           <div class="book-name" :style="{ 'font-size': `${bookNameSize}px` }">
              <span style="margin-right: 10px;">第1章</span>
              <span>戏鬼回家</span>
           </div>
           <div class="book-info" :style="{ 'font-size': `${bookInfoSize}px` }">
              <span>本章字数:2057字</span>
              <span>更新时间:2023-09-28</span>
           </div>
           <div class="book-text" :style="{ 'font-size': `${bookTextSize}px` }">
              <p>"我......是谁？"</p>
              <p>轰隆——</p>
              <p>苍白的雷光闪过如墨云层</p>
              <p>雨流狂落，神怒般的雷雨浇灌在泥泞的大地，涟漪层叠的水洼倒影中，一道朱红色的人影支离破碎</p>
              <p>那是位披着大红袍的少年，他好似醉酒般的踉跄趟过满地泥泞，宽大的袖摆在狂风中飘舞，戏袍表面的泥沙被雨水冲落，那抹似红的鲜血在黑夜中触目惊心</p>
           </div>
           <div class="book-button">
               <el-button round :class="{'previous-button1':ifsun,'previous-button2':!ifsun}">上一章</el-button>
               <el-button round :class="{'next-button1':ifsun,'next-button2':!ifsun}">下一章</el-button>
           </div>
        </div>
      </el-main>
      <el-aside width="200px" :class="{
        'aside1':ifsun,
        'aside2':!ifsun
      }">
        <div class="aside-component">
            <div :class="{'aside-component-icon1':ifsun,'aside-component-icon2':!ifsun}">
                 <el-icon><Reading /></el-icon>
                 <div>加书架</div>
            </div>
            <div :class="{'aside-component-icon1':ifsun,'aside-component-icon2':!ifsun}">
                 <el-icon><Tickets /></el-icon>
                 <div>目录</div>
            </div>
             <div :class="{'aside-component-icon1':ifsun,'aside-component-icon2':!ifsun}" v-if="ifsun" @click="ifsun=false">
                 <el-icon><Moon /></el-icon>
                 <div>夜间</div>
            </div>
            <div :class="{'aside-component-icon1':ifsun,'aside-component-icon2':!ifsun}" v-if="!ifsun" @click="ifsun=true">
                 <el-icon><Sunny /></el-icon>
                 <div>日间</div>
            </div>
             <div :class="{'aside-component-icon1':ifsun,'aside-component-icon2':!ifsun}"  @click="increaseFontSize">
                  <span style="font-size: 16px;">+</span>
                  <div>加字号</div>
            </div>
                  <div :class="{'aside-component-icon1':ifsun,'aside-component-icon2':!ifsun}"  @click="decreaseFontSize">
                  <span style="font-size: 16px;">-</span>
                  <div>减字号</div>
            </div>
        </div>
      </el-aside>
    </el-container>
  </div>
</template>
<style scoped>
.next-button{
  width: 150px;
  height: 40px;
}
.next-button2:hover{
  background-color: rgb(250 103 37 / 70%);;
}
.next-button2{
  width: 150px;
  height: 40px;
  border-color: rgb(250 103 37 / 60%);
  color: #b3b3b3;
  background-color:rgb(250 103 37 / 60%);
}
.next-button1:hover{
  background-color: #ff9900;
}
.next-button1{
  width: 150px;
  height: 40px;
  color: rgb(255, 255, 255);
  background-color:darkorange;
}
.previous-button2:hover{
  background-color:hsl(0deg 0% 70% / 20%);
}
.previous-button2{
  background-color: hsl(0deg 0% 70% / 10%);
  border-color: hsl(0deg 0% 70% / 10%);
  color: hsl(0deg 0% 70% / 70%);
  width: 150px;
  height: 40px;
}
.previous-button1:hover{
  background-color: #f0f2f5;
  color: rgb(0 0 0 / 70%);
}
.previous-button1{
  background-color:  #fafafa;
  width: 150px;
  height: 40px;
}
.book-button{
  display: flex;
  justify-content: center;
  gap: 0px 20px;
}
.book-text p {
  margin: 1.2rem 0; 
  text-indent: 2em;
}
.book-text {
  line-height: 1.8;
  height: auto; 
  max-height: none;
  margin-bottom: 100px;
}
.book-info{
  margin-bottom: 40px;
  display: flex;
  gap: 0px 20px;
}
.book-name{
  margin-bottom: 10px;
}
.main-top{
  width: 350px;
  display: flex;
  align-items: center;
  font-size: 20px;
  cursor: pointer;
  margin-bottom: 20px;
}
.main-container1{
  padding: 50px;
  box-sizing: border-box;
  min-height: 100vh;
  height: auto;
}
.main-container2{
  padding: 50px;
  box-sizing: border-box;
  background-color: #1a1a1a;
  color: #fff;
  height: auto;
  min-height: 100vh;
}
.aside-component-icon2{
    width: 60px;
    height: 60px;
    font-size: 14px;
    background-color:#1a1a1a;
    color: rgb(255, 255, 255);
    display: flex;
    flex-direction: column; 
    justify-content: center; 
    align-items: center; 
    border-radius: 5px;
    margin-bottom: 20px;
    gap: 5px 0px;
    cursor: pointer;
}
.aside-component-icon1{
    width: 60px;
    height: 60px;
    font-size: 14px;
    background-color: aliceblue;
    display: flex;
    flex-direction: column; 
    justify-content: center; 
    align-items: center; 
    border-radius: 5px;
    margin-bottom: 20px;
    gap: 5px 0px;
    cursor: pointer;
}
.aside-component{
    position: fixed;
    width: 120px;
    height: 300px;
    top:calc(50vh - 172px);
    box-sizing: border-box;
    padding: 20px;
}
.aside1{
    background-color: #e0e0e0;
}
.aside2{
    background-color: black;
}
</style>