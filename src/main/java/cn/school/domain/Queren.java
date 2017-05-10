package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/5/4.
 */
public class Queren implements Serializable {
    private int xuehao ;
    private long TID;//确认的通知ID
    private String userid;//
    private String queren;

    public Queren(String userid){
        this.userid=userid;
    }

    public Queren(String userid, long id){
        this.userid=userid;
        this.TID=id;
    }

    public Queren(String userid, int xh, long id){
        this.userid=userid;
        this.xuehao=xh;
        this.TID=id;
    }

    public String getQueren() {
        return queren;
    }

    public void setQueren(String queren) {
        this.queren = queren;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getXuehao() {
        return xuehao;
    }
    public void setXuehao(int xuehao) {
        this.xuehao = xuehao;
    }
    public long getTID() {
        return TID;
    }
    public void setTID(long tID) {
        TID = tID;
    }

    @Override
    public String toString() {
        return "Queren{" +
                "TID='" + TID + '\'' +
                "xuehao='" + xuehao + '\'' +
                "userid='" + userid + '\'' +
                "queren='" + queren + '\'' +
                '}';
    }
}
