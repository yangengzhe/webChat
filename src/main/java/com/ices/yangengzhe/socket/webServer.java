package com.ices.yangengzhe.socket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.ices.yangengzhe.service.persistence.IUserLogService;
import com.ices.yangengzhe.socket.manager.OnLineUserManager;
import com.ices.yangengzhe.socket.sender.MessageSender;
import com.ices.yangengzhe.socket.sender.MessageParse;
import com.ices.yangengzhe.util.enums.OnlineStatus;
import com.ices.yangengzhe.util.enums.UserLogType;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.SocketUser;
import com.ices.yangengzhe.util.pojo.message.ToServerTextMessage;

/**
 * webSocket服务器
 * 
 * @date 2017年1月22日 上午11:21:17
 * @author yangengzhe
 */
@ServerEndpoint(value = "/websocket/{uid}", configurator = SocketConfigurator.class)
public class webServer {

    private IUserLogService userLogService = (IUserLogService) WebChatFactory.beanFactory("userLogService");

    @OnOpen
    public void open(Session session, @PathParam("uid") int uid) {
        SocketUser user = new SocketUser();
        user.setSession(session);
        user.setUserId(uid);

        // 保存在线列表
        WebChatFactory.createManager().addUser(user);
        userLogService.insertLog(user, UserLogType.LOGIN);
        print("当前在线用户：" + WebChatFactory.createManager().getOnlineCount());
        print("缓存中的用户个数：" + new OnLineUserManager().getOnLineUsers().size());
        //通知所有人
        String message = MessageParse.ServiceOnlineStatus(uid, OnlineStatus.ONLINE);
        WebChatFactory.createManager().notifyOthers(user, message);
    }

    @OnMessage
    public void receiveMessage(String message, Session session) {
        if (message.startsWith("_online_user_")) {
            String uidStr = message.substring("_online_user_".length());
            // 发送离线消息
            MessageSender sender = new MessageSender();
            sender.sendOfflineMessage(Integer.valueOf(uidStr));
            return;
        }
        ToServerTextMessage toServerTextMessage = WebChatFactory.createSerializer().toObject(message,
                                                                                             ToServerTextMessage.class);
        // 得到接收的对象
        MessageSender sender = new MessageSender();
        sender.sendMessage(toServerTextMessage);
    }

    @OnError
    public void error(Throwable t) {
        print(t.getMessage());
    }

    @OnClose
    public void close(Session session) {

        SocketUser user = new SocketUser();
        user.setSession(session);
        user.setUserId(0);
        // 移除该用户
        int uid = WebChatFactory.createManager().removeUser(user);
        user.setUserId(uid);
        userLogService.insertLog(user, UserLogType.LOGOUT);
        System.out.println("用户掉线" + uid);
        // print("当前在线用户：" + WebChatFactory.createManager().getOnlineCount());
        // print("缓存中的用户个数：" + new OnLineUserManager().getOnLineUsers().size());
      //通知所有人
        String message = MessageParse.ServiceOnlineStatus(uid, OnlineStatus.OFFLINE);
        WebChatFactory.createManager().notifyOthers(user, message);
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}
