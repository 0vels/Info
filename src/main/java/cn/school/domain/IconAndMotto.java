package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/5/1.
 */
public class IconAndMotto implements Serializable {
    private String userid;
    private String motto;//座右铭
    private String icon;//头像

    public IconAndMotto(){

    }
    public IconAndMotto(String userid){
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public String toString() {
        return "IconAndMottoDao{" +
                "userid='" + userid + '\'' +
                ", icon='" + icon + '\'' +
                ", motto='" + motto + '\'' +
                '}';
    }
}
