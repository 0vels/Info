package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/4/28.
 */
public class Topic implements Serializable {


    private String topicid;//话题的id
    private String authorid;//发起话题人的id
    private Object icon;//头像
    private String nickName;//昵称
    private String createtime;//发起时间
    private String content;//内容
//
//    private String thumbPersonsNickname;//点赞人姓名
//    private String commentContents;//评论

    public Topic(){
//        private String photos;//照片
    }

    public Topic(String topicid){
        this.topicid = topicid;
    }

    public Topic(String topicid,String authorid, Object icon, String nickName, String createtime, String content) {
        this.topicid = topicid;
        this.authorid = authorid;
        this.icon = icon;
        this.nickName = nickName;
        this.createtime = createtime;
        this.content = content;
//        this.photos = photos;
//        this.thumbPersonsNickname = thumbPersonsNickname;
//        this.commentContents = commentContents;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }


    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCreate_time() {
        return createtime;
    }

    public void setCreate_time(String createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public String getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(String photos) {
//        this.photos = photos;
//    }

//    public String getThumbPersonsNickname() {
//        if (thumbPersonsNickname==null){
//            thumbPersonsNickname=new ArrayList<>();
//        }
//        return thumbPersonsNickname;
//    }
//
//    public void setThumbPersonsNickname(List<String> thumbPersonsNickname) {
//        this.thumbPersonsNickname = thumbPersonsNickname;
//    }
//
//    public String getCommentContents() {
//        if (commentContents==null){
//            commentContents=new ArrayList<>();
//        }
//        return commentContents;
//    }
//
//    public void setCommentContents(List<CommentContent> commentPersons) {
//        this.commentContents = commentPersons;
//    }

    @Override
    public String toString() {
        return "TopicEntity{" +
                "topicid='" + topicid + '\'' +
                ", authorid='" + authorid + '\'' +
                ", icon='" + icon + '\'' +
                ", nickName='" + nickName + '\'' +
                ", createtime='" + createtime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
