package com.ices.yangengzhe.persistence.dao;

import com.ices.yangengzhe.persistence.pojo.User;

public interface UserMapper {
    
    User selectByUID(Integer uid);
    
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}