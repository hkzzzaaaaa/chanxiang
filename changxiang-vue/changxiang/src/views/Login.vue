<script setup lang="ts">
import {ref} from 'vue'
import { ElForm,ElMessage} from 'element-plus'
import { useRouter } from 'vue-router'
import {login_password,login_code,get_captcha} from "@/api/login"
import { useTokenStore } from '@/stores/token'
const tokenStore=useTokenStore();
const loading = ref(false);
const router=useRouter();
const count = ref(0) 
const isCaptchaDisabled = ref(false) 
const ifpassword=ref(false)
const formRef = ref<InstanceType<typeof ElForm>|null>(null)
const form=ref({
    email:'',
    password:'',
    captcha:'',
    agree:false
})
const formrules={
    email: [
        { required: true, message: '请输入邮箱', trigger: ['blur', 'input'] },
        { 
            pattern: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/, 
            message: '请输入正确的邮箱格式', 
            trigger: ['blur', 'input'] 
        }
    ],
  password: [
    { required: true, message: '请输入密码', trigger: ['blur', 'input'] },
    { min: 6, message: '密码长度不能少于6位', trigger: ['blur', 'input']},
    { max: 30, message: '密码长度不能超过30位', trigger: ['blur', 'input']}
  ],
   captcha: [
    { required: true, message: '请输入验证码', trigger: ['blur', 'input'] },
    { pattern: /^\d{4}$/, message: '请输入4位数字验证码', trigger: ['blur', 'input'] },
  ],
    agree: [
        { required: true, message: '请阅读并同意用户协议和隐私政策', trigger: ['blur', 'change'] }
    ]
}
const getCaptcha =async () => {
  const phoneRule = formrules.email
  let isPhoneValid = true
  for (const rule of phoneRule) {
    if (rule.required && !form.value.email.trim()) {
      ElMessage.error(rule.message);
      isPhoneValid = false
      break
    }
    if (rule.pattern && !rule.pattern.test(form.value.email)) {
      ElMessage.error(rule.message);
      isPhoneValid = false
      break
    }
  }
  if (!isPhoneValid) return
  count.value = 60
  isCaptchaDisabled.value = true 
  const timer = setInterval(() => {
    count.value--
    if (count.value <= 0) {
      clearInterval(timer)
      isCaptchaDisabled.value = false
    }
  }, 1000)
  let result=await get_captcha(form.value.email);
  if(result.data.code===0){
    ElMessage.success("短信验证码已经成功发送");
  }
  else{
    ElMessage.error(result.data.message);
  }
}

const login = async function() {
    if (loading.value) return;    
    const formInstance = formRef.value;
    if (!formInstance) {
        ElMessage.error('表单初始化失败，请刷新页面重试');
        return;
    }
        loading.value = true;
        const valid = await new Promise((resolve) => {
            formInstance.validate((valid) => resolve(valid));
        });

        if (!valid) {
            loading.value = false; 
            return;
        }

        if (!form.value.agree) {
            ElMessage.warning('请阅读并同意用户协议和隐私政策后重试');
            loading.value = false; 
            return;
        }

        if (!ifpassword.value) {
            console.log(form.value.captcha);
            const result=await login_code(form.value.email,form.value.captcha);
            if(result.data.code===0){
                ElMessage.success('登录成功！');
                loading.value = false; 
                tokenStore.setToken(result.data.data);
                router.push('/');
            }
           else{
                ElMessage.error(result.data.message);
                loading.value = false; 
           }
        } else {
            const result=await login_password(form.value.email,form.value.password); 
            if(result.data.code===0){
                loading.value = false; 
                ElMessage.success('登录成功！');
                tokenStore.setToken(result.data.data);
                router.push('/');
            }
            else{
                loading.value = false; 
                ElMessage.error(result.data.message);
            }

        }
};

</script>
<template>
<div class="login-main">
    <div class="login-container">
        <div class="img-container">
            <img src="../assets/logo.png" style="height: 500px; width: 500px;">
        </div>
        <div class="login-layout">
            <div class="holder"></div>
            <RouterLink to="/" class="login-text" style="margin-left: 15px; font-size: 20px;">首页</RouterLink>
            <div style="display: flex; gap: 50px; margin-top: 20px;">
            <el-button v-if="ifpassword===false" text="true" style=" color: black;font-size: 18px;">验证码登录</el-button>
            <el-button v-if="ifpassword===false" @click="ifpassword=true" text="true" style="font-size: 18px;">密码登录</el-button>
            <el-button v-if="ifpassword===true" @click="ifpassword=false" text="true" style="font-size: 18px;">验证码登录</el-button>
            <el-button v-if="ifpassword===true" text="true" style="color: black;font-size: 18px;">密码登录</el-button>
            </div>
            <br></br>
            <br></br>
            <el-form class="formstyle" :rules="formrules" :model="form"  ref="formRef" >
                <el-form-item prop="email">
                <el-input class="input" placeholder="请输入邮箱" maxlength="30" v-model="form.email"/>
                </el-form-item>
                <br></br>
                 <el-form-item v-if="ifpassword===false" prop="captcha"> 
                    <div class="captcha">
                        <el-input 
                            class="input" 
                            placeholder="验证码" 
                            maxlength="4" 
                            v-model="form.captcha"
                            style="flex: 1;"
                        />
                        <el-button 
                            type="default" 
                            style="width: 120px; height: 40px; border-radius: 10px;"
                             :disabled="isCaptchaDisabled"
                             @click="getCaptcha"
                        >
                             {{count > 0 ? `${count}秒后重新获取` : '获取验证码' }}
                        </el-button>
                    </div>
                </el-form-item>
                 <el-form-item v-if="ifpassword===true" prop="password">
                    <el-input class="input" placeholder="请输入密码" maxlength="30" v-model="form.password"/>
                </el-form-item>
                <br></br>
                <el-form-item prop="agree" class="agree-item">
                    <div class="agree-wrapper">
                        <el-checkbox v-model="form.agree" style="margin-right: 8px;"></el-checkbox>
                        <div class="agree-text">
                            我已阅读并同意<a href="#" style="color: #1890ff;">用户协议</a>和<a href="#" style="color: #1890ff;">隐私政策</a>
                        </div>
                    </div>
                </el-form-item>
                <el-form-item>
                    <el-button style="margin-left: auto; margin-right: auto; width: 450px;" type="primary" @click="login" :disabled="loading">登录</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</div>
</template>

<style scoped>
.login-main{
    width: 1250px;
    height: 540px;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color:rgb(255, 255, 255);
}
.login-container{
    width: 1000px;
    height: 500px;
    background-color:rgb(0, 0, 0);
    border-radius: 10px;
    display: flex;
}
.img-container{
    width: 500px;
    height: 500px;
    max-width: 500px;
    max-height: 500px;
}
.login-layout{
    width: 500px;
    height:500px;
    background-color:azure;
}
.holder{
    width: 500px;
    height:50px;
}   
.formstyle{
    max-width: 500px;
}
.input{
    width: 470px;
    height: 40px;
    margin-left: auto;
    margin-right:auto;
    border-radius: 10px;
}
.el-form-item__error {
    padding-left: 15px;
    width: 470px;
    margin: 5px auto 0;
    text-align: left;
}
.captcha{
display: flex; gap: 10px; width: 470px; margin: 0 auto;
}
.agree-item{
    padding-left: 15px !important; 
    margin-top: -10px; 
}
.agree-wrapper{
    display: flex; 
    align-items: center; 
    cursor: pointer; 
}
.agree-text{
    font-size: 14px; 
    color: #333; 
}

.agree-item .el-checkbox__inner{
    width: 16px;
    height: 16px;
}
</style>