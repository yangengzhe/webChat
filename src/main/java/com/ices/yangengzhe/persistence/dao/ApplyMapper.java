package com.ices.yangengzhe.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ices.yangengzhe.persistence.pojo.Apply;
import com.ices.yangengzhe.persistence.pojo.ApplyKey;

public interface ApplyMapper {
    int deleteByPrimaryKey(ApplyKey key);

    int insert(Apply record);

    int insertSelective(Apply record);

    Apply selectByPrimaryKey(ApplyKey key);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);
    
    List<Apply> selectByUID(@Param("uid")Integer uid,@Param("offset")Integer offset,@Param("count")Integer count);
    
    int countByUID(@Param("uid")Integer uid);
    
    int countByFromUid(@Param("fromuser")Integer fromuser,@Param("uid")Integer uid);
}