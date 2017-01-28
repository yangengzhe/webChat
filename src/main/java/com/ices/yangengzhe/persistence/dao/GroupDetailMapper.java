package com.ices.yangengzhe.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ices.yangengzhe.persistence.pojo.GroupDetail;

public interface GroupDetailMapper {
    int deleteByUIDGID(@Param("uid") Integer uid,@Param("gid") Integer gid);

    int insert(GroupDetail record);

    List<GroupDetail> selectByUID(Integer uid);
    
    List<GroupDetail> selectByGID(Integer gid);

}