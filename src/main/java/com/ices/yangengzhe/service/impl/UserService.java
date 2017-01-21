package com.ices.yangengzhe.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ices.yangengzhe.dao.UserMapper;
import com.ices.yangengzhe.pojo.User;
import com.ices.yangengzhe.service.IUserService;


@Service("userService")
public class UserService implements IUserService {

    @Resource
    private UserMapper userDao;

    @Override
    public User getUserById(int userId) {
        return this.userDao.selectByPrimaryKey(userId);
    }

}