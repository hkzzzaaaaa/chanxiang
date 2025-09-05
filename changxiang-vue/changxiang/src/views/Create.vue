<script setup lang="ts">
import {ArrowLeft} from '@element-plus/icons-vue'
import { ref,watch} from 'vue'
import { genFileId, ElMessage } from 'element-plus'
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'
import router from '@/router'
const upload = ref<UploadInstance>() // 上传组件实例（用于手动控制上传）
const previewUrl = ref('') // 预览图片的URL（选图后赋值）
const isUploading = ref(false) // 上传状态（避免重复提交）
// 2. 限制单文件：超出数量时自动替换旧文件
const handleExceed: UploadProps['onExceed'] = (files) => {
  upload.value!.clearFiles() // 清空已有文件
  const file = files[0] as UploadRawFile
  file.uid = genFileId() // 生成文件唯一ID（组件必需）
  upload.value!.handleStart(file) // 加入新文件
  if (file) previewUrl.value = URL.createObjectURL(file) // 更新预览
}
// 3. 选图后生成预览（组件自带事件，选文件时触发）
const handleFileChange = (file: any) => {
  if (file.raw) {
    previewUrl.value = URL.createObjectURL(file.raw) // 将文件转为本地预览URL
  }
}
// 4. 手动触发上传（点击“上传到服务器”按钮时调用）
const submitUpload = async () => {
  if (!previewUrl.value) {
    ElMessage.warning('请先选择图片')
    return
  }
  if (isUploading.value || !upload.value) return
  isUploading.value = true
  try {
    await upload.value.submit() // 调用组件上传方法，发送到action接口
    ElMessage.success('图片上传成功')
  } catch (error) {
    ElMessage.error('上传失败，请重试')
    console.error('上传错误:', error)
  } finally {
    isUploading.value = false
  }
}
// 5. 上传成功后处理（可选：如保存后端返回的图片URL）
const handleUploadSuccess = (response: any) => {
  console.log('后端返回的图片地址:', response.url) // 可存入表单或数据库
}
const bookform=ref({
   bookName: undefined as string|undefined,
   targetReader: undefined as number | undefined, 
   bookTag: undefined as string|undefined,
   bookDescription: undefined as string|undefined,
})
const dynamicTags = ref<{ label: string; value: string }[]>([])
const tagOptions = {
  female: [
    { label: '言情', value: 'yanqing' },
    { label: '青春', value: 'qingchun' },
    { label: '悬疑', value: 'xuanyi' }
  ],
  male: [ 
    { label: '玄幻', value: 'xuanhuan' },
    { label: '武侠', value: 'wuxia' },
    { label: '科幻', value: 'kehuan' }
  ]
}
watch(
  () => bookform.value.targetReader,
  (newReader) => {
    if (newReader === undefined) {
      dynamicTags.value = []
      bookform.value.bookTag = undefined
      return
    }
    bookform.value.bookTag = ''
    dynamicTags.value = newReader === 0 ? tagOptions.female : tagOptions.male
  },{ immediate: true }
)

const getWorkManagement=function(){
  router.push('/author/workManagement')
}
</script>
<template>
<div class="create-main">
    <div class="create-top-text" @click="getWorkManagement">
        <el-icon><ArrowLeft /></el-icon>
        <div>创建新书</div>
    </div>
    <div class="create-main-container">
        <div class="image-upload-wrapper">
            <el-upload
                ref="upload"
                class="single-image-upload"
                action="https://your-backend-api.com/upload" 
                :limit="1" 
                :on-exceed="handleExceed" 
                :auto-upload="false" 
                :on-change="handleFileChange" 
                :on-success="handleUploadSuccess" 
                accept="image/*" 
            >
                <div class="upload-content">
                <div class="preview-container">
                    <img 
                    v-if="previewUrl" 
                    :src="previewUrl" 
                    alt="图片预览" 
                    class="preview-img"
                    >
                    <div v-else class="placeholder">
                    <img 
                    v-if="!previewUrl" 
                    src="../assets/book-picture.png"
                    alt="图片预览" 
                    class="preview-img"
                    >
                    </div>
                </div>
                <el-button 
                    type="primary" 
                    class="select-btn"
                    :disabled="isUploading"
                >
                    选择图片
                </el-button>
                </div>
            </el-upload>
            </div>
        <div class="create-main-container-information">
             <el-form label-width="auto" style="max-width: 800px" size="large" :model="bookform">
                  <el-form-item label="书本名称" prop="bookName">
                    <el-input
                      v-model="bookform.bookName"
                      maxlength="15"  
                      show-word-limit  
                    />
                  </el-form-item>
                <el-form-item label="目标读者" prop="targetReader">
                    <el-radio-group v-model="bookform.targetReader">
                        <el-radio :value=0>女生</el-radio>
                        <el-radio :value=1>男生</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="作评标签" prop="bookTag">
                <el-select placeholder="请选择作品标签"
                  v-model="bookform.bookTag"
                  :disabled="bookform.targetReader === undefined"
                >
                   <el-option 
                    v-for="tag in dynamicTags" 
                    :key="tag.value" 
                    :label="tag.label" 
                    :value="tag.value" 
                  />
                </el-select>
                </el-form-item>
                <el-form-item label="作品简介" prop="bookDescription">
                    <el-input type="textarea"
                    v-model="bookform.bookDescription"
                    maxlength="500"  
                    show-word-limit  
                />
                </el-form-item>
                <el-form-item style="margin-left: 580px;">
                <el-button style="width: 100px;" @click="getWorkManagement">取消</el-button>
                 <!-- 3. 手动上传按钮（可选：如需单独的上传触发） -->
                <el-button
                    type="success"
                    @click="submitUpload"
                    :disabled="!previewUrl || isUploading"
                    :loading="isUploading"
                >
                    {{ isUploading ? '上传中...' : '立刻创建' }}
                </el-button>        
                </el-form-item>
             </el-form>
        </div>
    </div>
</div>
</template>
<style scoped>
.create-main-container-information{
    margin-top: 10px;
    margin-left: 20px;
    width: 870px;   
}
.image-upload-wrapper {
  width: 120px;
}
.upload-content {
  display: flex;
  flex-direction: column; 
  align-items: center;
  gap: 12px; 
}

.preview-container {
  width: 120px; 
  height: 160px; 
  border: 1px dashed #ccc; 
  display: flex;
  align-items: center;
  justify-content: center; 
  overflow: hidden;
}

.preview-img {
  width: 120px;
  height: 160px;
}
.placeholder {
  color: #999;
  font-size: 12px;
}

.select-btn {
  width: 120px; 
}

.create-main-container{
    display: flex;
}
.create-top-text{
    display: flex;
    align-items: center;
    font-size: 20px;
    cursor: pointer;
    margin-bottom: 40px;
}
.create-main{
    width: 950px;
    background-color: rgb(255, 255, 255);
    padding: 20px;
}
</style>