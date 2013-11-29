package com.file.educloud;

/**
 * Created with IntelliJ IDEA.
 * User: save
 * Date: 13-11-16
 * Time: ����3:29
 * To change this template use File | Settings | File Templates.
 */
public class File {
    String uuid;                //�ļ�uuid      ��Ψһ��ʶ��
    int type;                   //����
    String name;                //�ļ���
    int imgId;               //�ļ�ͼ��id
    int btnImgId;            //��ť��ʽid
    String user;                //�����û�


    public File(String uuid, int type, String name, int imgId,int btnImgId, String user) {
        this.uuid = uuid;
        this.type = type;
        this.name = name;
        this.imgId = imgId;
        this.btnImgId = btnImgId;
        this.user = user;
    }

    public File() {
        this.uuid = "";
        this.type = -1;
        this.name = "";
        this.imgId = -1;
        this.user = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public String getUuid() {
        return uuid;
    }

    public int getType() {
        return type;
    }

    public int getBtnImgId() {
        return btnImgId;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }

    public String toString(){
         return "The File is " +"uuid:"+ uuid+ "type:"+type+"name:"+name+"imgId:"+imgId+"user:"+user;
    }
}
