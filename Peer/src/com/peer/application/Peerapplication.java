package com.peer.application;

import java.util.Iterator;
import java.util.List;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;

import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMChatOptions;
import com.peer.activitymain.CreatTopicActivity;
import com.peer.client.ServiceAction;
import com.peer.client.ui.PeerUI;

public class Peerapplication extends Application {
	private static Peerapplication instance=null;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub	
		instance=this;	
		initwebsevice();
		initEMChat();
		JPushInterface.setDebugMode(false);
		JPushInterface.init(this);		
		ShareSDK.initSDK(this);
	}	
	private void initwebsevice() {
		// TODO Auto-generated method stub
		 Intent serviceIntent = new Intent(ServiceAction.ACTION_SERVICE);
		 startService(serviceIntent);
		 PeerUI.getInstance();
	}
	public static Peerapplication getInstance() {
		return instance;
	}
	/*初始化环信sdk*/
	private void initEMChat() {
		// TODO Auto-generated method stub
		int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        
		EMChat.getInstance().init(instance);
		
		EMChatManager.getInstance().getChatOptions().setShowNotificationInBackgroud(false);
		EMChatOptions options = EMChatManager.getInstance().getChatOptions();
		options.setNotifyBySoundAndVibrate(false);
		EMChat.getInstance().setDebugMode(true);      	       
	}
	private String getAppName(int pID) {
		String processName = null;
		ActivityManager am = (ActivityManager) this
				.getSystemService(ACTIVITY_SERVICE);
		List l = am.getRunningAppProcesses();
		Iterator i = l.iterator();
		PackageManager pm = this.getPackageManager();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i
					.next());
			try {
				if (info.pid == pID) {
					CharSequence c = pm.getApplicationLabel(pm
							.getApplicationInfo(info.processName,PackageManager.GET_META_DATA));
					processName = info.processName;
					return processName;
				}
			} catch (Exception e) {
			}
		}
		return processName;
	}
}
