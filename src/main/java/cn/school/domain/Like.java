package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/5/2.
 */
public class Like implements Serializable {
    private String topicid;//会话的id
    private String userid;//点赞人的id
    private String nickName;//昵称

    public Like( ){
    }

    public Like(String topicid){
        this.topicid= topicid;
    }

    public Like(String topicid,String userid){
        this.topicid= topicid;
        this.userid= userid;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "LikeEntity{" +
                "topicid='" + topicid + '\'' +
                ", userid='" + userid + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
