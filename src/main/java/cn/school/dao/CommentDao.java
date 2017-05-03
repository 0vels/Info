package cn.school.dao;

import cn.school.domain.Comment;
import cn.school.domain.Topic;

import java.io.Serializable;
import java.util.List;

/**
 * 在传统的jdbc操作中，我们需要手动管理数据库连接的开关，数据库资源访问的开关等等
 * <br/>但是,我们这里采用了Mybatis和Druid这两个框架，那么我们可以完全不必理会数据库连接等等的控制，
 * <br/>我们只需要更加专注于业务实现的开发。
 */
public interface CommentDao extends Dao<Comment> {
    int add(Comment user);

    int del(Comment user);

    int update(Comment user);

    Comment findOneById(Serializable Id);

    List<Comment> findAll(Serializable topicid);
}
