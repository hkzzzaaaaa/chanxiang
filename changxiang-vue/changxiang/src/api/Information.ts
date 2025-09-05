import request from "@/utils/request"
import type { update_base_form_type } from "@/Interface/information";
export const getInformationService=function(){
    return request.get('/user/userservice/GetInformation');
}
export const Base_Information_Service=function(form : update_base_form_type){
    return request.put('/user/userservice/UpdateBaseInformation',form);
}