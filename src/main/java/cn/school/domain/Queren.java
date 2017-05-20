package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/5/4.
 */
public class Queren implements Serializable {


    private String userid;
    private String name;
    private String xibie;
    private String banji;
    private String phone;
    private String queren;
    private String queshi;
    private String xuehao;
    private int TID;//确认的通知ID
    private int id;

    public Queren(){

    }

    public Queren(String userid){
        this.userid=userid;
    }

    public Queren(String userid, int TID){
        this.userid=userid;
        this.TID=TID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXibie() {
        return xibie;
    }

    public void setXibie(String xibie) {
        this.xibie = xibie;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQueshi() {
        return queshi;
    }

    public void setQueshi(String queshi) {
        this.queshi = queshi;
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


    public int getTID() {
        return TID;
    }
    public void setTID(int tID) {
        TID = tID;
    }


    @Override
    public String toString() {
        return "Queren{" +
                "userid='" + userid + '\'' +
                "name='" + name + '\'' +
                "xibie='" + xibie + '\'' +
                "banji='" + banji + '\'' +
                "phone='" + phone + '\'' +
                "queren='" + queren + '\'' +
                "queshi='" + queshi + '\'' +
                "xuehao='" + xuehao + '\'' +
                "TID='" + TID + '\'' +
                '}';
    }

}
