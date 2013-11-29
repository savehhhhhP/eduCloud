package com.activity.educloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MessageActivity extends ListActivity {
	
	private ImageButton goback;
	//��Ӳ˵�ģ����
	
	private List<Map<String, Object>> mData;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		goback = (ImageButton)findViewById(R.id.imbtn_goback);
//		goback.setOnClickListener(gobackBtnClickListener);
		mData = getData();
		MyAdapter adapter = new MyAdapter(this);
		setListAdapter(adapter);
		
		//��ʼ���˵�
	}
	private View.OnClickListener gobackBtnClickListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(); 
			intent.setClass(MessageActivity.this, MainActivity.class); 
        	startActivity(intent);
//        	setContentView(R.layout.activity_message);
		}
		};
	

	private List<Map<String, Object>> getData() {
		//��ȡIntent�е�Bundle����
//		Bundle bundle = this.getIntent().getExtras();
		//ȡ��Bundle�����е�����
//		String previousTitle = bundle.getString("title");
//		getWindow().setTitle(previousTitle);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		//��̬��У԰��Ϣģ�����Ϣ���Ժ�Ҫ�����ݿ����洢
		String [] title = {"ITAT�����ͷ�","���˱���׿����","SUNRISE����С��"};
		String [] info = {
				"����������ʼ��ϣ�����������ǰ׼��",
				"AndroidӦ�ó��򿪷������Ѿ���ʼ�ύ��Ʒ",
				"���ǵ����˼���С�飬ϣ����Ҷ��֧��~~"
				};
		int [] img = {R.drawable.itat_head,R.drawable.zte_head,R.drawable.sunrise_head};
		for (int i=0; i<img.length; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", title[i]);
			map.put("info", info[i]);
			map.put("img", img[i]);
			list.add(map);
		}		
		return list;
	}
	
	// ListView 
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {		
//		Log.v("MyListView4-click", (String)mData.get(position).get("title"));
//		//��ѡ��ListView���ݸ�ModuleListActivity��ͼ
//		//newһ��Intent���󣬲�ָ��class
//		Intent intent = new Intent();
//		intent.setClass(MessageActivity.this, MessageActivity.class);
//		
//		//newһ��Bundled���󣬲���Ҫ���ݵ����ݴ���
//		Bundle bundle = new Bundle();
//		bundle.putString("title", (String)mData.get(position).get("title"));
//		//��Bundle����assign��Intent
//		intent.putExtras(bundle);
//		//����ModuleListActivity
//		startActivity(intent);
	}
	
	/**
	 * listview
	 */
	public void showInfo(){
		new AlertDialog.Builder(this)
		.setTitle("listview")
		.setMessage("...")
		.setPositiveButton("button", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		})
		.show();
		
	}
	
	
	
	public final class ViewHolder{
		public ImageView img;
		public TextView title;
		public TextView info;
//		public Button viewBtn;
	}
	
	
	public class MyAdapter extends BaseAdapter{

		private LayoutInflater mInflater;
		
		
		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder = null;
			if (convertView == null) {
				
				holder=new ViewHolder();  
				
				convertView = mInflater.inflate(R.layout.activity_message, null);
				holder.img = (ImageView)convertView.findViewById(R.id.img_theme);
				holder.title = (TextView)convertView.findViewById(R.id.title_theme);
				holder.info = (TextView)convertView.findViewById(R.id.info_theme);
//				holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn_school);
				convertView.setTag(holder);
				
			}else {
				
				holder = (ViewHolder)convertView.getTag();
			}
			
			
			holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
			holder.title.setText((String)mData.get(position).get("title"));
			holder.info.setText((String)mData.get(position).get("info"));
		
			return convertView;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}
		
	}
	
	
	//�����˵���
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
//		mainMenu.createOptionsMenu(menu);
		return super.onCreateOptionsMenu(menu);
	}
	//���ò˵�
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO Auto-generated method stub
//		mainMenu.menuOpened(featureId, menu);
		return false;
	}
	
	//�����˳���Ӧ
	
	
}

