package com.ices.yangengzhe.service.persistence;

import java.util.List;

import com.ices.yangengzhe.persistence.pojo.MessageBox;

/**
* webChat
* @date 2017年1月29日 下午5:22:16
* @author gengzhe.ygz
* 
*/
public interface IMessageboxService {
    //插入一条消息
    void insertMessagebox(int uid,String content);
    
    //根据UID获得消息数量
    int countMessageboxByUID(int uid);
    
    //根据UID获取消息
    List<MessageBox> selectMessagebox(int uid,int offset);
}
