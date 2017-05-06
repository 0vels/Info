package cn.school.domain;

import cn.school.utils.JSONtool;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by wang on 2017/5/4.
 */

public class TongZhi implements Serializable{
    /*TID	UID	Tleixing	fabu	neirong	biaoti*/
    private long TID;
    private int isSelectAll;//发送时是否选择全选标志
    private String sendPersonName;//发送通知姓名
    private int messageType;//通知类型 1：重要 0 不重要
    private String time;
    //通知发布时间，服务器接收到老师发来的时间
    private String content;//通知内容
    private String title;//通知标题
    private String sendTo;//接收群体




    public TongZhi() {

    }

    public TongZhi(int messageType, String sendPersonName, String title, String icontent, String sendTo) {
        this.content = icontent;
        this.sendPersonName = sendPersonName;

        this.title = title;
        messageType = messageType;
        this.sendTo = sendTo;

    }

    public TongZhi(int imessageType, String sendPersonName, String title, String icontent, String sendTo, long ID, String time) {
        this.content = icontent;
        this.sendPersonName = sendPersonName;

        this.title = title;
        imessageType = imessageType;
        this.sendTo = sendTo;
        this.TID = ID;
        this.time = time;
    }

//    public static void main(String[] args) {
//        JSONtool jt = new JSONtool();
//        String[] name = {"计算机", "云计算"};
//        TongZhi itz = new TongZhi(1, "tom", "biaoti", "neirong", name);
//        String jsonstr = jt.Tojsonstr(itz);
//        TongZhi tz = jt.jsontotz(jsonstr, TongZhi.class);
//
//        extradata extra = new extradata("2017.4.23", "tom", 0, name, 1229125632);
//        Calendar now = Calendar.getInstance();
//        System.out.println(now.getTime());
//        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
//        String dateNowStr = sdf.format(now.getTime());
//        System.out.println("格式化后的日期：" + dateNowStr);
//        long id = Integer.parseInt("1229125632");
//        System.out.println(id);
//
//
//        System.out.println(jt.Tojsonstr(extra));
//    }

    public String getSendPersonName() {
        return sendPersonName;
    }

    public void setSendPersonName(String sendPersonName) {
        this.sendPersonName = sendPersonName;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getnames() {
        return sendTo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIsSelectAll() {
        return isSelectAll;
    }

    public void setIsSelectAll(int isSelectAll) {
        this.isSelectAll = isSelectAll;
    }

    public long getTID() {
        return TID;
    }

    public void setTID(long tID) {
        TID = tID;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public String toString() {
        return "TongZhi{" +
                "TID='" + TID + '\'' +
                "isSelectAll='" + isSelectAll + '\'' +
                ", sendPersonName='" + sendPersonName + '\'' +
                ", messageType='" + messageType + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", sendTo='" + sendTo + '\'' +
                '}';
    }

}


