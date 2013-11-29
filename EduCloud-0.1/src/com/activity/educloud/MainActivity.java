package com.activity.educloud;

import java.lang.reflect.Field;

import android.util.Log;
import com.upload.educloud.FileForderActivity;


import android.os.Build;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
    final static String TAG = "MainActivity";

	//tab��
	private TabHost myTabhost;
	private ImageButton head,message,tools;
	private ImageButton upload,download,delete,others;
	int exitnum = 0;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Log.i(TAG, "main start");
		TabHost.TabSpec spec;
		Intent intent;
		myTabhost=this.getTabHost();
		
		//��һ��ѡ�
		//���ø�ѡ����������ת��activity
		intent=new Intent().setClass(this,FileActivity.class);
		spec=myTabhost.newTabSpec("one")
			.setIndicator("�б�", null)
			.setContent(intent);
		myTabhost.addTab(spec);
		
		//�ڶ���ѡ�
		//���ø�ѡ����������ת��activity
		intent=new Intent().setClass(this,ShareActivity.class);
		spec=myTabhost.newTabSpec("two")
			.setIndicator("����", null)
			.setContent(intent);
		myTabhost.addTab(spec);
		
		//������ѡ�
		//���ø�ѡ����������ת��activity
		intent=new Intent().setClass(this,FriendActivity.class);
		spec=myTabhost.newTabSpec("three")
			.setIndicator("����", null)
			.setContent(intent);
		myTabhost.addTab(spec);
		
		//���ĸ�ѡ�
		//���ø�ѡ����������ת��activity
		intent=new Intent().setClass(this,GroupActivity.class);
		spec=myTabhost.newTabSpec("four")
			.setIndicator("Ⱥ��", null)
			.setContent(intent);
		myTabhost.addTab(spec);
		
		//����tabѡ��߶�
		TabWidget tabwidget=myTabhost.getTabWidget();
		//Integer tabimage[] = {R.drawable.tab_list,R.drawable.tab_share,R.drawable.tab_friend,
		//		R.drawable.tab_group};
		int height=50;
		for(int i=0;i<tabwidget.getChildCount();i++)
		{
			tabwidget.getChildAt(i).getLayoutParams().height=height;
			//tabwidget.getChildAt(i).setBackgroundResource(tabimage[i]);
		}
		

		findViews();
		setListeners();
	}
	
	private void findViews() {
		head = (ImageButton)findViewById(R.id.imbtn_head);
		message = (ImageButton)findViewById(R.id.imbtn_message);
		tools = (ImageButton)findViewById(R.id.imbtn_tools);
		upload = (ImageButton)findViewById(R.id.imbtn_upload);
		download = (ImageButton)findViewById(R.id.imbtn_download);
		delete = (ImageButton)findViewById(R.id.imbtn_delete);
		others = (ImageButton)findViewById(R.id.imbtn_others);
	}
	
	private void setListeners() {
		
		head.setOnClickListener(headBtnClickListener);
		head.setOnTouchListener(headBtnTouchListener);
		message.setOnClickListener(messageBtnClickListener);
		message.setOnTouchListener(messageBtnTouchListener);
		tools.setOnClickListener(toolsBtnClickListener);
		tools.setOnTouchListener(toolsBtnTouchListener);
		upload.setOnClickListener(uploadBtnClickListener);
		upload.setOnTouchListener(uploadBtnTouchListener);
		download.setOnClickListener(downloadBtnClickListener);
		download.setOnTouchListener(downloadBtnTouchListener);
		delete.setOnClickListener(deleteBtnClickListener);
		delete.setOnTouchListener(deleteBtnTouchListener);
		others.setOnClickListener(othersBtnClickListener);
		others.setOnTouchListener(othersBtnTouchListener);
	}
	
	private View.OnClickListener headBtnClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			
			
		}
		};
		
		private View.OnTouchListener headBtnTouchListener = new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					head.setBackgroundResource(R.drawable.selected_male_head);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP){
					head.setBackgroundResource(R.drawable.male_head);
				}
				return false; 
			}

		};
		
		private View.OnClickListener messageBtnClickListener = new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(); 
				intent.setClass(MainActivity.this, MessageActivity.class); 
	        	startActivity(intent);
				
			}
			};
			
			private View.OnTouchListener messageBtnTouchListener = new OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						message.setBackgroundResource(R.drawable.selected_message);
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
						message.setBackgroundResource(R.drawable.message);
					}
					return false; 
				}

			};
			
			private View.OnClickListener toolsBtnClickListener = new OnClickListener(){
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(); 
					intent.setClass(MainActivity.this, SettingsActivity.class); 
		        	startActivity(intent);
					
				}
				};
				
				private View.OnTouchListener toolsBtnTouchListener = new OnTouchListener(){

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if(event.getAction() == MotionEvent.ACTION_DOWN){
							tools.setBackgroundResource(R.drawable.selected_tools);
						}
						else if(event.getAction() == MotionEvent.ACTION_UP){
							tools.setBackgroundResource(R.drawable.tools);
						}
						return false; 
					}

				};
				
	
	private View.OnClickListener uploadBtnClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			try{
				Intent intent = new Intent(); 
				intent.setClass(MainActivity.this, FileForderActivity.class); 
				startActivity(intent);	
			}catch(Exception e){
//				Toast.makeText(this, "�ܱ�Ǹ�����������粻�ǽ�����", Toast.LENGTH_LONG).show();
			}
					
		}
		};
		
		private View.OnTouchListener uploadBtnTouchListener = new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					upload.setBackgroundResource(R.drawable.selected_upload);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP){
					upload.setBackgroundResource(R.drawable.upload);
				}
				return false; 
			}

		};
	
	private View.OnClickListener downloadBtnClickListener = new OnClickListener(){
	@Override
	public void onClick(View v) {
		//��ʾ��
		Toast.makeText(MainActivity.this, "�ܱ�Ǹ�����������粻�ǽ�����", Toast.LENGTH_LONG).show();
	}
	};
	
	private View.OnTouchListener downloadBtnTouchListener = new OnTouchListener(){

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				download.setBackgroundResource(R.drawable.selected_download);
			}
			else if(event.getAction() == MotionEvent.ACTION_UP){
				download.setBackgroundResource(R.drawable.download);
			}
			return false; 
		}

	};
	
	private View.OnClickListener deleteBtnClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {			
//			AlertDialog show = new AlertDialog.Builder(MainActivity.this)
//			.setTitle("��ʾ")
//			.setMessage("��Ǹ~~���������粻�ǽ�����")
//			.setPositiveButton("ȷ��", null)
//			.show();
			Toast.makeText(MainActivity.this, "�ܱ�Ǹ�����������粻�ǽ�����", Toast.LENGTH_LONG).show();
		}
		};
		
		private View.OnTouchListener deleteBtnTouchListener = new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_DOWN){
					delete.setBackgroundResource(R.drawable.selected_delete);
				}
				else if(event.getAction() == MotionEvent.ACTION_UP){
					delete.setBackgroundResource(R.drawable.delete);
				}
				return false; 
			}

		};
		
		private View.OnClickListener othersBtnClickListener = new OnClickListener(){
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "�ܱ�Ǹ�����������粻�ǽ�����", Toast.LENGTH_LONG).show();
				
			}
			};
			
			private View.OnTouchListener othersBtnTouchListener = new OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						others.setBackgroundResource(R.drawable.selected_others);
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
						others.setBackgroundResource(R.drawable.others);
					}
					return false; 
				}

			};

			@Override
			public void finish() {
				// TODO Auto-generated method stub
				if(exitnum == 0)
				{
					exitnum++;
					Toast.makeText(MainActivity.this, "�ٰ�һ���˳�Ӧ��", Toast.LENGTH_LONG).show();
					return;
				}
				else if(exitnum == 1)
				{
					MyApplication mApp = (MyApplication)getApplication();
					if (mApp.isExit()) {
						int pid = android.os.Process.myPid();
						android.os.Process.killProcess(pid);
						finish();
					}
				}
				
			}
		
}
