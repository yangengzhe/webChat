package com.ices.yangengzhe.persistence.dao;

import com.ices.yangengzhe.persistence.pojo.GroupDetail;

public interface GroupDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GroupDetail record);

    int insertSelective(GroupDetail record);

    GroupDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupDetail record);

    int updateByPrimaryKey(GroupDetail record);
}