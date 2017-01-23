package com.ices.yangengzhe.util.enums;


/**
 * 聊天类型
 * @date 2017年1月22日 上午11:24:29
 * @author yangengzhe
 *
 */
public enum ChatType {
    GROUP("group"),
    FRINED("friend");
    
    String name;
    
    ChatType(String name){
        this.name= name;
    }
    
    public String getName(){
        return this.name();
    }
}
