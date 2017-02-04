package com.ices.yangengzhe.persistence.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ices.yangengzhe.persistence.pojo.FriendgroupDetail;

public interface FriendgroupDetailMapper {

    List<FriendgroupDetail> selectUsersByFID(Integer fid);
    
    List<FriendgroupDetail> selectUsersByFIDUID(@Param("fid")Integer fid,@Param("uid")Integer uid);

    int deleteByPrimaryKey(Integer id);

    int insert(FriendgroupDetail record);

    FriendgroupDetail selectByPrimaryKey(Integer id);
    
    int countfidOwnerUid(@Param("fidOwner")Integer fidOwner,@Param("uid")Integer uid);
}
