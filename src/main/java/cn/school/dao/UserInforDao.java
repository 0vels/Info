package cn.school.dao;

import cn.school.domain.UserInfor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang on 2017/4/30.
 */
public interface UserInforDao extends Dao<UserInfor>  {

    int add(UserInfor userInfor);

    int del(UserInfor userInfor);

    int update(UserInfor userInfor);

    UserInfor findOneById(Serializable Id);

    List<UserInfor> findAll();
}
