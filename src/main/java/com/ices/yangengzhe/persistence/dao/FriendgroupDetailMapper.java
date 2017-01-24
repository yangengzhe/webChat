package com.ices.yangengzhe.persistence.dao;

import java.util.List;

import com.ices.yangengzhe.persistence.pojo.FriendgroupDetail;

public interface FriendgroupDetailMapper {

    List<FriendgroupDetail> selectUsersByFID(Integer fid);

    int deleteByPrimaryKey(Integer id);

    int insert(FriendgroupDetail record);

    int insertSelective(FriendgroupDetail record);

    FriendgroupDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FriendgroupDetail record);

    int updateByPrimaryKey(FriendgroupDetail record);
}
