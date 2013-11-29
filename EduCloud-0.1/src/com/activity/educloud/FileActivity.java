package com.activity.educloud;

import java.util.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.file.educloud.File;
import com.file.educloud.fileFactory;
import com.utill.educloud.Contents;
import com.utill.educloud.DataServer;

public class FileActivity extends Activity {
    static final String TAG ="FileActivity";

	private ListView mListView;
    private FileListAdapter filelistadapter;
    private List<File> files;
    private String rootUuid;                     //�û���Ŀ¼ uuid
    private String curUuid;                       //�û���ǰĿ¼ uuid
    private FileActivity act;

    MyHandler myHandler;
    /**
     * ��ʼ���ļ�����
     */
    private void initFileData() {

        getFileData();
        act = this;
        rootUuid = "root";
        curUuid = rootUuid;
        List<File> files = new ArrayList<File>();
        //����ģ���ļ�
        int i = 0;
        for (i = 0; i < 6; i++) {
            File f = new File("", 0, i + "", R.drawable.documnet, R.drawable.not_selected, "u1");
            files.add(f);
            Log.i(TAG, f.toString());
        }
        this.files = files;

    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file);

        init();
        initFileData();   //��ʼ������
        initUI();
	}

    private void init() {
        myHandler = new MyHandler();
    }

    private void initUI() {
        mListView = (ListView) findViewById(R.id.ltv_file);
        filelistadapter = new FileListAdapter(this, files); //����������

    }

    class FileListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;  
        private Context mContext = null;
        private android.widget.Spinner fileselect = null;
        private List<File> mlistItems;
        public FileListAdapter(Context context,List<File> lt) {
            mContext = context;  
            mlistItems = lt;
            mInflater = LayoutInflater.from(mContext);  
        }  
        public Object getItem(int arg0) {  
            // TODO Auto-generated method stub  
            return null;  
        }  
        public long getItemId(int position) {  
            // TODO Auto-generated method stub  
            return 0;  
        }  
        public int getCount() {  
            // TODO Auto-generated method stub  
            return mlistItems.size();  
        }  
        public View getView(final int position, View convertView,
                android.view.ViewGroup parent) {  
            final ImageView indexImage;  
            final TextView indexText;
            final ImageButton indexButton;
            if (convertView == null) {  
                // ��item_custom.xml�ű�����  
                convertView = mInflater.inflate(R.layout.listview_fileitem, null);  
            }  
            indexImage = (ImageView) convertView.findViewById(R.id.imv_filetype);  
            indexText = (TextView) convertView.findViewById(R.id.tv_filename); 
            indexButton = (ImageButton)convertView.findViewById(R.id.imbtn_select); 
            
            indexText.setText(mlistItems.get(position).getName());
            //indexText.setTextColor(Color.RED);    
            indexImage.setBackgroundResource(mlistItems.get(position).getImgId());
            indexButton.setBackgroundResource(mlistItems.get(position).getBtnImgId());

            indexImage.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.i(TAG,mlistItems.get(position).toString());
                    File f = mlistItems.get(position);
                    if(f.getType()==0){                     //��������Ŀ¼ �����Ŀ¼    �׸�Ŀ¼Ϊ����

                        goinCategroy(f);
                    }
                }
            });
            indexButton.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					new AlertDialog.Builder(FileActivity.this)
					.setTitle("�ļ�ѡ��")
	                .setMultiChoiceItems(new String[] {"ɾ��","����","������"}, null, null)
	                .setPositiveButton("ȷ��", null)
	                .setNegativeButton("ȡ��", null)
	                .show();
				}
            	
            });
            
            indexButton.setOnTouchListener(new View.OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if(event.getAction() == MotionEvent.ACTION_DOWN){
						indexButton.setBackgroundResource(R.drawable.selected);
					}
					else if(event.getAction() == MotionEvent.ACTION_UP){
						indexButton.setBackgroundResource(R.drawable.not_selected);
					}
					return false;
				}
            	
            });
            return convertView;  
        }  
    }


    private void goinCategroy(File fl) {
        files.clear();
        if(fl.getUuid()!=rootUuid){      //�������Ĳ�Ϊ��Ŀ¼����ӷ����ļ���
            files.add(new File(curUuid, 0, "�����ϼ�...", R.drawable.documnet, R.drawable.not_selected, "u1"));
        }
        curUuid = fl.getUuid();
        //�˴���Ҫ�������� ��ѯ���� Ŀ¼�µ������ļ���Ϣ


        //����ģ���ļ�
        int i = 0;
        for (i = 0; i < 3; i++) {
            File f = new File(i+"", 0, i + "tttt", R.drawable.documnet, R.drawable.not_selected, "u1");
            files.add(f);
            Log.i(TAG, f.toString());
        }
        mListView = (ListView) findViewById(R.id.ltv_file);
        filelistadapter = new FileListAdapter(this, files); //����������
        mListView.setAdapter(filelistadapter);
    }

    /**
     * ȡ�÷������ļ�����
     */
    private void getFileData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    String mainifestData;
                    mainifestData = DataServer.getJsonString(Contents.getFileUrl);    //���mainfest����
                    Log.i(TAG,"get mainfest");
                    Map<String,String> mapMain = DataServer.getMainifest(mainifestData);     //����mainfest����
                    int i=0,length=0;
                    length=Integer.parseInt(mapMain.get("length"));                                      //��ȡ�ļ���Դ�ĸ���
                    Log.i(TAG,""+length);
//                    for(;i<length;i++){
//                        String fileKey = "id_"+i;
//                        DataSyn.getResourceString(Constants.GET_FILE_PATH+mapMain.get(fileKey),myDbHelper.getFilename(mapMain.get(fileKey)),Constants.dir_path_pic);
//                    }
//                    Log.i(TAG,"get file");

                    Message msg = new Message();
                    Bundle b = new Bundle();//�������
                    b.putString("msg", getString(R.string.FileActivity_msg_ok));
                    msg.setData(b);
                    FileActivity.this.myHandler.sendMessage(msg);
                }catch(Exception e){
                    Log.i("syn1",e.toString()  + "error");
                    Message msg = new Message();
                    Bundle b = new Bundle();//�������
                    b.putString("msg", getString(R.string.FileActivity_msg_error));
                    msg.setData(b);
                    FileActivity.this.myHandler.sendMessage(msg);
                }
            }
        }).start();
    }

    class MyHandler extends Handler {

        public MyHandler() {

        }
        public MyHandler(Looper L) {
            super(L);
        }

        //���������д�˷���,��������

        @Override

        public void handleMessage(Message msg) {

            // TODO Auto-generated method stub
            super.handleMessage(msg);

            //�˴����Ը���UI

            Bundle b = msg.getData();

            String error = b.getString("msg");
            mListView.setAdapter(filelistadapter);
            Toast.makeText(FileActivity.this, error, Toast.LENGTH_LONG).show();
        }

    }
}
