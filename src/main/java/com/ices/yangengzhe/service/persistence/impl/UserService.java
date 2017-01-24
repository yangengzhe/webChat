package com.ices.yangengzhe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.dao.UserMapper;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.IUserService;


/**
 * @date 2017年1月22日 下午7:27:24
 * @author yangengzhe
 *
 */
@Service("userService")
public class UserService implements IUserService {

    @Resource
    private UserMapper userDao;

    @Override
    public User getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserByUID(int userUID) {
        return this.userDao.selectByUID(userUID);
    }

    @Override
    public void insertUser(User user) {
        this.userDao.insert(user);
    }

}