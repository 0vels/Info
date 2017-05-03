package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/4/28.
 */
public class Picture implements Serializable {

    private String picid;//发起话题人的id
    private String topicid;//发起话题人的id
    private String picpath;//发起话题人的id

//    private String thumbPersonsNickname;//点赞人姓名
//    private String commentContents;//评论

    public Picture(){

    }

    public Picture(String topicid){
        this.topicid = topicid;
    }

    public Picture(String topicid, String picpath) {
        this.topicid = topicid;
        this.picpath = picpath;
//        this.thumbPersonsNickname = thumbPersonsNickname;
//        this.commentContents = commentContents;
    }

    public String getPicid() {
        return picid;
    }

    public void setPicid(String picid) {
        this.picid = picid;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }



    @Override
    public String toString() {
        return "PictureEntity{" +
                "picid='" + picid + '\'' +
                "topicid='" + topicid + '\'' +
                ", picpath='" + picpath + '\'' +
                '}';
    }

}
