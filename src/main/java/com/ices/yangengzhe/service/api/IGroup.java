package com.ices.yangengzhe.service.api;

import java.util.HashMap;
import java.util.List;

import com.ices.yangengzhe.util.pojo.JsonResult;

/**
 * @date 2017年1月27日 上午10:02:36
 * @author yangengzhe
 *
 */
public interface IGroup {
    //创建空群
    int addGroup(Integer uid,String name);
    
    //创建带好友的群
    int addGroup(Integer uid,String name,List<Integer> uids);
    
    //向群内添加成员
    void addMemberToGroup(Integer gid,Integer uid);
    
    //获取群列表
    List<HashMap<String, Object>> getAllGroup(Integer uid);
    
    //查看群成员
    JsonResult getGroupMembers(Integer gid);
    
}
