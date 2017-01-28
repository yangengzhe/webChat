package com.ices.yangengzhe.service.api;

import java.util.HashMap;

import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.util.enums.ResponseType;
import com.ices.yangengzhe.util.pojo.JsonResult;

/**
 * @date 2017年1月23日 下午10:47:37
 * @author yangengzhe
 */
public interface IInformation {

    public ResponseType getMyInformation(Integer uid,  String password,HashMap<String, Object> map);

    //初始化方法
    public JsonResult init(Integer uid, String password);
    
    public void addUser(Integer uid,String name,String photo,String sign);
    
    public User findUserByUid(Integer uid);
    
    //查找好友
    public JsonResult searchUserByKeyword(String Keyword);
}
