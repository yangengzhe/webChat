package com.ices.yangengzhe.persistence.dao;

import com.ices.yangengzhe.persistence.pojo.Friendgroup;

public interface FriendgroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Friendgroup record);

    int insertSelective(Friendgroup record);

    Friendgroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friendgroup record);

    int updateByPrimaryKey(Friendgroup record);
}