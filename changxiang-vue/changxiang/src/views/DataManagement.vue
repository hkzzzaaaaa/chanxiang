<script setup lang="ts">
import {
  Warning
} from '@element-plus/icons-vue'
import { ref } from 'vue';
const drawer=ref(false);
</script>
<template>
    <div class="main">
        <el-drawer 
        v-model="drawer" 
        title="切换作品" 
        :with-header="true">
            <div class="drawer-main">
                <div class="book-main" v-for="value in 3">
                    <img style="width: 42px;height: 56px;" src="../assets/1.jpg"></img>
                     <div class="book-name">
                        我不是戏神
                    </div>
                </div>
            </div>
        </el-drawer>
        <div class="main-top">
            <div style="width: 990px;height: 1px;"></div>
            <div class="main-top-title">当前作品</div>
            <div class="main-top-body">
                <div class="main-top-body-picture">
                    <img style="width: 42px;height: 56px;" src="../assets/1.jpg"></img>
                </div>
                <div class="main-top-body-text">
                    我不是戏神
                </div>
                <el-button class="button-style-1" round style="margin-right: 5px;" @click="drawer = true">切换作品</el-button>
            </div>
        </div>
        <div class="main-info">
            <div class="main-info-title">作品数据</div>
            <div class="form">
                <div class="table">
                    <div class="main-info-text">
                        <div>阅读人数</div>
                        <el-popover
                        width="400px"
                        title="每日有阅读行为的读者数量"
                        content="当阅读人数发生明显变化时请注意关注读者来源和作品质量数据明确是否有明显变化"
                        placement="top"
                    >
                        <template #reference>
                        <el-icon><Warning /></el-icon>
                        </template>
                    </el-popover>
                    </div>
                    <div class="main-info-data">--</div>
                    <div class="main-info-data">比前日&nbsp;--</div>           
                </div>
                <div class="table">
                    <div class="main-info-text">
                        <div>在读人数</div>
                        <el-popover
                        width="400px"
                        title="最近14天作品累积“读”用户数量"
                        content="近期作品的热度&读者活跃度与收入关联不大，不同作品不具备比较性，是读者选书参考的数据"
                        placement="top"
                    >
                        <template #reference>
                        <el-icon><Warning /></el-icon>
                        </template>
                    </el-popover>
                    </div>
                    <div class="main-info-data">--</div>
                    <div class="main-info-data">比前日&nbsp;--</div> 
                </div>
                <div class="table">
                    <div class="main-info-text">
                        <div>加书架人数</div>
                        <el-popover
                        width="400px"
                        title="将作品添加进入书架的读者数量（未扣除当日用户移除书架数）"
                        content="加书架人数增加要提高更新频率，能提高读者的阅读概率，由于老读者追新书和新书预热功能可能带来加书架读者，因此加书架数可能高于作品阅读数"
                        placement="top"
                    >
                        <template #reference>
                        <el-icon><Warning /></el-icon>
                        </template>
                    </el-popover>
                    </div>
                    <div class="main-info-data">--</div>
                    <div class="main-info-data">比前日&nbsp;--</div> 
                </div>
                <div class="table">
                    <div class="main-info-text">
                        <div>订阅人数</div>
                        <el-popover
                        width="400px"
                        title="每日有订阅行为的读者数量"
                        content="当订阅人数发生明显变化时请注意关注读者来源和作品质量数据明确是否有明显变化"
                        placement="top"
                    >
                        <template #reference>
                        <el-icon><Warning /></el-icon>
                        </template>
                    </el-popover>
                    </div>
                    <div class="main-info-data">--</div>
                    <div class="main-info-data">比前日&nbsp;--</div> 
                </div>
                <div class="table"><div class="main-info-text">
                        <div>订阅收益</div>
                        <el-popover
                        width="400px"
                        title="每日订阅所获得的分成收益"
                        content="反映当日你的作品所获得的分成收益"
                        placement="top"
                    >
                        <template #reference>
                        <el-icon><Warning /></el-icon>
                        </template>
                    </el-popover>
                    </div>
                    <div class="main-info-data">--</div>
                    <div class="main-info-data">比前日&nbsp;--</div> 
                </div>
                <div class="table">
                    <div class="main-info-text">
                        <div>打赏收益</div>
                        <el-popover
                        width="400px"
                        title="每日所获得的打赏收益"
                        content="反映当日你的作品所获得得打赏收益"
                        placement="top"
                    >
                        <template #reference>
                        <el-icon><Warning /></el-icon>
                        </template>
                    </el-popover>
                    </div>
                    <div class="main-info-data">--</div>
                    <div class="main-info-data">比前日&nbsp;--</div> 
                </div>
                <div class="table"><div class="main-info-text">
                        <div>总收益</div>
                        <el-popover
                        width="400px"
                        title="当前月份所获得得总收益"
                        content="当前月份订阅+礼物所获得得总收益"
                        placement="top"
                    >
                        <template #reference>
                        <el-icon><Warning /></el-icon>
                        </template>
                    </el-popover>
                    </div>
                    <div class="main-info-data">--</div>
                    <div class="main-info-data">比前日&nbsp;--</div> 
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped>
.book-name{
    font-size: 14px;
    width: 150px;
    margin-left: 10px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow:ellipsis;
}
.book-main:hover{
    cursor: pointer;
    background-color: #f0f2f5;
}
.book-main{
    display: flex;
    width: 300px;
    height: 56px;
    margin-left: 10px;
    margin-bottom: 20px;
    background-color: #fafafa;;
}
.drawer-main{
    width: 340px;
}
.main-info-data{
   margin-left: 20px;
   margin-bottom: 20px;
}
.main-info-text{
   display: flex;
   align-items: center;
   margin-left: 20px;
   margin-top: 20px;
   margin-bottom: 20px;
   gap: 5px;
}
.table{
    width: 100%;
    height: 100%;
    background-color:  #fafafa;
}
.table:hover{
     background-color: #f0f2f5;
}
.form{
    display:grid;
    grid-template-columns:220px 220px 220px 220px;
    grid-template-rows: 150px 150px;
    gap: 20px;
}
.main-info-title{
    font-size: 18px;
    margin-bottom: 30px;
}
.main-info{
    margin-left: 30px;
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
.main-top-body-text{
    width: 300px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    font-size: 16px;
    margin-right: 450px;
}
.main-top-body-picture{
    margin-right: 25px;
}
.main-top-body{
    margin-left: 30px;
    display: flex;
}
.main-top-title{
    margin-left: 30px;
    margin-top: 20px;
    margin-bottom: 20px;
    font-size: 18px;
}
.main-top{
    width: 990px;
    height: 180px;
}
.main{
    width: 990px;
    height: 600px;
    background-color:rgb(255, 255, 255);
}
</style>