package com.ices.yangengzhe.util.factory;

import com.ices.yangengzhe.socket.manager.IUserManager;
import com.ices.yangengzhe.socket.manager.UserManager;
import com.ices.yangengzhe.util.serializer.FastJsonSerializer;
import com.ices.yangengzhe.util.serializer.IJsonSerializer;

/**
 * @date 2017年1月22日 下午1:23:51
 * @author yangengzhe
 *
 */
public class WebChatFactory {
    //创建序列化器
    public static IJsonSerializer createSerializer(){
        return new FastJsonSerializer();
    }

    //创建在线人员管理工具
    public static IUserManager createManager(){
        return UserManager.getInstance();
    }

}
