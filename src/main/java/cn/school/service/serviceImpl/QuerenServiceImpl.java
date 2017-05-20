package cn.school.service.serviceImpl;

import cn.school.dao.CommentDao;
import cn.school.dao.LikeDao;
import cn.school.dao.QuerenDao;
import cn.school.dao.TopicDao;
import cn.school.domain.Comment;
import cn.school.domain.Like;
import cn.school.domain.Topic;
import cn.school.domain.Queren;
import cn.school.exception.*;
import cn.school.service.QuerenService;
import cn.school.service.TopicService;
import cn.school.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("querenService")
public class QuerenServiceImpl implements QuerenService {

    @Autowired
    private QuerenDao querenDao;



    public void checkNull(Queren user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException {
        //先检查用户是否存在
        if (null == user) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("用户不能为空");
        }
//        //用户名不能为空检查
//        if (StringUtils.isEmpty(user.getTopicid())) {
//            //抛出用户名为空的自定义异常
//            throw new UserNameCanNotBeNullException("话题不能为空");
//        }

    }
//    /**
//     * 添加用户，一般来说需要检查用户为空、用户名为空、密码为空
//     */
    public void add(Queren user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, UserAireadyExistException, OtherThingsException {
        //先检查用户是否存在
        if (null == user) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("确认信息不能为空");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(user.getUserid())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("UserID不能为空");
        }

        //用户名不能为空检查
        if (user.getTID()==0) {
            //抛出用户名为空的自定义异常
            throw new UserPwdCanNotBeNullException("通知ID不能为空");
        }

        if (null != querenDao.findOneByuserIDAndTID(user)) {
            //存在相同的用户异常
            throw new UserAireadyExistException("用户已存在");
        }

        int result = 0; //受影响的行数默认为0
        try {
            result = querenDao.add(user);
        } catch (Exception e) {
            System.out.println("确认失败");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("确认成功");
    }



    public Queren find(Queren user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException {
        final  Queren user_find = new Queren();
//
//        //查找用户
//        if (null != querenDao.findOneById(user.getTopicid())) {
//            user_find = querenDao.findOneById(user.getTopicid());
//        }else {
//            user_find = new Topic();
//            throw new OtherThingsException("话题不存在");
//        }
//
        return user_find;
    }

    /**
     * 删除
     * @param user
     *
     */
    @Override
    public void del(Queren user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException  {
//        checkNull(user);
//        //用户不存在
//        if (null == querenDao.findOneById(user.getTopicid())) {
//            throw new OtherThingsException("话题不存在");
//        }
//        int result = 0; //受影响的行数默认为0
//        try {
//            result = querenDao.del(user);
//        } catch (Exception e) {
//            System.out.println("删除话题失败"+e.getMessage());
//            //其他失败异常
//            throw new OtherThingsException("删除话题失败,"+e.getMessage());
//        }
//        if (result > 0)
//            System.out.println("删除话题成功");
    }
    /**
     * 更新
     * @param user
     *
     */
    @Override
    public void update(Queren user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException  {
//        checkNull(user);
//        //用户不存在
//        if (null == querenDao.findOneById(user.getTopicid())) {
//            throw new OtherThingsException("话题不存在");
//        }
//        int result = 0; //受影响的行数默认为0
//        try {
//            result = querenDao.update(user);
//        } catch (Exception e) {
//            System.out.println("更新话题失败"+e.getMessage());
//            //其他失败异常
//            throw new OtherThingsException("更新话题失败,"+e.getMessage());
//        }
//        if (result > 0)
//            System.out.println("更新话题成功");
    }


    @Override
    public List<Queren> findAll() throws  OtherThingsException  {

        return querenDao.findAll();
    }

}
