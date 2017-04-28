//package cn.school.domain;
//
///**
// * Created by wang on 2017/4/28.
// */
//public class Topic {
//
//    private String id;//发起话题人的id
//    private Object icon;//头像
//    private String nickName;//昵称
//    private String create_time;//发起时间
//    private String content;//内容
//    private String photos;//照片
//    private String thumbPersonsNickname;//点赞人姓名
//    private String commentContents;//评论
//
//    public TopicEntity(String id, Object icon, String nickName, String create_time, String content, List<Object> photos, List<String> thumbPersonsNickname, List<CommentContent> commentContents) {
//        this.id = id;
//        this.icon = icon;
//        this.nickName = nickName;
//        this.create_time = create_time;
//        this.content = content;
//        this.photos = photos;
//        this.thumbPersonsNickname = thumbPersonsNickname;
//        this.commentContents = commentContents;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public Object getIcon() {
//        return icon;
//    }
//
//    public void setIcon(Object icon) {
//        this.icon = icon;
//    }
//
//    public String getNickName() {
//        return nickName;
//    }
//
//    public void setNickName(String nickName) {
//        this.nickName = nickName;
//    }
//
//    public String getCreate_time() {
//        return create_time;
//    }
//
//    public void setCreate_time(String create_time) {
//        this.create_time = create_time;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<Object> photos) {
//        this.photos = photos;
//    }
//
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
//
//    @Override
//    public String toString() {
//        return "TopicEntity{" +
//                "id='" + id + '\'' +
//                ", icon='" + icon + '\'' +
//                ", nickName='" + nickName + '\'' +
//                ", create_time='" + create_time + '\'' +
//                ", content='" + content + '\'' +
//                ", photos=" + photos +
//                ", thumbPersonsNickname=" + thumbPersonsNickname +
//                ", commentPersons=" + commentContents +
//                '}';
//    }
//
//}
