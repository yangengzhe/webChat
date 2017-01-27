# webChat
基于layim的web聊天系统，采用Java技术 WebSocket+mysql，利用spring mvc框架+MyBatis实现

## 功能
WebSocket私聊
Restful获取个人信息、好友列表

##结构图

com.ices.yangengzhe.service.api

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

##设计模式

工厂模式、单列模式、代理模式、过滤器模式