<script setup lang="ts">
import {ArrowLeft} from '@element-plus/icons-vue'
import { ref,watch } from 'vue';
import { useRouter } from 'vue-router';
const router=useRouter();
const getWorkManagement=function(){
    router.push('/author/workManagement')
}
const issign=ref(false);
const dialogVisible = ref(false)
import { onMounted } from 'vue';
import { useRoute } from 'vue-router';
const route = useRoute();
onMounted(() => {
  const type = route.params.issign;
  if(type==='settings'){
    issign.value=false;
  }
  else{
    issign.value=true;
  }
});
const ifupdate=ref(false);
//这是修改页面
import { genFileId, ElMessage } from 'element-plus'
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'
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

</script>
<template>
<div class="main" v-if="ifupdate===false">
    <div class="top">
        <div style="width: 990px;height: 1px;"></div>
        <div class="top-book" @click="getWorkManagement">
            <el-icon style="margin-top: 3px;margin-right: 5px;"><ArrowLeft /></el-icon>
            <div>作品名字</div>
        </div>
        <div class="top-book-info">
            <div :class="{'top-book-bar': !issign,
                'top-book-sign': issign
            }" style="margin-right: 40px;" @click="issign=false">作品信息</div>
            <div :class="{'top-book-sign': !issign,
                'top-book-bar': issign
            }" @click="issign=true">签约管理</div>
        </div>
    </div>
    <div class="main-container" v-if="issign===false">
        <div class="basic-info">
            <div class="basic-top">
                <div class="basic-top-bar">基础信息</div>
                <el-button class="button-style-1" round>删除</el-button>
                <el-button class="button-style-1" round>申请完结</el-button>
                <el-button class="button-style-2" round @click="ifupdate=true">修改</el-button>
            </div>
            <div class="basic-main">
                <div class="default-text">
                    <div class="default">书籍封面</div>
                    <img style="width: 80px;height: 107px;" src="../assets/1.jpg"></img>
                </div>
                <div class="default-text">
                    <div class="default">书本名称</div>
                    <div class="text-max">我不是戏神</div>
                </div>
                <div class="default-text">
                    <div class="default">目标读者</div>
                    <div class="text-max">男频</div>
                </div>
                <div class="default-text">
                    <div class="default">标签</div>
                    <div class="text-max" style="margin-left: 30px;">都市高武</div>
                </div>
                <div class="default-text">
                    <div class="default">作品简介</div>
                    <div class="book-introduction">
                        新作品出炉，欢迎大家前往番茄小说阅读我的作品，希望大家能够喜欢，你们的关注是我写作的动力，我会努力讲好每个故事！新作品出炉，欢迎大家前往番茄小说阅读我的作品，希望大家能够喜欢，你们的关注是我写作的动力，我会努力讲好每个故事！新作品出炉，欢迎大家前往番茄小说阅读我的作品，希望大家能够喜欢，你们的关注是我写作的动力，我会努力讲好每个故事！</div>
                </div>
            </div>
        </div>
        <div class="item-info">
            <div class="item-top">其他信息</div>
            <div class="default-item-text">
                <div class="default" style="margin-right: 48px;">书号</div>
                <div class="text-max">1423654748579835192</div>
            </div>
            <div class="default-item-text">
                <div class="default" >签约状态</div>
                <div class="text-max">已签约</div>
            </div>
            <div class="default-item-text">
                <div class="default" >创建时间</div>
                <div class="text-max">2025-01-09 16:30</div>
            </div>
            <div class="default-item-text">
                <div class="default" >更新状态</div>
                <div class="text-max">连载中</div>
            </div>
        </div>
    </div>
    <div class="sign" v-if="issign===true">
        <div class="sign-info">
            <div style="margin-bottom: 30px;display: flex;">
                <!-- 如果满足条件会出现签约按钮 -->
                <div style="margin-right: 700px;">签约说明</div> 
                <el-button class="button-style-2" round @click="dialogVisible=true">签约</el-button>
            </div>
            <div class="sign-text">1.作品字数达到2万字或被编辑提签后，将获得申请签约资格；详见《签约问题全解》</div>
            <div class="sign-text">2.签约作品可被用户在畅想阅读平台查看，在一定字数后获得全平台推荐，并享有订阅，打赏分成等各种收益，详见《畅想阅读作家福利》</div>
        </div>
        <el-dialog
            v-model="dialogVisible"
            title="Tips"
            width="500"
        >
            <span>是否对该作品进行签约，签约之后作品将上架，享受作品收益</span>
            <template #footer>
            <div class="dialog-footer">
                <el-button type="primary" @click="dialogVisible = false">签约</el-button>
                <el-button  @click="dialogVisible = false">
                取消
                </el-button>
            </div>
            </template>
        </el-dialog>
    </div>
</div>



<div class="create-main" v-if="ifupdate===true">
    <div class="create-top-text" @click="ifupdate=false">
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
                <el-button style="width: 100px;" @click="ifupdate=false">取消</el-button>
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
.sign-text{
    margin-left: 5px;
    margin-bottom: 20px;
    font-size: 14px;
}
.sign-info{
    margin-left: 58px;
}
.sign{
    width: 1000px;
}
.default-item-text{
    display: flex;
    margin-left: 58px;
    margin-bottom: 20px;
    font-size: 14px;
}
.item-top{
    margin-left: 58px;
    margin-bottom: 30px;
}
.item-info{
    width: 1000px;
    height: 200px;
}
.book-introduction{
    width: 830px;
}
.text-max{
    width: 300px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
.default{
    margin-right: 20px;
    color: #606266;
}
.default-text{
    display: flex;
    margin-bottom: 20px;
}
.basic-main{
    margin-top: 30px;
    margin-left: 58px;
    font-size: 14px;
}
.basic-top-bar{
    font-size: 16px;
    margin-left: 58px;
    margin-right: 530px;
}
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
.basic-top{
    width: 1000px;
    display: flex;

}
.basic-info{
    width: 1000px;
    height: 400px;
    margin-bottom: 60px;
}
.top-book-sign{
 cursor: pointer;
 color:darkgray;
}
.top-book-bar{
cursor: pointer;
}
.top-book-info{
    display: flex;
    margin-left: 58px;
    font-size: 17px;
}
.top-book:hover{
    color: coral;
    cursor: pointer;
}
.top-book{
    margin-top: 35px;
    margin-left: 60px;
    margin-bottom: 50px;
    display: flex;
    font-size: 21px;
}
.top{
    width: 990px;
    height: 200px;
}
.main{
    width: 990px;
    background-color: rgb(255, 255, 255);
}

/* 修改页面 */
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