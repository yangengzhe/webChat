package com.ices.yangengzhe.util.enums;


/**
 * @date 2017年1月23日 下午7:27:32
 * @author yangengzhe
 *
 */
public enum UserLogType {
    
    LOGIN("登陆"),
    LOGOUT("退出");
    
    String value;
       
    UserLogType(String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
}
