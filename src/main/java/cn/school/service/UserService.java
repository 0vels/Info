package cn.school.service;

import cn.school.domain.User;


public interface UserService extends BaseService<User> {

    void add(User user) throws Exception;
}
