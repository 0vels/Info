package cn.school.service.serviceImpl;

import cn.school.dao.OpenimUserDao;
import cn.school.dao.UserDao;
import cn.school.domain.OpenimUser;
import cn.school.domain.User;
import cn.school.exception.*;
import cn.school.service.OpenimUserService;
import cn.school.service.UserService;
import cn.school.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("openimuserService")
public class OpenimUserServiceImpl implements OpenimUserService {

    @Autowired
    private OpenimUser user;

    public void checkNull(OpenimUser user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException {
        //先检查用户是否存在
        if (null == user) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("用户不能为空");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(user.getUserid())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("用户名不能为空");
        }
        //用户密码不能为空检查
        if (StringUtils.isEmpty(user.getPassword())) {
            //抛出用户密码为空的自定义异常
            throw new UserPwdCanNotBeNullException("用户密码不能为空");
        }
    }

    @Override
    public void add(OpenimUser user) throws Exception {

    }

    @Override
    public void del(OpenimUser user) throws Exception {

    }

    @Override
    public void update(OpenimUser user) throws Exception {

    }

    @Override
    public OpenimUser find(OpenimUser user) throws Exception {
        return null;
    }

    @Override
    public List<OpenimUser> findAll() throws Exception {
        return null;
    }


}
