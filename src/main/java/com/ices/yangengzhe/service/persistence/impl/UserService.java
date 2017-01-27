package com.ices.yangengzhe.service.persistence.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.dao.UserMapper;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.persistence.IUserService;
import com.ices.yangengzhe.util.security.Security;


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
    public String insertUser(Integer uid, String name, String photo, String sign) {
        User user = new User();
        user.setAddtime(new Date());
        user.setHeadphoto(photo);
        user.setName(name);
        user.setOnline(0);
        user.setSign(sign);
        user.setUid(uid);
        user.setPassword(Security.getPassword(uid));
        this.userDao.insert(user);
        return null;
    }

}