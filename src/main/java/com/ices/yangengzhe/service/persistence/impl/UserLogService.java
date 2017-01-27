package com.ices.yangengzhe.service.persistence.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ices.yangengzhe.persistence.dao.UserLogMapper;
import com.ices.yangengzhe.persistence.pojo.UserLog;
import com.ices.yangengzhe.service.persistence.IUserLogService;
import com.ices.yangengzhe.util.enums.UserLogType;
import com.ices.yangengzhe.util.pojo.SocketUser;

/**
 * @date 2017年1月23日 下午7:27:20
 * @author yangengzhe
 *
 */
@Service
public class UserLogService implements IUserLogService {
    
    @Resource
    private UserLogMapper userLogDao;
    
    @Override
    public void insertLog(SocketUser user, UserLogType type) {
        UserLog userlog = new UserLog();
        userlog.setAddtime(new Date());
        String ipaddr=(String) user.getSession().getUserProperties().get("ClientIP");
        userlog.setIp(ipaddr);
        userlog.setLog(type.getValue());
        userlog.setUid(user.getUserId());
        userLogDao.insert(userlog);
    }

}
