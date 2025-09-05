<script setup lang="ts">
import { ArrowLeft, ArrowRight } from '@element-plus/icons-vue'
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
const router = useRouter();
const getWorkManagement = () => {
  router.push('/author/workManagement');
};

const paragraphs = ref<string[]>(['']); 
const chapter = ref<number | null>(null);
const title = ref<string | null>(null);


const wordCount = computed(() => {
  const allContent = paragraphs.value.join('');
  const filteredContent = allContent.replace(/\s+/g, '');
  return filteredContent.length;
});

const handleParagraphInput = (index: number, e: Event) => {
  const target = e.target as HTMLParagraphElement;
  paragraphs.value[index] = target.innerText;
};

const handlePaste = (index: number, e: ClipboardEvent) => {
  e.preventDefault();   
  const pastedText = e.clipboardData?.getData('text/plain') || '';
  if (!pastedText) return;
  const pastedParagraphs = pastedText.split('\n').filter(para => para.trim() !== '');
  if (pastedParagraphs.length > 0) {
    paragraphs.value.splice(index, 1, ...pastedParagraphs);
    setTimeout(() => {
      const paragraphList = document.querySelectorAll('.editable-paragraph') as NodeListOf<HTMLParagraphElement>;
      const lastPastedIndex = index + pastedParagraphs.length - 1;
      const lastParagraph = paragraphList[lastPastedIndex];
      if (lastParagraph) {
        lastParagraph.focus();
        const range = document.createRange();
        const selection = window.getSelection();
        if (range && selection) {
          range.selectNodeContents(lastParagraph);
          range.collapse(false);
          selection.removeAllRanges();
          selection.addRange(range);
        }
      }
    }, 0);
  }
};
const handleEnter = (index: number, e: KeyboardEvent) => {
  if (e.key === 'Enter' && !e.shiftKey && !e.ctrlKey) {
    e.preventDefault();
    
    if (paragraphs.value[index].trim() === '' && index !== paragraphs.value.length - 1) {
      return;
    }
    
    paragraphs.value.splice(index + 1, 0, '');
    
    setTimeout(() => {
      const newParagraph = document.querySelector(`.editable-paragraph:nth-child(${index + 2})`) as HTMLParagraphElement | null;
      if (newParagraph) {
        newParagraph.focus();
        const range = document.createRange();
        const selection = window.getSelection();
        if (range && selection) {
          range.selectNodeContents(newParagraph);
          range.collapse(true);
          selection.removeAllRanges();
          selection.addRange(range);
        }
      }
    }, 0);
  }
};

const handleBackspace = (index: number, e: KeyboardEvent) => {
  if (e.key === 'Backspace' && paragraphs.value[index].trim() === '' && paragraphs.value.length > 1) {
    e.preventDefault();
    paragraphs.value.splice(index, 1);
    const prevParagraph = document.querySelector(`.editable-paragraph:nth-child(${index})`) as HTMLParagraphElement | null;
    prevParagraph?.focus();
  }
};

const submitToBackend = async () => {
  if (!chapter.value || !title.value || paragraphs.value.every(p => p.trim() === '')) {
    ElMessage.warning('请完善章节号、标题和正文内容');
    return;
  }

  const submitData = {
    chapter: chapter.value,
    title: title.value,
    content: paragraphs.value.join('\n\n'),
  };

  try {
    console.log('提交给后端的数据：', submitData);
  } catch (error) {
    console.error('提交失败：', error);
  }
};

onMounted(() => {
  const firstParagraph = document.querySelector('.editable-paragraph') as HTMLParagraphElement | null;
  firstParagraph?.focus();
});
</script>

<template>
  <div class="main">
    <div class="top">
      <div class="icon" @click="getWorkManagement">
        <el-icon><ArrowLeft /></el-icon>
      </div>
      <div class="top-info">
        <div class="top-text">我不是戏神</div>
        <div class="top-count">正文字数&nbsp;{{ wordCount }}</div>
      </div>
      <div class="top-buttons">
        <el-button class="button-style-1" round>存草稿</el-button>
        <el-button class="button-style-2" round @click="submitToBackend">
          <el-icon style="margin-right: 5px;"><ArrowRight /></el-icon>
          下一步
        </el-button>
      </div>
    </div>

    <div class="main-container">
      <div class="main-container-title-top">
        <span class="main-container-title">第</span>
        <span class="main-container-title input-container">
          <input 
            type="text" 
            class="main-container-title-number" 
            v-model="chapter"
            @keydown.enter.prevent
          >
          <span class="input-underline"></span>
        </span>
        <span class="main-container-title">章</span>  
        <span class="main-container-title">
          <input 
            type="text" 
            class="main-container-title-text" 
            placeholder="请输入标题" 
            v-model="title"
            @keydown.enter.prevent
          >
        </span> 
      </div>

      <div class="paragraph-container">
        <p 
          class="editable-paragraph"
          v-for="(para, index) in paragraphs"
          :key="index"
          contenteditable="true"
          @input="handleParagraphInput(index, $event)"
          @keydown.enter="handleEnter(index, $event)"
          @keydown.backspace="handleBackspace(index, $event)"
          @paste="handlePaste(index, $event)"
        >
          {{ para }}
        </p>
      </div>
    </div>
  </div>
</template>

<style scoped>
.paragraph-container {
  width: 100%;
}
.editable-paragraph {
  width: 100%;
  min-height: 36px;
  margin: 0 0 16px 0;
  padding: 8px 10px;
  font-size: 16px;
  line-height: 1.8;
  color: #000;
  text-indent: 2em;
  outline: none;
  box-sizing: border-box;
  transition: border-color 0.2s;
}
.novel-textarea {
  display: none;
}
.novel-textarea::placeholder {
  color: #ccc; 
}
.main-container-title-top{
  margin-bottom: 40px;
  display: flex;
  gap: 10px;
  align-items: center;
}
.main-container-title-text{
  background: transparent;
  caret-color: #ff5f00;
  border: none;
  outline: none;
  font-size: 24px;
}
.main-container {
  align-items: flex-start; 
  font-size: 24px; 
  color: #333; 
  padding: 20px;
}
.main-container-title {
  line-height: 1.4;
}
.input-container {
  position: relative;
  width: 60px; 
  padding-bottom: 4px;
}
.main-container-title-number {
  width: 100%;
  height: 1.4rem; 
  padding: 0 2px;
  border: none;
  outline: none; 
  background: transparent;
  font-size: 24px; 
  text-align: center; 
  caret-color: #ff5f00;
}
.input-underline {
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 2px; 
  background-color: #333; 
}
.main {
  width: 100%;
  min-height: 100vh;
  background-color: #f5f5f5;
  overflow-x: hidden; 
}
.top {
  display: flex;
  align-items: center;
  width: 100%;
  height: 70px;
  padding: 0 20px; 
  background-color: #fff;
  box-sizing: border-box;
  box-shadow: 0 1px 2px rgba(0,0,0,0.05); 
}
.icon {
  cursor: pointer;
  margin-right: 20px; 
  width: 30px;
  height: 30px;
  border-radius: 5px;
  background-color: #f5f5f5;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.2s; 
}
.icon:hover {
  background-color: #e8e8e8;
}
.top-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-right: 20px;
  flex: 1; 
}
.top-text {
  font-size: 14px;
  font-weight: 500; 
  color: #333;
}
.top-count {
  font-size: 13px;
  color: #999;
  margin-top: 2px;
}
.top-buttons {
  display: flex;
  gap: 12px; 
}
.button-style-1 {
  width: 100px;
  height: 35px; 
  background-color: #fafafa;
  color: #606266;
  border-color: #e4e7ed; 
}
.button-style-1:hover {
  background-color: #f0f2f5;
  border-color: #dcdfe6;
}
.button-style-2 {
  width: 120px;
  height: 35px; 
  color: #ff5f00;
  background-color: #ff5f001f;
  border-color: #ff5f002a; 
}
.button-style-2:hover {
  background-color: #fff0e0;
  border-color: #ff5f003a;
}
.main-container {
  margin: 20px auto; 
  width: 100%;
  max-width: 800px; 
  min-height: calc(100vh - 90px); 
  background-color: #fff; 
  padding: 60px;
  box-sizing: border-box;
  border-radius: 8px; 
  box-shadow: 0 2px 10px rgba(0,0,0,0.03);
}
.editor {
  display: none;
}
.editor:focus {
  border-color: #ff5f00;
}
</style>
