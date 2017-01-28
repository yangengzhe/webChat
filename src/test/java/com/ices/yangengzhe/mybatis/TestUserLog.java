package com.ices.yangengzhe.mybatis;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.persistence.IUserLogService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })

public class TestUserLog {    // private ApplicationContext ac = null;
    @Autowired
    private IUserLogService  userLogService;
    
     @Test
    public void testAdd() {
        try {
            User user = new User();
            user.setAddtime(new Date());
            user.setHeadphoto("");
            user.setName("闫庚哲");
            user.setOnline(0);
            user.setPassword("123456");
            user.setSign("签名");
            user.setUid(10000);
//            userLogService.insertLog(user, "127.0.0.1", UserLogType.LOGIN);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
