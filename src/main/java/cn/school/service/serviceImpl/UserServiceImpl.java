package cn.school.service.serviceImpl;

import cn.school.dao.UserDao;
import cn.school.domain.User;
import cn.school.exception.*;
import cn.school.service.UserService;
import cn.school.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public void checkNull(User user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException {
        //先检查用户是否存在
        if (null == user) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("用户不能为空");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(user.getLoginId())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("用户名不能为空");
        }
        //用户密码不能为空检查
        if (StringUtils.isEmpty(user.getPwd())) {
            //抛出用户密码为空的自定义异常
            throw new UserPwdCanNotBeNullException("用户密码不能为空");
        }
    }
    /**
     * 添加用户，一般来说需要检查用户为空、用户名为空、密码为空
     */
    public void add(User user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, UserAireadyExistException, OtherThingsException {
        //先检查用户是否存在
        if (null == user) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("User can not be Null");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(user.getLoginId())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("User name can not be Null");
        }
        //用户密码不能为空检查
        if (StringUtils.isEmpty(user.getPwd())) {
            //抛出用户密码为空的自定义异常
            throw new UserPwdCanNotBeNullException("User name can not be Null");
        }
        //由于我这个是仓库管理系统，根据业务需求来说，我们的用户基本信息都是不能为空的
        //基本信息包括：姓名、年龄、用户名、密码、性别、手机号，年龄大于18
        if (StringUtils.isEmpty(user.getDuty())
                || StringUtils.isEmpty(user.getSex())
                || user.getAge() > 18
                || StringUtils.isEmpty(user.getCellNumber())) {
            //其他综合异常
            throw new OtherThingsException("Some use's base info can not be null");
        }
        //已经存在相同用户
        if (null != userDao.findOneById(user.getLoginId())) {
            //存在相同的用户异常
            throw new UserAireadyExistException("Register User Failed，Because the  user Aiready exist");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.add(user);
        } catch (Exception e) {
            System.out.println("添加用户失败,用户已经存在");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加用户成功");
    }


    /**
     * 查找用户
     */
    public User find(User user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException {
        final  User user_find ;
        checkNull(user);
        //查找用户
        if (null != userDao.findOneById(user.getLoginId())) {
            user_find = userDao.findOneById(user.getLoginId());
        }else {
            user_find = new User();
            throw new OtherThingsException("用户不存在");
        }

        return user_find;
    }

    /**
     * 删除
     * @param user
     *
     */
    @Override
    public void del(User user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException  {
        checkNull(user);
        //用户不存在
        if (null == userDao.findOneById(user.getLoginId())) {
            throw new OtherThingsException("用户不存在");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.del(user);
        } catch (Exception e) {
            System.out.println("删除用户失败"+e.getMessage());
            //其他失败异常
            throw new OtherThingsException("删除用户失败,"+e.getMessage());
        }
        if (result > 0)
            System.out.println("删除用户成功");
    }
    /**
     * 更新
     * @param user
     *
     */
    @Override
    public void update(User user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException  {
        checkNull(user);
        //用户不存在
        if (null == userDao.findOneById(user.getLoginId())) {
            throw new OtherThingsException("用户不存在");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.update(user);
        } catch (Exception e) {
            System.out.println("更新用户失败"+e.getMessage());
            //其他失败异常
            throw new OtherThingsException("更新用户失败,"+e.getMessage());
        }
        if (result > 0)
            System.out.println("更新用户成功");
    }


    @Override
    public List<User> findAll() throws  OtherThingsException  {

        return userDao.findAll();
    }

}
