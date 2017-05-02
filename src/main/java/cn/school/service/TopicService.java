package cn.school.service;

import cn.school.domain.Topic;
import cn.school.domain.User;

import java.util.List;


public interface TopicService extends BaseService<Topic> {

    void checkNull(Topic t) throws Exception;

    void add(Topic user)throws Exception;

    void del(Topic user)throws Exception;

    void update(Topic user)throws Exception;

    Topic find(Topic user)throws Exception;

    List<Topic> findAll()throws Exception;
}
