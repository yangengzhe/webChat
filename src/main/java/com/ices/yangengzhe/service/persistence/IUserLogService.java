package com.ices.yangengzhe.service.persistence;

import com.ices.yangengzhe.persistence.pojo.User;
import com.ices.yangengzhe.util.enums.UserLogType;

/**
 * @date 2017年1月23日 下午7:27:09
 * @author yangengzhe
 *
 */
public interface IUserLogService {

    public void insertLog(User user, String ip, UserLogType type);
}
