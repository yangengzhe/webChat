package com.ices.yangengzhe.util.enums;


public enum ToClientMessageType {

    TYPE_SYSTEM("系统消息",0)

    ,TYPE_TEXT_MESSAGE("普通文本消息",1)
    
    ,SERVICE_ONLINE_STATUS("用户状态服务",2)
    
    ,SERVICE_MESSAGE_COUNT("消息提醒数量服务",3);

    ToClientMessageType(String s,int i){

    }
}

