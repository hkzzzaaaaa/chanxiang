<script setup lang="ts">
import { ref } from 'vue';
import { ElMessage } from 'element-plus';
import { getInformationService,Base_Information_Service} from '@/api/Information';
import router from '@/router';
import type { update_base_form_type } from '@/Interface/information';
const count = ref(0) 
const basic_form=ref(
    {
        username:'',
        age: 0,
        gender:'',
        signial:'',
        genderFavourite:'',
        favourite:'',
        wordCount:'',
    }
)
const isCaptchaDisabled = ref(false) 
const getCaptcha =function(){
  count.value = 60
  isCaptchaDisabled.value = true 
  const timer = setInterval(() => {
    count.value--
    if (count.value <= 0) {
      clearInterval(timer)
      isCaptchaDisabled.value = false
    }
  }, 1000)
}
const getinformation=async function(){
    let result=await getInformationService();
    console.log(result.data);
    if(result.data.code===0){
        const userInfo = result.data.data;
        basic_form.value = {
                username: userInfo.username,
                age: userInfo.age, 
                gender: userInfo.gender,
                signial: userInfo.signial, 
                genderFavourite: userInfo.genderFavourite,
                favourite: userInfo.favourite,
                wordCount: userInfo.wordCount
            };
    }
    else{
        ElMessage.error("系统繁忙，请稍后重试");
        router.push('/')
    }
}
getinformation();
const update_base_information=async function(){
    if(basic_form.value.username===''){
        ElMessage.warning("用户名不能为空，请添加用户名后重试");
        return;
    }
    if(basic_form.value.age<0||basic_form.value.age>200){
        ElMessage.warning('年龄不能小于0或者大于200，请输入合法年龄');
        return;
    }
    const update_base_form=ref<update_base_form_type>({
        username:basic_form.value.username,
        age:basic_form.value.age,
        gender:basic_form.value.gender,
        signial:basic_form.value.signial
    })
    const result=await Base_Information_Service(update_base_form.value);
    if(result.data.code===0){
        ElMessage.success("修改成功");
    }
    else{
        ElMessage.error(result.data.message)
        getinformation();
    }
}
const update_style_information=async function(){
    
}
</script>
<template>
<div class="placeholder"></div>
<div class="main">
     <div class="base-information">
        <div class="form-title">基本信息</div>
        <el-form style="max-width: 400px;" label-width="auto" :model="basic_form">
            <el-form-item label="用户名">
                <el-input v-model="basic_form.username" maxlength="15"/>
            </el-form-item>
             <el-form-item label="年龄">
                <el-input v-model="basic_form.age" />
            </el-form-item>
            <el-form-item label="性别">
                <el-select placeholder="性别" v-model="basic_form.gender">
                    <el-option label="保密" value="保密" />
                    <el-option label="男" value="男" />
                    <el-option label="女" value="女" />
                </el-select>
            </el-form-item>
            <el-form-item label="个性签名" >
               <el-input maxlength="200" type="textarea"  show-word-limit rows="10" v-model="basic_form.signial"/>
            </el-form-item>
            <el-form-item>
                <div style="margin-left: 110px">
                <el-button type="primary" style="width: 100px;" @click="update_base_information">保存</el-button>
                <el-button style="width: 100px;" @click="getinformation">重置</el-button>
                </div>
            </el-form-item>
        </el-form>
     </div>
     <div class="style-information">
        <div class="form-title">个性定制</div>
        <el-form style="max-width: 400px;" label-width="auto" :model="basic_form">
            <el-form-item label="性别偏好">
                <el-select placeholder="性别偏好" v-model="basic_form.genderFavourite">
                    <el-option label="全部" value="全部" />
                    <el-option label="男频" value="男频" />
                    <el-option label="女频" value="女频" />
                </el-select>
            </el-form-item>
            <el-form-item label="爱好分类">
                <el-select placeholder="爱好分类" v-model="basic_form.favourite">
                    <el-option label="无" value="无" />
                    <el-option label="男频" value="1" />
                    <el-option label="女频" value="2" />
                </el-select>
            </el-form-item>
            <el-form-item label="字数偏好" v-model="basic_form.wordCount">
                <el-select placeholder="字数偏好">
                    <el-option label="无" value="无" />
                    <el-option label="30万字以下" value="30万字以下" />
                    <el-option label="30~50万字" value="30~50万字" />
                    <el-option label="50~100万字" value="50~100万字" />
                    <el-option label="100万以上" value="100万以上" />
                </el-select>
            </el-form-item>
             <el-form-item>
                <div style="margin-left: 110px">
                <el-button type="primary" style="width: 100px;">保存</el-button>
                <el-button style="width: 100px;"  @click="getinformation">取消</el-button>
                </div>
            </el-form-item>
        </el-form>
        <div class="form-title">密码修改</div>
        <el-form style="max-width: 400px;" label-width="auto">
            <el-form-item> 
                <el-input placeholder="请输入需要修改的密码" maxlength="30"/>
            </el-form-item>
             <el-form-item> 
                <el-input placeholder="请再次输入需要修改的密码" maxlength="30"/>
            </el-form-item>
            <el-form-item> 
                <el-input placeholder="请输入邮箱验证码" maxlength="4" style="width: 250px;margin-right: 30px;"/>
                    <el-button 
                        type="default" 
                        style="width: 120px; height: 30px; border-radius: 10px;"
                        :disabled="isCaptchaDisabled"
                        @click="getCaptcha"
                        >
                        {{count > 0 ? `${count}秒后重新获取` : '获取验证码' }}
                    </el-button>
            </el-form-item>
             <el-form-item>
                <div style="margin-left: 110px">
                <el-button type="primary" style="width: 100px;">保存</el-button>
                <el-button style="width: 100px;">取消</el-button>
                </div>
            </el-form-item>
        </el-form>
     </div>
</div>
</template>
<style scoped>
.form-title{
    font-size: 18px;
    margin-bottom: 20px;
}
.main{
    display: grid;
    box-sizing: border-box;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    padding: 50px;
    width: 1250px;
    background-color: rgb(255, 255, 255);
}
.placeholder{
    width: 1250px;
    height: 75px;
}
.base-information{
    background-color: antiquewhite;
    box-sizing: border-box;
    padding: 40px;
}
.style-information{
    background-color: antiquewhite;
    box-sizing: border-box;
    padding: 40px;
}
</style>