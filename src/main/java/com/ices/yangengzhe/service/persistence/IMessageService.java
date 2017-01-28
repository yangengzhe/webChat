package com.ices.yangengzhe.service.persistence;

import java.util.List;

import com.ices.yangengzhe.util.pojo.message.ToServerTextMessage;

/**
 * @date 2017年1月24日 下午3:11:08
 * @author yangengzhe
 */
public interface IMessageService {

    // 保存消息
    void insertMessage(ToServerTextMessage message);

 // 根据接收好友id读取
    List<String> getMessageByUIDTop(int uid, int num);
    
 // 根据接收好友id 和发送好友id 读取
    List<String> getMessageByUIDTop(int fromuser,int uid, int num);

    // 根据接收群id读取
    List<String> getMessageByGIDTop(int gid, int num);

    // 根据发送人id读取
    List<String> getMessageByFromIDTop(int fromuser, int num);

    
    
    // 插入未读信息数据
    void offlineMessage(Integer recvUid,ToServerTextMessage message);

    // 获取未读好友信息
    List<List<String>> getFriendUnreadMessage(Integer id);

    // 获取未读群信息
    List<List<String>> getGroupUnreadMessage(Integer id);

}
