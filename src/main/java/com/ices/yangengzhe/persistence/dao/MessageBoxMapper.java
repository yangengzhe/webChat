package com.ices.yangengzhe.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ices.yangengzhe.persistence.pojo.MessageBox;

public interface MessageBoxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageBox record);

    int insertSelective(MessageBox record);

    MessageBox selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageBox record);

    int updateByPrimaryKey(MessageBox record);
    
    int countByUid(int uid);
    
    List<MessageBox> selectByUid(@Param("uid")Integer uid,@Param("offset")Integer offset,@Param("count")Integer count);
}