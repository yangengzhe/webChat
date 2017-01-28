package com.ices.yangengzhe.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ices.yangengzhe.persistence.pojo.MsgUnread;

public interface MsgUnreadMapper {
    //id 接收者（好友或群） gid 0代表好友 1代表群 fromuser表示发送者
    
    //删除
    int deleteMsg(@Param("id")Integer id,@Param("gid")Integer gid,@Param("fromuser")Integer fromuser,@Param("uid")Integer uid);
    //自动插入
    int countMsg(@Param("id")Integer id,@Param("gid")Integer gid,@Param("fromuser")Integer fromuser,@Param("uid")Integer uid);
    
    //自己的好友： id = 自己UID 且 gid=0
    //自己的群：  id = 自己群ID 且 gid=1
    List<MsgUnread> selectByGidId(@Param("gid")Integer gid,@Param("id")Integer id,@Param("uid")Integer uid);
    
    int insertSelective(MsgUnread record);

    int updateByPrimaryKeySelective(MsgUnread record);

    int updateByPrimaryKey(MsgUnread record);
}