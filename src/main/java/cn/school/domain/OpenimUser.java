package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/4/11.
 */
public class OpenimUser implements Serializable {

    private String name;    //名字
    private String sex; //性别
    private String loginId; //登陆ID
    private String pwd;    //密码
    private String duty;    //职务
    private int age;    //年龄
    private String cellNumber;  //手机号
    private String photoUrl;    //头像地址
    private boolean used = true;   //是否可用,默认值是true

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", loginId='" + loginId + '\'' +
                ", pwd='" + pwd + '\'' +
                ", duty='" + duty + '\'' +
                ", age=" + age +
                ", cellNumber='" + cellNumber + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", used=" + used +
                '}';
    }
}

