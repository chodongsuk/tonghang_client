package com.peer.adapter;

import java.util.List;
import java.util.Map;
import com.peer.R;
import com.peer.activitymain.PersonalPageActivity;
import com.peer.client.User;
import com.peer.constant.Constant;
import com.peer.util.PersonpageUtil;
import com.peer.widgetutil.LoadImageUtil;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SeachResultAdapter extends BaseAdapter {
	private List<User> mlist;
	private Context mContext;
	public SeachResultAdapter(Context mContext,List<User> list){
		this.mContext=mContext;
		this.mlist=list;
		LoadImageUtil.initImageLoader(mContext);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlist.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mlist.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parentgroup) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_listnike_friends,null,false);
			viewHolder.headpic=(ImageView)convertView.findViewById(R.id.im_headpic);
			viewHolder.nikename=(TextView)convertView.findViewById(R.id.tv_nikename);			
			viewHolder.descripe=(TextView)convertView.findViewById(R.id.tv_descripe);
			viewHolder.click=(LinearLayout)convertView.findViewById(R.id.ll_clike);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}		
		LoadImageUtil.imageLoader.displayImage(mlist.get(position).getImage(), viewHolder.headpic,LoadImageUtil.options);
		viewHolder.nikename.setText(mlist.get(position).getUsername());
		List<String> list=mlist.get(position).getLabels();
		String labels="";
		for(String s:list){
			if(labels.equals("")){
				labels=s;	
			}else{
				labels=labels+","+s;
			}			
		}
		
		viewHolder.descripe.setText(labels);	
		viewHolder.click.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PersonpageUtil.getInstance().setPersonpagetype(Constant.UNFRIENDSPAGE);
				Intent intent=new Intent(mContext,PersonalPageActivity.class);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}
	private class ViewHolder{
		LinearLayout click;
		ImageView headpic;
		TextView nikename;
		TextView descripe;
	}

}
