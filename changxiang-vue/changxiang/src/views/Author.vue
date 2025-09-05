<template>
  <div class="app-container">
    <div style="width: 1250px;height: 72px;"></div>
    <el-container class="container">
      <el-aside width="200px" class="aside">
        <el-menu
          :default-active=currentRoute
          class="el-menu-vertical-demo menu"
          router
        >
          <el-menu-item index="/author/work">
            <el-icon><monitor /></el-icon>
            <span>工作台</span>
          </el-menu-item>
          <el-menu-item index="/author/workManagement">
            <el-icon><memo/></el-icon>
            <span>作品管理</span>
          </el-menu-item>
           <el-menu-item index="/author/dataManagement">
            <el-icon><DataLine /></el-icon>
            <span>数据管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <RouterView></RouterView>
      </el-main>
    </el-container>
  </div>
</template>

<script setup lang="ts">
import {
  Monitor,
  Memo,
  DataLine
} from '@element-plus/icons-vue'
import { useRoute } from 'vue-router';
import { useRouter } from 'vue-router';
import { computed } from 'vue';
const router =useRouter();
const route = useRoute()
const currentRoute = computed(() => route.path);
router.push('/author/work');
</script>

<style scoped>
.app-container {
  height: 75vh;
}

.container {
  height: 100%;
}

.aside {
  height: 100%;
  background-color: #f5f7fa; 
}

.menu {
  height: 100%;
  border-right: none; 
}

.el-main {
  background-color: #f5f7fa;
}
</style>
