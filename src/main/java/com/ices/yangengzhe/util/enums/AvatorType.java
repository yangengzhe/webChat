package com.ices.yangengzhe.util.enums;


/**
* webchat
* @date 2017年1月30日 下午3:24:49
* @author gengzhe.ygz
* 
*/
public enum AvatorType {
    STUDENT_M("sm","images/avator-sm.jpg"),
    STUDENT_F("sf","images/avator-sf.jpg"),
    TEACHER_M("tm","images/avator-tm.jpg"),
    TEACHER_F("tf","images/avator-tf.jpg"),
    QUN("qun","images/avator-qun.jpg");
    
    String typeName;
    String headphoto;
    
    private AvatorType(String typeName, String headphoto){
        this.typeName = typeName;
        this.headphoto = headphoto;
    }
    
    public static AvatorType getMatchByName(String typeName) {
        for (AvatorType routeState : values()){
            if (routeState.typeName.equalsIgnoreCase(typeName)){
                return routeState;
            }
        }
        return null;
    }

    
    public String getTypeName() {
        return typeName;
    }

    
    public String getHeadphoto() {
        return headphoto;
    }
    
    
}
