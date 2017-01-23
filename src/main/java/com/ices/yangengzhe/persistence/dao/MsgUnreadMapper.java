package com.ices.yangengzhe.persistence.dao;

import com.ices.yangengzhe.persistence.pojo.MsgUnread;

public interface MsgUnreadMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgUnread record);

    int insertSelective(MsgUnread record);

    MsgUnread selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MsgUnread record);

    int updateByPrimaryKey(MsgUnread record);
}