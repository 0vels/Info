package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/5/4.
 */
public class extradata implements Serializable {
    private String time;
    private String sendname;
    private int type;
    private String sendto;
    private long ID;



    public extradata(String itime,String isendname,int itype,String isendto,long id){
        this.sendname=isendname;
        this.type=itype;
        this.time=itime;
        this.sendto=isendto;
        this.ID=id;

    }


    public String getSendname() {
        return sendname;
    }
    public void setSendname(String sendname) {
        this.sendname = sendname;
    }
    public String  getSendto() {
        return sendto;
    }
    public void setSendto(String  sendto) {
        this.sendto = sendto;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public long getID() {
        return ID;
    }
    public void setID(long iID) {
        this.ID = iID;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "extradata{" +
                "time='" + time + '\'' +
                "sendname='" + sendname + '\'' +
                ", type='" + type + '\'' +
                ", sendto='" + sendto + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
