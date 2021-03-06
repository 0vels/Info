package cn.school.service.serviceImpl;

import cn.school.dao.CommentDao;
import cn.school.dao.LikeDao;
import cn.school.dao.TopicDao;
import cn.school.domain.Comment;
import cn.school.domain.Like;
import cn.school.domain.Topic;
import cn.school.exception.*;
import cn.school.service.CommentService;
import cn.school.service.TopicService;
import cn.school.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao userDao;



    public void checkNull(Comment user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException {
        //先检查用户是否存在
        if (null == user) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("用户不能为空");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(user.getTopicid())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("话题不能为空");
        }

    }
    /**
     * 添加用户，一般来说需要检查用户为空、用户名为空、密码为空
     */
    public void add(Comment user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, UserAireadyExistException, OtherThingsException {
        //先检查用户是否存在
        if (null == user) {
            //抛出用户为空的自定义异常
            throw new UserCanNotBeNullException("用户不能为空");
        }
        //用户名不能为空检查
        if (StringUtils.isEmpty(user.getTopicid())) {
            //抛出用户名为空的自定义异常
            throw new UserNameCanNotBeNullException("消息ID不能为空");
        }

        //由于我这个是仓库管理系统，根据业务需求来说，我们的用户基本信息都是不能为空的
        //基本信息包括：姓名、年龄、用户名、密码、性别、手机号，年龄大于18
//        if (StringUtils.isEmpty(user.getContent())) {
//            //其他综合异常
//            throw new OtherThingsException("消息不能为空");
//        }
        //已经存在相同用户
//        if (null != userDao.findOneById(user.getTopicid())) {
//            //存在相同的用户异常
//            throw new UserAireadyExistException("话题重复");
//        }
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.add(user);

        } catch (Exception e) {
            System.out.println("话题发送失败");
            //其他用户添加失败异常
            throw new OtherThingsException(e);
        }
        if (result > 0)
            System.out.println("话题发送成功");
    }


    /**
     * 查找用户
     */
    public Comment find(Comment user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException {
        final  Comment user_find ;
        checkNull(user);
        //查找用户
        if (null != userDao.findOneById(user.getTopicid())) {
            user_find = userDao.findOneById(user.getTopicid());
        }else {
            user_find = new Comment();
            throw new OtherThingsException("话题不存在");
        }

        return user_find;
    }

    /**
     * 删除
     * @param user
     *
     */
    @Override
    public void del(Comment user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException  {
//        checkNull(user);
        //用户不存在
        if (null == userDao.findOneById(user.getCommentid())) {
            throw new OtherThingsException("评论不存在");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.del(user);
        } catch (Exception e) {
            System.out.println("删除评论失败"+e.getMessage());
            //其他失败异常
            throw new OtherThingsException("删除评论失败,"+e.getMessage());
        }
        if (result > 0)
            System.out.println("删除评论成功");
    }
    /**
     * 更新
     * @param user
     *
     */
    @Override
    public void update(Comment user) throws UserCanNotBeNullException, UserNameCanNotBeNullException, UserPwdCanNotBeNullException, OtherThingsException  {
        checkNull(user);
        //用户不存在
        if (null == userDao.findOneById(user.getTopicid())) {
            throw new OtherThingsException("话题不存在");
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.update(user);
        } catch (Exception e) {
            System.out.println("更新话题失败"+e.getMessage());
            //其他失败异常
            throw new OtherThingsException("更新话题失败,"+e.getMessage());
        }
        if (result > 0)
            System.out.println("更新话题成功");
    }


    @Override
    public List<Comment> findAll() throws  OtherThingsException  {

        return userDao.findAll();
    }

}
