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
    private OpenimUserDao openimUserDao;

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
    public void add(OpenimUser openimUser) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, UserAireadyExistException, OtherThingsException {
            //先检查用户是否存在
        if (null == openimUser) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("User can not  be Null");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(openimUser.getUserid())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("User name can not be Null");
        }
        //用户密码不能为空检查
        if (StringUtils.isEmpty(openimUser.getPassword())) {
            //抛出用户密码为空的自定义异常
            throw new UserPwdCanNotBeNullException("User name can not be Null");
        }
        //由于我这个是仓库管理系统，根据业务需求来说，我们的用户基本信息都是不能为空的
        //基本信息包括：姓名、年龄、用户名、密码、性别、手机号，年龄大于18
        if (StringUtils.isEmpty(openimUser.getCareer())
                || openimUser.getAge() > 18
                || StringUtils.isEmpty(openimUser.getMobile())) {
            //其他综合异常
            throw new OtherThingsException("Some use's base info can not be null");
        }
        //已经存在相同用户
        if (null != openimUserDao.findOneById(openimUser.getUserid())) {
            //存在相同的用户异常
            throw new UserAireadyExistException("Register User Failed，Because the  user Aiready exist");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = openimUserDao.add(openimUser);
        } catch (Exception e) {
            System.out.println("添加用户失败,用户已经存在");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加用户成功");
    }

    @Override
    public void del(OpenimUser openimUser) throws Exception {

    }

    @Override
    public void update(OpenimUser openimUser) throws Exception {

    }

    @Override
    public OpenimUser find(OpenimUser openimUser) throws Exception {
        return null;
    }

    @Override
    public List<OpenimUser> findAll() throws Exception {
        return null;
    }


}
