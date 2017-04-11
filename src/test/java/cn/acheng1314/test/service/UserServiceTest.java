package cn.acheng1314.test.service;

import cn.acheng1314.domain.User;
import cn.acheng1314.exception.*;
import cn.acheng1314.service.serviceImpl.UserServiceImpl;
import cn.acheng1314.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/9/25.
 */
public class UserServiceTest extends BaseTest {
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testAdd() {
        User user = new User();
        try {
            userService.add(user);
        } catch (UserCanNotBeNullException e) {
            //用户不能为空异常抛出
            e.printStackTrace();
        } catch (UserNameCanNotBeNullException e) {
            //用户名不能为空
            e.printStackTrace();
        } catch (UserPwdCanNotBeNullException e) {
            //用户密码不能为空
            e.printStackTrace();
        } catch (UserAireadyExistException e) {
            //用户存在抛出
            e.printStackTrace();
        } catch (OtherThingsException e) {
            //其他综合异常或是不能处理的异常
            e.printStackTrace();
        }


    }
}
