<script lang="ts" setup>
import {Search} from '@element-plus/icons-vue';
import { useTokenStore } from '@/stores/token';
import router from '@/router';
const tokenStore=useTokenStore();
const Exit=function(){
    tokenStore.removeToken();
    router.push('/');
}
const toInformation=function(){
    router.push('/information')
}
</script>
<template>
  <div class="main-container">
    <div class="top-navigation-bar">
        <div class="item-logo">
            <img class="logo" src="../assets/logo.png" alt="logo">
            <el-text class="logo-text">畅想阅读</el-text>
        </div>
        <div class="top-navigation">
            <div class="item">
                <RouterLink to="/" class="default-text">首页</RouterLink>
                <div class="nav-item"></div>
            </div>
            <div class="item">
                <!-- 等待实现 -->
                <RouterLink to="/stack" class="default-text">书库</RouterLink>
                <div class="nav-item"></div>
            </div>
            <div class="item">
                <!-- 等待实现 -->
                <RouterLink to="/" class="default-text">书架</RouterLink>
                <div class="nav-item"></div>
            </div>
            <div class="item">
                <!-- 等待实现 -->
                <RouterLink to="/" class="default-text">原创榜</RouterLink>
                <div class="nav-item"></div>
            </div>
            <div class="item">
                <!-- 等待实现 -->
                <RouterLink to="/author" class="default-text">作家专区</RouterLink>
                <div class="nav-item"></div>
            </div>
            <div class="item-search">
                <!-- 等待实现 -->
                <input class="search" placeholder="请输入书名或者作者名"></input>
                <el-button :icon="Search" circle  size="default"/>       
            </div>
            <!-- 等待实现 -->
             <div class="item" v-if="tokenStore.token===''">
                <RouterLink to="/Login" class="login-text">登录</RouterLink>
            </div>
            <!-- 等待实现 -->
            <div class="item" v-if="tokenStore.token===''">
                <RouterLink to="/Login" class="login-text">注册</RouterLink>
            </div>
            <div class="item" style="margin-right: 20px;" v-if="tokenStore.token!==''">
                <el-dropdown>
                    <el-avatar :size="50" src='https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'/>
                    <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item @click="toInformation">个人资料</el-dropdown-item>
                        <el-dropdown-item>账号充值</el-dropdown-item>
                        <el-dropdown-item @click="Exit">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </div>
        </div>
    </div>
    <div class="nav-placeholder">
    </div>
    <RouterView></RouterView>
  </div>
</template>
<style scoped>
.main-container{
width: 1250px;
margin: auto;
background-color: rgb(255, 255, 255);
}
.top-navigation-bar{
    position: fixed;
    height: 60px;
    width: 1230px;
    background-color:azure;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px;
    top: 0;
    z-index: 999;
}
.top-navigation{
    display: flex;
    width: 800px;
    height: 30px;
    justify-content: space-around;
    align-items: center;
}
</style>