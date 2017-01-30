package com.ices.yangengzhe.service.api;

import com.ices.yangengzhe.util.pojo.JsonPageResult;
import com.ices.yangengzhe.util.pojo.JsonResult;

/**
* webChat
* @date 2017年1月28日 下午10:00:30
* @author gengzhe.ygz
* 
*/
public interface IMessage {
    //消息盒子内容
    JsonPageResult getMessageBox(int uid,int page);
    
    //申请添加好友
    JsonResult applyAddFriend(int uid,int toUid,int from_group,String remark);
    
    //同意并添加好友
    JsonResult agreeAddFriend(int uid,int fromUid,int group);
    
    //忽略添加请求
    JsonResult ignoreAddFriend(int uid,int fromUid);
    
    //获得未处理消息数
    Integer countUnreadMessage(int uid);
}
