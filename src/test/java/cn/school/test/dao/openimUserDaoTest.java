package cn.school.test.dao;

import cn.school.dao.OpenimUserDao;
import cn.school.dao.UserDao;
import cn.school.domain.OpenimUser;
import cn.school.domain.User;
import cn.school.openim.OpenimUserController;
import cn.school.test.BaseTest;
import com.taobao.api.ApiException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/9/25.
 */
public class openimUserDaoTest extends BaseTest {

    @Autowired
    private OpenimUserDao openimUserDao;

    @Test
    public void testget() {
        OpenimUser user = new OpenimUser();
        String userId = "wangzhennan";
        try {
            OpenimUserController openimUserController = new OpenimUserController();
            openimUserController.getIMUser(userId);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        int result = 0; //受影响的行数默认为0
        try {
            result = openimUserDao.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加用户失败");
        }
        if (result > 0)
            System.out.println("添加用户成功");
    }

//    @Test
//    public void testFindOneId() throws Exception {
//        User user = new User();
//        user.setLoginId("pc147852369");
//        User result = null; //受影响的行数默认为0
//        try {
//            result = userDao.findOneById(user.getLoginId());
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("查找用户失败");
//        }
//        if (null != result)
//            System.out.println("查找用户成功\n" + result.toString());
//    }
//
//    @Test
//    public void testDel() {
//        User user = new User();
//        user.setLoginId("pc147852369");
//        int result = 0; //受影响的行数默认为0
//        try {
//            result = userDao.del(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("删除用户失败");
//        }
//        if (result > 0)
//            System.out.println("删除用户成功");
//    }
//
//    @Test
//    public void testUpdate() {
//        User user = new User();
//        user.setLoginId("pc147852369");
//        user.setName("手把手教程");
//        user.setPwd("123456");
//        user.setSex("男");
//        int result = 0; //受影响的行数默认为0
//        try {
//            result = userDao.update(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("更新用户信息用户失败");
//        }
//        if (result > 0)
//            System.out.println("更新用户信息用户成功");
//
//    }
}
