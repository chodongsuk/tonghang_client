package com.peer.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.peer.R;
import com.peer.activitymain.CreatTopicActivity;
import com.peer.activitymain.HomePageActivity;
import com.peer.activitymain.SearchActivity;
import com.peer.adapter.HomepageAdapter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeFragment extends BasicFragment{
	private TextView createtopic;
	private LinearLayout search;
	private PullToRefreshListView mPullrefreshlistview;	
	public RelativeLayout errorItem;
	public TextView errorText;
	
	List<Map> list=new ArrayList<Map>();
	HomepageAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_home, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initdata();
		init();
		
	}
	private void init() {
		// TODO Auto-generated method stub
		errorItem = (RelativeLayout) getView().findViewById(R.id.rl_error_item);
		errorText = (TextView) errorItem.findViewById(R.id.tv_connect_errormsg);
		
		search=(LinearLayout)getView().findViewById(R.id.ll_search);		
		createtopic=(TextView)getView().findViewById(R.id.tv_createtopic);
		
		createtopic.setOnClickListener(this);		
		search.setOnClickListener(this);
			
		mPullrefreshlistview=(PullToRefreshListView)getView().findViewById(R.id.pull_refresh_homepage);
		
		adapter=new HomepageAdapter(getActivity(),list);
		mPullrefreshlistview.setAdapter(adapter);
		RefreshListner();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);
		switch (v.getId()) {
		case R.id.ll_search:
			Intent tosearch=new Intent(getActivity(),SearchActivity.class);
			startActivity(tosearch);			
			break;
		case R.id.tv_createtopic:
			Intent creat=new Intent(getActivity(),CreatTopicActivity.class);
			startActivity(creat);
			break;
		}
	}
	private void RefreshListner() {
		// TODO Auto-generated method stub
		mPullrefreshlistview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub				
				String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				RefreshTask task=new RefreshTask();
				task.execute("DownToRefresh");				
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub				
				String label = DateUtils.formatDateTime(getActivity().getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);				
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				RefreshTask task=new RefreshTask();
				task.execute("UpToRefresh");
			}			
		});
	}
	private void initdata() {
		// TODO Auto-generated method stub
		Map m=new HashMap<String, String>();
		m.put("type", "person");
		list.add(m);
		m=new HashMap<String, String>();
		m.put("type", "topic");
		list.add(m);
		m=new HashMap<String, String>();
		m.put("type", "person");
		list.add(m);
		m=new HashMap<String, String>();
		m.put("type", "person");
		list.add(m);
		m=new HashMap<String, String>();
		m.put("type", "person");
		list.add(m);
		m=new HashMap<String, String>();
		m.put("type", "topic");
		list.add(m);
		m=new HashMap<String, String>();
		m.put("type", "topic");
		list.add(m);
		m=new HashMap<String, String>();
		m.put("type", "person");
		list.add(m);
	}
	private void freshdata(){
		Map m=new HashMap<String, String>();
		m.put("type", "topic");
		for(int i=0;i<10;i++){
			list.add(m);
		}	
	}
	private class RefreshTask extends AsyncTask<String, String, Void>{

		@Override
		protected Void doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(500);														
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(arg0[0].equals("up")){
				freshdata();	
			}else{
				initdata();
			}		
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			adapter.notifyDataSetChanged();
			mPullrefreshlistview.onRefreshComplete();
		}		
	}
	private class RecommendTask extends AsyncTask<Void, Void, List>{

		@Override
		protected List doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			String message = null;
            int code = 1;
           
			return list;
		}
		@Override
		protected void onPostExecute(List result) {
			// TODO Auto-generated method stub
		
		}
	}
}
