package com.ices.yangengzhe.service;

import com.ices.yangengzhe.persistence.pojo.User;

/**
 * @date 2017年1月22日 下午7:27:13
 * @author yangengzhe
 *
 */
public interface IUserService {

    public User getUserById(int userId);
    
    public User getUserByUID(int userUID);
    
    public void insertUser(User user);
}
