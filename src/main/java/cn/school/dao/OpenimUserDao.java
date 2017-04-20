package cn.school.dao;

import cn.school.domain.OpenimUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang on 2017/4/19.
 */
public interface OpenimUserDao extends Dao<OpenimUser> {
    int add(OpenimUser openimUser);

    int del(OpenimUser openimUser);

    int update(OpenimUser openimUser);

    OpenimUser findOneById(Serializable Id);

    List<OpenimUser> findAll();
}

