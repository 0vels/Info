package cn.school.service;

import cn.school.domain.User;

import java.util.List;


public interface UserService extends BaseService<User> {

    void checkNull(User t) throws Exception;

    void add(User user)throws Exception;

    void del(User user)throws Exception;

    void update(User user)throws Exception;

    User find(User user)throws Exception;

    List<User> findAll()throws Exception;
}
