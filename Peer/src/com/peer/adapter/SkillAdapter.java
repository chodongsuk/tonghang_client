package com.peer.adapter;

import java.util.ArrayList;
import java.util.List;

import com.peer.R;
import com.peer.activity.MySkillActivity;
import com.peer.activity.SettingActivity;
import com.peer.event.NewFriensEvent;
import com.peer.event.SkillEvent;
import com.peer.util.ManagerActivity;

import de.greenrobot.event.EventBus;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class SkillAdapter extends BaseAdapter {
	private Context mContext;
	private String type="";
	private List<String> mlist;
	public SkillAdapter(Context mContext,List<String> mlist){
		this.mContext=mContext;
		this.mlist=mlist;
	}
	public SkillAdapter(Context mContext,String type){
		this.mContext=mContext;
		this.type=type;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(type.equals("page")){
			return 5;
		}
		return mlist.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parentgroup) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder=null;
		if(convertView==null){
			viewHolder=new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_skill,null,false);
			viewHolder.skillname=(TextView)convertView.findViewById(R.id.tv_skill);
			viewHolder.delete=(TextView)convertView.findViewById(R.id.tv_delete);
			viewHolder.update=(TextView)convertView.findViewById(R.id.tv_update);		
			if(type.equals("page")){
				viewHolder.delete.setVisibility(View.GONE);
				viewHolder.update.setVisibility(View.GONE);
			}else{
				viewHolder.delete.setVisibility(View.VISIBLE);
				viewHolder.update.setVisibility(View.VISIBLE);
				viewHolder.delete.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						deleteSkill(position);
					}
				});
				viewHolder.update.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						updateSkill();
					}
				});
			}
			convertView.setTag(viewHolder);
		}else{
			convertView.getTag();
		}		
		return convertView;
	}
	private void deleteSkill(final int position){
		new AlertDialog.Builder(mContext).setTitle(mContext.getResources().getString(R.string.deleteskill))  
		.setMessage(mContext.getResources().getString(R.string.deletethisskill)) .setNegativeButton(mContext.getResources().getString(R.string.cancel), null) 
		 .setPositiveButton(mContext.getResources().getString(R.string.sure), new DialogInterface.OnClickListener(){
             public void onClick(DialogInterface dialoginterface, int i){            	 
            	 EventBus.getDefault().post(new SkillEvent(position));
             }
		 })
		 .show();  
	}
	private void updateSkill() {
		// TODO Auto-generated method stub
		final EditText inputServer = new EditText(mContext);
        inputServer.setFocusable(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mContext.getResources().getString(R.string.updateskill)).setView(inputServer).setNegativeButton(
        		mContext.getResources().getString(R.string.cancel), null);
        builder.setPositiveButton(mContext.getResources().getString(R.string.sure),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String inputName = inputServer.getText().toString().trim();
                       if(!inputName.equals("")){
                    	   List<String> arr=new ArrayList<String>();
                           arr.add(inputName);
                        
                       }else{
                    	  
                       }
                       
                    }
                });
        builder.show();			
	}
	private class ViewHolder{
		TextView skillname,delete,update;
		
	}
}
