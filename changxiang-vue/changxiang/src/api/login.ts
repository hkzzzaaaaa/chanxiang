import request from "@/utils/request"
export const login_password=function(email : string,password : string){
    return request.post("/auth/userlogin/PasswordLogin",
        {
            email,
            password
        }
    );
}
export const login_code=function(email:string,code:string){
    return request.post("/auth/userlogin/CaptchaLogin",
      {
        email,
        code
      }
    );
}
export const get_captcha = function(email:string){
  return request.post('/auth/userlogin/GetCaptcha', {}, {
    params: {
      email: email
    }
  });
};
