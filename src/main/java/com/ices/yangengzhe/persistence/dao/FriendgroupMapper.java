package com.ices.yangengzhe.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ices.yangengzhe.persistence.pojo.Friendgroup;

public interface FriendgroupMapper {

    List<Friendgroup> selectByUID(Integer uid);

    int deleteByPrimaryKey(Integer id);

    int insert(Friendgroup record);

    int insertSelective(Friendgroup record);
    
    List<Friendgroup> selectByNameUID(@Param("name")String name,@Param("uid")Integer uid);

    Friendgroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Friendgroup record);

    int updateByPrimaryKey(Friendgroup record);
}
