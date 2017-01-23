package com.ices.yangengzhe.socket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.ices.yangengzhe.socket.manager.OnLineUserManager;
import com.ices.yangengzhe.socket.sender.MessageSender;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.SocketUser;
import com.ices.yangengzhe.util.pojo.message.ToServerTextMessage;

/**
 * webSocket服务器
 * @date 2017年1月22日 上午11:21:17
 * @author yangengzhe
 *
 */
@ServerEndpoint("/websocket/{uid}")
public class webServer {
    @OnOpen
    public  void open(Session session, @PathParam("uid") int uid){
        SocketUser user = new SocketUser();
        user.setSession(session);
        user.setUserId(uid);

        //保存在线列表
        WebChatFactory.createManager().addUser(user);
        print("当前在线用户："+WebChatFactory.createManager().getOnlineCount());
        print("缓存中的用户个数："+new OnLineUserManager().getOnLineUsers().size());
    }

    @OnMessage
    public void receiveMessage(String message,Session session){
        //try {

            ToServerTextMessage toServerTextMessage =
                    WebChatFactory.createSerializer().toObject(message,ToServerTextMessage.class);

            //得到接收的对象
            MessageSender sender = new MessageSender();

            sender.sendMessage(toServerTextMessage);

        //}catch (Exception e){
          //  e.printStackTrace();
        //}
    }

    @OnError
    public void error(Throwable t) {
        print(t.getMessage());
    }

    @OnClose
    public void close(Session session){

        SocketUser user = new SocketUser();
        user.setSession(session);
        user.setUserId(0);
        print("用户掉线");
        //移除该用户
        WebChatFactory.createManager().removeUser(user);
       //print("当前在线用户："+LayIMFactory.createManager().getOnlineCount());

    }

    private void print(String msg){
        System.out.println(msg);
    }
}
