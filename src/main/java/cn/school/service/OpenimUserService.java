package cn.school.service;

import cn.school.domain.OpenimUser;
import cn.school.domain.User;

import java.util.List;

/**
 * Created by wang on 2017/4/19.
 */
public interface OpenimUserService extends BaseService<OpenimUser> {

    void checkNull(OpenimUser t) throws Exception;

    void add(OpenimUser openimUser)throws Exception;

    void del(OpenimUser openimUser)throws Exception;

    void update(OpenimUser openimUser)throws Exception;

    OpenimUser find(OpenimUser openimUser)throws Exception;

    List<OpenimUser> findAll()throws Exception;
}
