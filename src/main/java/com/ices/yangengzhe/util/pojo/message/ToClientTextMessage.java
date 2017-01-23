package com.ices.yangengzhe.util.pojo.message;


/**
 * 发送给客户端的消息
 * 
 * layim.getMessage({
  username: "纸飞机" //消息来源用户名
  ,avatar: "http://tp1.sinaimg.cn/1571889140/180/40030060651/1" //消息来源用户头像
  ,id: "100000" //消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
  ,type: "friend" //聊天窗口来源类型，从发送消息传递的to里面获取
  ,content: "嗨，你好！本消息系离线消息。" //消息内容
  ,cid: 0 //消息id，可不传。除非你要对消息进行一些操作（如撤回）
  ,mine: false //是否我发送的消息，如果为true，则会显示在右方
  ,fromid: "100000" //消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
  ,timestamp: 1467475443306 //服务端动态时间戳
});

 * @date 2017年1月22日 下午1:56:56
 * @author yangengzhe
 *
 */
public class ToClientTextMessage {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private String username;
    private int id;
    private String type;
    private String content;
    private long timestamp;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;

    @Override
    public String toString() {
        return "ToClientTextMessage{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
