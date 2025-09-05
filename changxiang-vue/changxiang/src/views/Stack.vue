<script lang="ts" setup>
import { ref, computed, watch } from 'vue';
import { useRouter } from 'vue-router';
const router=useRouter();
const toContent=function(){
  router.push('Content')
}
const selectedCondition1 = ref('全部'); 
const handleSelect1 = (value: string) => {
  selectedCondition1.value = value;
};
const maleCategoryOptions = [
  { label: '玄幻', value: 'xuanhuan' },
  { label: '武侠', value: 'wuxia' },
  { label: '科幻', value: 'kehuan' },
  { label: '游戏', value: 'youxi' },
  { label: '历史', value: 'lishi' }
];

const femaleCategoryOptions = [ 
  { label: '言情', value: 'yanqing' },
  { label: '青春', value: 'qingchun' },
  { label: '悬疑', value: 'xuanyi' },
  { label: '都市', value: 'dushi' },
  { label: '仙侠', value: 'xianxia' }
];

const allCategoryOptions = [
  ...maleCategoryOptions,
  ...femaleCategoryOptions
];
const selectedCondition2 = ref('全部'); 
const handleSelect2 = (value: string) => {
  selectedCondition2.value = value;
  if (value === '全部') selectedCategory.value = '';
};
const filteredCategoryOptions = computed(() => {
  switch (selectedCondition1.value) {
    case '男生':
      return maleCategoryOptions; 
    case '女生':
      return femaleCategoryOptions; 
    case '全部':
      return allCategoryOptions; 
    default:
      return allCategoryOptions;
  }
});
const selectedCategory = ref('');
const handleCategorySelect = (value: string, label: string) => {
  selectedCategory.value = label;
};
watch(selectedCondition1, () => {
  selectedCategory.value = '';
});
const selectedCondition3 = ref('全部'); 
const handleSelect3 = (value: string) => {
  selectedCondition3.value = value;
};
const selectedCondition4 = ref('全部'); 
const handleSelect4 = (value: string) => {
  selectedCondition4.value = value;
};

const selectedCondition5 = ref('最热'); 
const handleSelect5 = (value: string) => {
  selectedCondition5.value = value;
};

import type { ComponentSize } from 'element-plus';
const currentPage3 = ref(1);
const pageSize3 = ref(100);
const size = ref<ComponentSize>('large')
const disabled = ref(false)
const background = ref(true)
const handleSizeChange = (val: number) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  console.log(`current page: ${val}`)
}
</script>

<template>
  <div style="width: 10px;height: 50px;"></div>
  <div class="main">
    <div class="top">
      <div class="selector-wrap">
        <div class="selector-label">读者:</div>
        <div class="condition-item"
             :class="{ 'active': selectedCondition1 === '全部' }" 
             @click="handleSelect1('全部')"
        >全部</div>
        <div class="condition-item"
             :class="{ 'active': selectedCondition1 === '男生' }" 
             @click="handleSelect1('男生')"
        >男生</div>
        <div class="condition-item"
             :class="{ 'active': selectedCondition1 === '女生' }" 
             @click="handleSelect1('女生')"
        >女生</div>
      </div>
      <div class="selector-group">
        <div class="selector-wrap">
          <div class="selector-label">分类:</div>
          <div class="condition-item"
               :class="{'active': selectedCondition2 === '全部'}"
               @click="handleSelect2('全部')"
          >全部</div>
          <div class="condition-item"
               :class="{'active': selectedCondition2 === '主题'}"
               @click="handleSelect2('主题')"
          >主题</div>
        </div>
        <div class="sub-selector-wrap" v-if="selectedCondition2 === '主题'">
          <div class="category-expansion">
            <div class="category-item"
                 :class="{'active': selectedCategory === item.label}"
                 @click="handleCategorySelect(item.value, item.label)"
                 v-for="item in filteredCategoryOptions"
                 :key="item.value"
            >
              {{ item.label }}
            </div>
          </div>
        </div>
      </div>

      <div class="selector-wrap">
        <div class="selector-label">状态:</div>
        <div class="condition-item"
             :class="{ 'active': selectedCondition3 === '全部' }" 
             @click="handleSelect3('全部')"
        >全部</div>
        <div class="condition-item"
             :class="{ 'active': selectedCondition3 === '已完结' }" 
             @click="handleSelect3('已完结')"
        >已完结</div>
        <div class="condition-item"
             :class="{ 'active': selectedCondition3 === '连载中' }" 
             @click="handleSelect3('连载中')"
        >连载中</div>
      </div>
      <div class="selector-wrap">
        <div class="selector-label">字数:</div>
        <div class="condition-item"
             :class="{ 'active': selectedCondition4 === '全部' }" 
             @click="handleSelect4('全部')"
        >全部</div>
        <div class="condition-item" style="width: 60px;"
             :class="{ 'active': selectedCondition4 === '30万以下' }" 
             @click="handleSelect4('30万以下')"
        >30万以下</div>
        <div class="condition-item" style="width: 60px;"
             :class="{ 'active': selectedCondition4 === '30-50万' }" 
             @click="handleSelect4('30-50万')"
        >30-50万</div>
        <div class="condition-item" style="width: 80px;"
             :class="{ 'active': selectedCondition4 === '50万-100万' }" 
             @click="handleSelect4('50万-100万')"
        >50万-100万</div>
        <div class="condition-item" style="width: 80px;"
             :class="{ 'active': selectedCondition4 === '100万以上' }" 
             @click="handleSelect4('100万以上')"
        >100万以上</div>
      </div>
    </div>
    <br></br>
    <br></br>
    <div class="main-top">
        <div class="main-top-item"
             :class="{ 'active': selectedCondition5 === '最热' }" 
             @click="handleSelect5('最热')"
        >最热</div>
        <div class="main-top-item" style="width: 60px;"
             :class="{ 'active': selectedCondition5 === '最新' }" 
             @click="handleSelect5('最新')"
        >最新</div>
        <div class="main-top-item" style="width: 60px;"
             :class="{ 'active': selectedCondition5 === '字数' }" 
             @click="handleSelect5('字数')"
        >字数</div>
    </div>
    <hr></hr>
     <div class="main-container">
      <div class="main-container-item" v-for="item in 18" :key="item">
        <div class="main-container-picture" @click="toContent">
          <img style="max-width: 115px; max-height: 150px;" src="../assets/1.jpg" alt="书籍封面">
        </div>
        <div class="main-container-book">
          <p class="book-title" @click="toContent">我不是戏神</p>
          <p class="book-author">作者：三音九域</p>
          <p class="book-author">连载中&nbsp;&nbsp;307.4万字</p>
          <p class="book-intro">
            这是一本讲述小人物逆袭的精彩小说，主角在困境中坚守初心，凭借智慧和勇气打破命运枷锁，剧情跌宕起伏，人物形象鲜明，值得一读。
          </p>
          <p class="book-author">2025-03-22&nbsp;17:00</p>
        </div>
      </div>
    </div>
    <div class="footer">
       <el-pagination
        v-model:current-page="currentPage3"
        v-model:page-size="pageSize3"
        :size="size"
        :disabled="disabled"
        :background="background"
        layout="prev, pager, next"
        :total="1000"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
    </div>
  </div>
</template>

<style scoped>
.footer {
  margin-top: 15px;
  padding: 15px 10px;
  background-color: #fff;
  border-radius: 4px;
  font-size: 14px;
  display: flex;
  justify-content: center; 
  height: 100px;
}
.book-title:hover{
  color: #ff6b35;
}
.book-title {
  cursor: pointer;
  font-size: 18px;
  font-weight: 500;
  max-width: 170px;
  color: #333;
  margin: 0;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis; 
}
.book-author {
  font-size: 13px;
  color: #666;
  margin: 0;
  line-height: 1.5;
}

.book-intro {
  font-size: 12px;
  color: #555;
  margin: 0;
  line-height: 1.5; 
  line-clamp: 3;
  display: -webkit-box;
  -webkit-line-clamp: 3;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}
.main-container-picture{
  cursor: pointer;
}
.main-container-item{
  width: 310px;
  height: 150px;
  display: flex;
  gap: 0px 10px;

}
.main-container{
  display: flex;
  flex-wrap: wrap;
  width: 1150px;
  gap: 80px;
}
.main-top{
    display: flex;
}
.main {
  width: 1150px;
  height: 450px;
  margin: auto;
  background-color: rgb(255, 255, 255);
  padding: 20px;
  box-sizing: border-box;
}
.top {
  display: grid;
  gap: 15px 0;
  width: 100%;
  height: auto;
  background-color: #fff;
  padding: 15px;
  box-sizing: border-box;
}
.selector-group {
  display: grid;
  gap: 8px 0; 
}
.selector-wrap {
  display: flex;
  align-items: center;
  gap: 0 20px;
  width: 100%;
  height: auto;
}
.sub-selector-wrap {
  display: flex;
  align-items: center;
  padding-left: 80px; 
  width: 100%;
  height: auto;
}

.selector-label {
  width: 60px;
  color: #333;
  font-size: 14px;
}
.condition-item:hover{
  color: #ff6b35;
}
.condition-item {
  text-align: center;
  width: 50px;
  padding: 4px 0;
  border-radius: 4px;
  color: #333;
  cursor: pointer;
  transition: all 0.2s ease;
  font-size: 14px;
}
.condition-item.active {
  color: #ff6b35;
  background-color: beige;
}
.main-top-item:hover{
  color: #ff6b35;
}
.main-top-item{
  text-align: center;
  width: 50px;
  padding: 4px 0;
  border-radius: 4px;
  color: #333;
  cursor: pointer;
  transition: all 0.2s ease;
  font-size: 14px;
}
.main-top-item.active{
  color: #ff6b35;
}
.category-expansion {
  display: flex;
  flex-wrap: wrap;
  gap: 12px 15px;
  width: 100%;
}
.category-item {
  padding: 4px 12px;
  border-radius: 4px;
  color: #333;
  cursor: pointer;
  transition: all 0.2s ease;
  font-size: 14px;
  white-space: nowrap;
}
.category-item.active {
  color: #ff6b35;
  background-color: beige;
}
.category-item:hover {
  background-color: #f5f5f5;
}
.main-top, .main-container, .footer {
  margin-top: 15px;
  padding: 10px;
  background-color: #fff;
  border-radius: 4px;
  font-size: 14px;
}
</style>