package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/4/28.
 */
public class Comment implements Serializable {


    private String commentid;
    private String topicid;
    private String senderNickname;
    private String receiverNickname;
    private String content;



    public Comment(){

    }

    public Comment(String commentid){
        this.commentid = commentid;
    }
    public Comment(String topicid,String commentid){
        this.topicid = topicid;
    }


    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public String getSenderNickname() {
        return senderNickname;
    }

    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }

    public String getReceiverNickname() {
        return receiverNickname;
    }

    public void setReceiverNickname(String receiverNickname) {
        this.receiverNickname = receiverNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "commentid='" + commentid + '\'' +
                "topicid='" + topicid + '\'' +
                "senderNickname='" + senderNickname + '\'' +
                "receiverNickname='" + receiverNickname + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
