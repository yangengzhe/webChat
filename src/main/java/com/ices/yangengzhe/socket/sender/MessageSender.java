package com.ices.yangengzhe.socket.sender;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import org.apache.log4j.lf5.viewer.categoryexplorer.TreeModelAdapter;

import com.ices.yangengzhe.service.persistence.IMessageService;
import com.ices.yangengzhe.service.persistence.IUserLogService;
import com.ices.yangengzhe.socket.manager.GroupUserManager;
import com.ices.yangengzhe.util.enums.ChatType;
import com.ices.yangengzhe.util.enums.ToClientMessageType;
import com.ices.yangengzhe.util.factory.WebChatFactory;
import com.ices.yangengzhe.util.pojo.SocketUser;
import com.ices.yangengzhe.util.pojo.message.ToClientMessageResult;
import com.ices.yangengzhe.util.pojo.message.ToClientTextMessage;
import com.ices.yangengzhe.util.pojo.message.ToServerMessageMine;
import com.ices.yangengzhe.util.pojo.message.ToServerTextMessage;

/**
 * 发送信息类
 * 所有从客户端到服务端的消息转发到此类进行消息处理
 * ToServerTextMessage转换为ToClientTextMessage
 * 如果是单聊，直接从缓存取出对象的session进行消息发送，群聊则需要从缓存中取出该群里所有人的id进行遍历发送消息，
 * 遍历过后需要优化在线与否，假如100人中只有一个人在线，则会浪费99次（未做优化）
 */
public class MessageSender {
    private IMessageService messageService = (IMessageService) WebChatFactory.beanFactory("messageService");
    //发送信息业务逻辑
    public void sendMessage(ToServerTextMessage message){

        int toUserId = message.getTo().getId();
        //获取发送人
        String sendUserId = Integer.toString(message.getMine().getId());
        String type =  message.getTo().getType();
        //消息提前生成，否则进行循环内生成会浪费资源
        String toClientMessage = getToClientMessage(message);

        System.out.println("当前消息类型是"+type);
        //不能用==做比较，因为一个是static final 值，另外一个是在对象中 == 为false
        if(type.equals(ChatType.GROUP.getName())){
            //群聊，需要遍历该群组里的所有人
            //第一次从缓存中取userId，否则，从数据库中取在存到缓存中
            List<String> users =  new GroupUserManager().getGroupMembers(message.getTo().getId());
            for (String userid : users) {
                //遍历发送消息 自己过滤，不给自己发送(发送人id和群成员内的某个id相同)
                if (!sendUserId.equals(userid)) {
                    sendMessage(Integer.parseInt(userid), toClientMessage,message);
                }
            }
        }else {
            sendMessage(toUserId, toClientMessage,message);
        }

        //最后保存到数据库
        saveMessage(message);

    }
    
  //给单个用户发
    private  void sendMessage(Integer userId,String message,ToServerTextMessage toServerTextMessage){
        SocketUser user = WebChatFactory.createManager().getUser(userId);
        if (user.isExist()) {
            if (user.getSession() == null) {
//                LayIMLog.info("该用户不在线 ");
                saveOfflineMessage(toServerTextMessage);
            } else {
                Session session = user.getSession();
                if (session.isOpen()) {
                    //构造用户需要接收到的消息
                        session.getAsyncRemote().sendText(message);
                }
            }
        }else{
//            LayIMLog.info("该用户不在线 ");
            saveOfflineMessage(toServerTextMessage);
        }
    }
    
  //同步给单个用户发（离线推送）
    public  void sendMessage(Integer userId,String message){
        SocketUser user = WebChatFactory.createManager().getUser(userId);
        if (user.isExist()) {
            if (user.getSession() == null) {
            } else {
                Session session = user.getSession();
                if (session.isOpen()) {
                        try {
                            session.getBasicRemote().sendText(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }else{
        }
    }

    //发送离线消息
    public void sendOfflineMessage(Integer userId){
        //获取好友的离线消息
        List<List<String>> msgList = messageService.getFriendUnreadMessage(userId);
        for (List<String> list : msgList) {
            for (String string : list) {
                System.out.println("离线消息："+ string);
                sendMessage(userId,string);
            }
        }
        
        //获取群组的离校消息
        msgList = messageService.getGroupUnreadMessage(userId);
        for (List<String> list : msgList) {
            for (String string : list) {
                sendMessage(userId,string);
            }
        }
    }
    
    public void saveOfflineMessage(ToServerTextMessage message){
        if(message==null)
            return;
        messageService.offlineMessage(message);
    }
    
    //保存到数据库
    //需要加入到队列
    private void saveMessage(ToServerTextMessage message){
        
        messageService.insertMessage(message);
        
    }

    //根据客户端发送来的消息，构造发送出去的消息
     /*
        *  username: data.mine.username
            , avatar: data.mine.avatar
            , id: data.mine.id
            , type: data.to.type
            , content:data.mine.content
            , timestamp: new Date().getTime()
        * */
    private String getToClientMessage(ToServerTextMessage message) {

        ToClientTextMessage toClientTextMessage = new ToClientTextMessage();

        ToServerMessageMine mine = message.getMine();

        toClientTextMessage.setUsername(mine.getUsername());
        toClientTextMessage.setAvatar(mine.getAvatar());
        toClientTextMessage.setContent(mine.getContent());
        toClientTextMessage.setType(message.getTo().getType());

        //群组和好友直接聊天不同，群组的id 是 组id，否则是发送人的id
        if (toClientTextMessage.getType().equals(ChatType.GROUP.getName())) {
            toClientTextMessage.setId(message.getTo().getId());
        } else {
            toClientTextMessage.setId(mine.getId());
        }
        toClientTextMessage.setTimestamp(new Date().getTime());

        //返回统一消息接口
        ToClientMessageResult result = new ToClientMessageResult();
        result.setMsg(toClientTextMessage);
        result.setType(ToClientMessageType.TYPE_TEXT_MESSAGE);

        return WebChatFactory.createSerializer().toJSON(result);
    }

    //生成对应的groupId
    private long getGroupId(int sendUserId,int toUserId,String type){

        //如果是组内聊天，直接返回组id，否则返回 两个id的组合
        if (type.equals(ChatType.GROUP.getName())){
            return toUserId;
        }


        String sendUserIdStr = Integer.toString(sendUserId);
        String toUserIdStr = Integer.toString(toUserId);

        String groupIdStr = "";
        //按照固定次序生成相应的聊天组
        if (sendUserId > toUserId){
            groupIdStr = sendUserIdStr + toUserIdStr;
        }else{
            groupIdStr = toUserIdStr + sendUserIdStr;
        }

        long groupId = Long.parseLong(groupIdStr);
        return groupId;
    }
}
