package cn.school.service;

import cn.school.domain.Like;
import cn.school.domain.Topic;

import java.util.List;


public interface LikeService extends BaseService<Like> {

    void checkNull(Like t) throws Exception;

    void add(Like user)throws Exception;

    void del(Like user)throws Exception;

    void update(Like user)throws Exception;

    Like find(Like user)throws Exception;

    List<Like> findAll()throws Exception;
}
