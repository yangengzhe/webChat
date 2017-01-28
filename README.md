# webChat

基于layim 3.x的web聊天系统，采用Java技术 WebSocket+mysql，利用spring mvc框架+MyBatis实现

**注意：由于layim非开源，故本项目中移除该部分功能，无法导入直接运行。需要将layui文件夹拷贝到/webapp/js/目录下**

## 功能

1. 私聊、群聊
2. 离线消息
3. 实时上线、下线状态更新

## 更新

2017.1.29

1. 完成 添加好友、消息盒子的模板
2. 

---

2017.1.28

完成群离线消息的处理

---

2017.1.27

1. 完成Restful的member接口（获取群成员信息）
2. 完成Restful的init接口（获取群信息）

---

2017.1.26

1. 离线消息功能完成
2. 实时上线、下线状态更新

---

2017.1.24

1. 完成WebSocket私聊功能
2. 完成Restful的init接口（获取个人信息、好友列表）

---

2017.1.22

基本框架搭设

## 结构图

### com.ices.yangengzhe.service.api

	Information
		init - 初始化方法 返回用户信息和好友列表
		addUser - 添加用户
		findUserByUid - 根据UID找用户
		
	Friend
		// 创建好友分组
	    int addFriendgroup(Integer uid, String groupname);
	    // 创建好友分组
	    int addFriendgroup(Integer uid, String groupname, List<User> members);
	    // 添加好友
	    void addFriend(Integer user1,Integer user1_group,Integer user2,Integer user2_group);
	    //向分组加成员
	    void addMemberToFG(Integer groupId,Integer uid);
	    // 获取分组列表
	    public List<Friendgroup> getFriendgroupList(Integer uid);
	    // 获取分组列表(含成员)
	    public List<HashMap<String, Object>> getFriendgroupDetaillist(Integer uid);
	    // 获取好友分组的成员
	    public List<HashMap<String, Object>> getFridengroupMember(Integer fid);

### com.ices.yangengzhe.socket

	webServer.java	webSocket服务器，用于与客户端消息的交互	
	manager	利用缓存管理会员列表
		GroupUserManager.java	组成员管理
		UserManager.java		会员session管理
		OnLineUserManager.java	在线会员管理
	sender 	消息发送
		MessageParse.java		消息生成工具类
		MessageSender.java		消息发送类

## 设计模式

工厂模式、单列模式、代理模式、过滤器模式

## 帮助支持

可在该项目的Issues中讨论 或 加入QQ群：194895016

by 爱上极客(http://www.i3geek.com)