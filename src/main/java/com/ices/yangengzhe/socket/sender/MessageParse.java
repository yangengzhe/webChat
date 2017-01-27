package com.ices.yangengzhe.socket.sender;

import java.util.HashMap;

import com.ices.yangengzhe.util.enums.OnlineStatus;
import com.ices.yangengzhe.util.enums.ToClientMessageType;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.message.ToClientMessageResult;

/**
 * @date 2017年1月26日 下午11:06:54
 * @author yangengzhe
 *
 */
public class MessageParse {
    public static String ServiceOnlineStatus(Integer uid , OnlineStatus status){
      //返回统一消息接口
        ToClientMessageResult result = new ToClientMessageResult();
        HashMap<String, Object> msg = new HashMap<String, Object>();
        msg.put("id", uid);
        msg.put("status", status.getName());
        result.setMsg(msg);
        result.setType(ToClientMessageType.SERVICE_ONLINE_STATUS);

        return WebChatFactory.createSerializer().toJSON(result);
    }
}
