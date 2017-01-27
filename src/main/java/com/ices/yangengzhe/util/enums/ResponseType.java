package com.ices.yangengzhe.util.enums;


public enum ResponseType {
    SUCCESS(0,"成功"),
    USER_NOTFOUND(10,"用户不存在"),
    USER_WRONG(11,"用户名或密码不存在"),
    USER_DISABLE(12,"用户不可用"),
    LOGIN_NO(20,"未登录");
    
    String msg;
    Integer code;
    
    private ResponseType(Integer code,String msg){
        this.msg = msg;
        this.code = code;
    }

    
    public String getMsg() {
        return msg;
    }

    
    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    public Integer getCode() {
        return code;
    }

    
    public void setCode(Integer code) {
        this.code = code;
    }
    
}
