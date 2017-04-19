package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/4/11.
 */
public class OpenimUser implements Serializable {

//    private String nick;    //名字
//    private String userid; //登陆ID
//    private String password;    //密码
//    private int age;    //年龄
//    private String mobile;  //手机号
//    private String icon_url;    //头像地址
//    private boolean used = true;   //是否可用,默认值是true
//
//    public String getNick() {
//        return nick;
//    }
//
//    public void setNick(String nick) {
//        this.nick = nick;
//    }
//
//    public String getUserid() {
//        return userid;
//    }
//
//    public void setUserid(String userid) {
//        this.userid = userid;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getIcon_url() {
//        return icon_url;
//    }
//
//    public void setIcon_url(String icon_url) {
//        this.icon_url = icon_url;
//    }
//
//    public boolean isUsed() {
//        return used;
//    }
//
//    public void setUsed(boolean used) {
//        this.used = used;
//    }
//
////    @Override
////    public String toString() {
////        return "User{" +
////                "name='" + name + '\'' +
////                ", sex='" + sex + '\'' +
////                ", loginId='" + loginId + '\'' +
////                ", pwd='" + pwd + '\'' +
////                ", duty='" + duty + '\'' +
////                ", age=" + age +
////                ", cellNumber='" + cellNumber + '\'' +
////                ", photoUrl='" + photoUrl + '\'' +
////                ", used=" + used +
////                '}';
////    }


    /**
     * Auto-generated: 2017-04-19 0:40:36
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
//    public class JsonRootBean {

    private String address;
    private int age;
    private String career;
    private String email;
    private String extra;
    private String gender;
    private String gmt_modified;
    private String icon_url;
    private String mobile;
    private String name;
    private String nick;
    private String password;
    private String qq;
    private String remark;
    private int status;
    private String taobaoid;
    private String userid;
    private String vip;
    private String wechat;
    private String weibo;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(String gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getCareer() {
        return career;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getExtra() {
        return extra;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGmtModified(String gmtModified) {
        this.gmt_modified = gmtModified;
    }

    public String getGmtModified() {
        return gmt_modified;
    }

    public void setIconUrl(String iconUrl) {
        this.icon_url = iconUrl;
    }

    public String getIconUrl() {
        return icon_url;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getQq() {
        return qq;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setTaobaoid(String taobaoid) {
        this.taobaoid = taobaoid;
    }

    public String getTaobaoid() {
        return taobaoid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getVip() {
        return vip;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getWeibo() {
        return weibo;
    }

//    }
}

