package com.ices.yangengzhe.util.enums;


/**
 * 聊天类型
 * @date 2017年1月22日 上午11:24:29
 * @author yangengzhe
 *
 */
public enum ChatType {
    FRINED("friend"),
    GROUP("group");
    
    
    String name;
    
    ChatType(String name){
        this.name= name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getOrdinal(){
        return this.ordinal();
    }
}
