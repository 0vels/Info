package cn.school.service.serviceImpl;

import cn.school.dao.IconAndMottoDao;
import cn.school.dao.UserInforDao;
import cn.school.domain.IconAndMotto;
import cn.school.domain.UserInfor;
import cn.school.exception.*;
import cn.school.service.IconAndMottoService;
import cn.school.service.UserInforService;
import cn.school.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wang on 2017/4/30.
 */

@Service("iconAndMottoService")
public class IconAndMottoServiceImpl implements IconAndMottoService {

    @Autowired
    private IconAndMottoDao iconAndMottoDao;

    public void checkNull(IconAndMotto iconAndMotto) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException {
        //先检查用户是否存在
        if (null == iconAndMotto) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("用户不能为空");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(iconAndMotto.getUserid())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("用户名不能为空");
        }
//        //用户密码不能为空检查
//        if (StringUtils.isEmpty(userInfor.ge())) {
//            //抛出用户密码为空的自定义异常
//            throw new UserPwdCanNotBeNullException("用户密码不能为空");
//        }
    }
    /**
     * 添加用户，一般来说需要检查用户为空、用户名为空、密码为空
     */
    public void add(IconAndMotto iconAndMotto) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, UserAireadyExistException, OtherThingsException {
        //先检查用户是否存在
        if (null == iconAndMotto) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("UserInfor can not be Null");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(iconAndMotto.getUserid())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("用户名不能为空");
        }
//        //用户密码不能为空检查
//        if (StringUtils.isEmpty(userInfor.getPwd())) {
//            //抛出用户密码为空的自定义异常
//            throw new UserPwdCanNotBeNullException("UserInfor name can not be Null");
//        }
        //由于我这个是仓库管理系统，根据业务需求来说，我们的用户基本信息都是不能为空的
//        //基本信息包括：姓名、年龄、用户名、密码、性别、手机号，年龄大于18
//        if (StringUtils.isEmpty(userInfor.getDuty())
//                || StringUtils.isEmpty(userInfor.getSex())
//                || userInfor.getAge() > 18
//                || StringUtils.isEmpty(userInfor.getCellNumber())) {
//            //其他综合异常
//            throw new OtherThingsException("Some use's base info can not be null");
//        }
        //已经存在相同用户
        if (null != iconAndMottoDao.findOneById(iconAndMotto.getUserid())) {
            //存在相同的用户异常
            throw new UserAireadyExistException("用户已存在");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = iconAndMottoDao.add(iconAndMotto);
        } catch (Exception e) {
            System.out.println("添加用户信息失败");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("添加用户信息成功");
    }


    /**
     * 查找用户
     */
    public IconAndMotto find(IconAndMotto iconAndMotto) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException {
        final  IconAndMotto user_find ;
        checkNull(iconAndMotto);
        //查找用户
        if (null != iconAndMottoDao.findOneById(iconAndMotto.getUserid())) {
            user_find = iconAndMottoDao.findOneById(iconAndMotto.getUserid());
        }else {
            user_find = new IconAndMotto();
            throw new OtherThingsException("用户不存在");
        }

        return user_find;
    }

    /**
     * 删除
     * @param iconAndMotto
     *
     */
    @Override
    public void del(IconAndMotto iconAndMotto) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException  {
        checkNull(iconAndMotto);
        //用户不存在
        if (null == iconAndMottoDao.findOneById(iconAndMotto.getUserid())) {
            throw new OtherThingsException("用户不存在");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = iconAndMottoDao.del(iconAndMotto);
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
     * @param userInfor
     *
     */
    @Override
    public void update(IconAndMotto userInfor) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException  {
        checkNull(userInfor);
        //用户不存在
        if (null == iconAndMottoDao.findOneById(userInfor.getUserid())) {
            throw new OtherThingsException("用户不存在");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = iconAndMottoDao.update(userInfor);
        } catch (Exception e) {
            System.out.println("更新用户失败"+e.getMessage());
            //其他失败异常
            throw new OtherThingsException("更新用户失败,"+e.getMessage());
        }
        if (result > 0)
            System.out.println("更新用户成功");
    }


    @Override
    public List<IconAndMotto> findAll() throws  OtherThingsException  {

        return iconAndMottoDao.findAll();
    }

}
