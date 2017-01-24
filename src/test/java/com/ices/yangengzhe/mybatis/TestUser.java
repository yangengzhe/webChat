package com.ices.yangengzhe.mybatis;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.service.persistence.IUserService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })

public class TestUser {

    private static Logger logger = Logger.getLogger(TestUser.class);
    // private ApplicationContext ac = null;
    @Autowired
    private IUserService  userService;

    // @Before
    // public void before() {
    // ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    // userService = (IUserService) ac.getBean("userService");
    // }

//     @Test
    public void testAdd() {
        try {
            User user = new User();
            user.setAddtime(new Date());
            user.setHeadphoto("");
            user.setName("李四");
            user.setOnline(0);
            user.setPassword("123456");
            user.setSign("签名");
            user.setUid(10002);
            userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        // logger.info(JSON.toJSONString(user));
    }

//     @Test
    public void testFindByUID() {
        try {
            User user = userService.getUserByUID(10000);
            System.out.println(user.getName());
            System.out.println(JSON.toJSONString(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
