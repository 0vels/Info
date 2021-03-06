package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/4/30.
 */
public class UserInfor implements Serializable {
    private String userid;
    private String mima;
    private String nicheng;
    private String xingming;
    private String xibie;
    private String banji;
    private String xuehao;
    private String xingbie;
    private String shengri;
    private String minzu;
    private String jia;
    private String xingqu;
    private String shenfen;
    private String touxiang;
    private String zuoyouming;
    private String phone;

    public UserInfor() {

    }

    public UserInfor(String userid) {
        this.userid = userid;
    }

    public UserInfor(String userid,String mima) {
        this.userid = userid;
        this.mima = mima;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public String getNicheng() {
        return nicheng;
    }

    public void setNicheng(String nicheng) {
        this.nicheng = nicheng;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
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

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getXingbie() {
        return xingbie;
    }

    public void setXingbie(String xingbie) {
        this.xingbie = xingbie;
    }

    public String getShengri() {
        return shengri;
    }

    public void setShengri(String shengri) {
        this.shengri = shengri;
    }

    public String getMinzu() {
        return minzu;
    }

    public void setMinzu(String minzu) {
        this.minzu = minzu;
    }

    public String getJia() {
        return jia;
    }

    public void setJia(String jia) {
        this.jia = jia;
    }

    public String getXingqu() {
        return xingqu;
    }

    public void setXingqu(String xingqu) {
        this.xingqu = xingqu;
    }

    public String getShenfen() {
        return shenfen;
    }

    public void setShenfen(String shenfen) {
        this.shenfen = shenfen;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getZuoyouming() {
        return zuoyouming;
    }

    public void setZuoyouming(String zuoyouming) {
        this.zuoyouming = zuoyouming;
    }

    @Override
    public String toString() {
        return "UserInforEntity{" +
                "userid='" + userid + '\'' +
                ", mima='" + mima + '\'' +
                ", nicheng='" + nicheng + '\'' +
                ", xingming='" + xingming + '\'' +
                ", xibie='" + xibie + '\'' +
                ", banji='" + banji + '\'' +
                ", xuehao='" + xuehao + '\'' +
                ", xingbie='" + xingbie + '\'' +
                ", shengri='" + shengri + '\'' +
                ", minzu='" + minzu + '\'' +
                ", jia='" + jia + '\'' +
                ", xingqu='" + xingqu + '\'' +
                ", shenfen='" + shenfen + '\'' +
                ", touxiang='" + touxiang + '\'' +
                ", zuoyouming='" + zuoyouming + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
