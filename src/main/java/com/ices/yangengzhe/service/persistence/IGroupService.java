package com.ices.yangengzhe.service.persistence;

import java.util.List;

import com.ices.yangengzhe.persistence.pojo.Group;
import com.ices.yangengzhe.persistence.pojo.User;

/**
* webChat
* @date 2017年1月27日 上午10:19:40
* @author gengzhe.ygz
* 
*/
public interface IGroupService {
    
    int insertGroup(Integer uid,String name);
    
    void addMember(Integer gid, Integer uid);
    
    List<Group> selectGroup(Integer uid);
    
    List<User> getAllMember(Integer gid);
    
    List<String> getAllMemberToString(Integer gid);
    
    Group selectGroupById(Integer gid);
    
    User getGroupOwner(Integer gid);
}
