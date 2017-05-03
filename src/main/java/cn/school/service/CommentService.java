package cn.school.service;

import cn.school.domain.Comment;
import cn.school.domain.Topic;

import java.util.List;


public interface CommentService extends BaseService<Comment> {

    void checkNull(Comment t) throws Exception;

    void add(Comment user)throws Exception;

    void del(Comment user)throws Exception;

    void update(Comment user)throws Exception;

    Comment find(Comment user)throws Exception;

    List<Comment> findAll()throws Exception;
}
