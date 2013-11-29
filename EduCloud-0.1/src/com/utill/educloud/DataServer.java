package com.utill.educloud;


import android.util.Log;
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: Appchina
 * Date: 13-8-15
 * Time: ����3:05
 *
 */
public class DataServer {
    static final String TAG= "DataServer";
    /**
     * ����������Դ
     * @param urlPath  ��Դ��ַ
     * @param fileName  ������ļ���
     * @param filePath  ������ļ���ַ
     * @throws Exception
     */
    public static void getResourceString(String urlPath, String fileName, String filePath) throws Exception {
        File extDir = new File(filePath);
        File fullFilename = new File(extDir, fileName);
        //File file = new File("a.mp3");    // c://test
        fullFilename.createNewFile();
        fullFilename.setWritable(Boolean.TRUE);
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        //��Ӧ���ַ�����ת��
        int size =0;
        FileOutputStream fos =null;
        BufferedInputStream bis = null;
        byte[] buf = new byte[8096];
        bis  = new BufferedInputStream(connection.getInputStream());
        fos = new FileOutputStream(fullFilename);
        while((size = bis.read(buf))!=-1){
            fos.write(buf,0,size);
        }
        fos.close();
        bis.close();
        connection.disconnect();
    }
    /**
     * ɾ��������Դ
     */
    public static void delFile(String filePath) {
        File extDir = new File(filePath);
        File[] files = extDir.listFiles();
        for(int i=0;i<files.length;i++){
             files[i].delete();
        }
    }


    /**
     * ��ȡ��ҳ����                  GET
     * @param urlPath  ·��
     * @return Json����
     * @throws Exception
     */

    public static String getJsonString(String urlPath) throws Exception {
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        //��Ӧ���ַ�����ת��
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str = null;
        StringBuffer sb = new StringBuffer();
        while ((str = bufferedReader.readLine()) != null) {
            sb.append(str);
        }
        reader.close();
        connection.disconnect();
        return sb.toString();
    }

    /**
     * ����manifest
     * @param jsonData json�ַ���
     * @return Map<String,String> - user -resources
     */
    public static Map<String, String> getMainifest(String jsonData) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        JsonReader reader = new JsonReader(new StringReader(jsonData));
        int i = 0;
        reader.beginObject();
        while (reader.hasNext()) {
            String tagName = reader.nextName();
            if (tagName.equals("user")) {
                map.put(tagName, reader.nextString());
            } else if (tagName.equals("resources")) {
                reader.beginArray();
                while (reader.hasNext()) {
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String tagName2 = reader.nextName();
                        if (tagName2.equals("id")) {
                            map.put("id_" + i, reader.nextString());
                        } else if (tagName2.equals("timestamp")) {
                            map.put("timestamp_" + i, reader.nextString());
                        }
                    }
                    i++;
                    reader.endObject();
                }
                reader.endArray();
            }
        }
        reader.endObject();
        Log.i(TAG,""+i);
        map.put("length",i+"");
        return map;
    }
}
