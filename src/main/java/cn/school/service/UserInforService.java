package cn.school.service;

import cn.school.domain.OpenimUser;
import cn.school.domain.OpenimUser;
import cn.school.domain.UserInfor;

import java.util.List;

/**
 * Created by wang on 2017/4/30.
 */
public interface UserInforService extends BaseService<UserInfor>{

    void checkNull(UserInfor t) throws Exception;

    void add(UserInfor userInfor)throws Exception;

    void del(UserInfor userInfor)throws Exception;

    void update(UserInfor userInfor)throws Exception;

    UserInfor find(UserInfor userInfor)throws Exception;

    List<UserInfor> findAll()throws Exception;
}
