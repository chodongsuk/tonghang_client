// ISessionManager.aidl
package com.peer.client;

// Declare any non-default types here with import statements
import com.peer.client.ISessionListener;
import com.peer.client.User;

interface ISessionManager {

	String getHuanxingUser();
	
	String getUserId();
	
	String getImagUrL();
	
	String getUserName();
	
	List<String> getLabels();
	
    com.peer.client.User login(String username, String password, ISessionListener callback);    
    
    void register(String email, String password, String username, ISessionListener callback);
   
    void registerLabel(in List<String> labels,ISessionListener callback);
 		
 	void profileUpdate(String nickName, String birthday, String city, String sex, String filename, in byte[] image, ISessionListener callback);
 	
 	void addFriends(String targetId,String reason,ISessionListener callback);
 	
 	void refuseAddFriends(String sourceId);
 	
 	void agreeAddFriends(String sourceId);
 	
 	void deleteFriends(String targetId);
 	
    List<String> search(String label,ISessionListener callback);
    
    List<com.peer.client.User> searchUserByLabel(String label,ISessionListener callback);
    
    List<com.peer.client.User> searchUsersByNickName(String username,ISessionListener callback);
 	
 	List recommendByPage(int page, ISessionListener callback);
 
 	List<com.peer.client.User> myFriends();
 	
 	List<com.peer.client.User> Invitations(ISessionListener callback);
 	
 	com.peer.client.User personalPage(String targetId,ISessionListener callback);
 	
 	List topicHistory(String targetId,ISessionListener callback);
 	
 	void TopicchatHistory(String topicId,ISessionListener callback);
 	
 	void forgetPassword(String tagetId,ISessionListener callback);
 	
 	void updatePassword(String newPassword);
 	
 	String creatTopic(String label,String topic);
 	
 	void setLabels(in List<String> labels);
 	
 	List<com.peer.client.User> inTopicUser(String topicid,ISessionListener callback);
 	
}
