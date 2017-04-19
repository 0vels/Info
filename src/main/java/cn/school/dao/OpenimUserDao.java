package cn.school.dao;

import cn.school.domain.OpenimUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang on 2017/4/19.
 */
public interface OpenimUserDao extends Dao<OpenimUser> {
    int add(OpenimUser user);

    int del(OpenimUser user);

    int update(OpenimUser user);

    OpenimUser findOneById(Serializable Id);

    List<OpenimUser> findAll();
}

