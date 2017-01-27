package com.ices.yangengzhe.util.enums;



/**
 * @date 2017年1月26日 下午11:13:08
 * @author yangengzhe
 *
 */
public enum OnlineStatus {
    ONLINE("online"),
    OFFLINE("offline");
    
    
    String name;
    
    OnlineStatus(String name){
        this.name= name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getOrdinal(){
        return this.ordinal();
    }
}
