package com.ices.yangengzhe.service.persistence;

import com.ices.yangengzhe.util.enums.UserLogType;
import com.ices.yangengzhe.util.pojo.SocketUser;

/**
 * @date 2017年1月23日 下午7:27:09
 * @author yangengzhe
 *
 */
public interface IUserLogService {

    public void insertLog(SocketUser user, UserLogType type);
}
