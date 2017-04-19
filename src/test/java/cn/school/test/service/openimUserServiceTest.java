package cn.school.test.service;

import cn.school.domain.OpenimUser;
import cn.school.domain.User;
import cn.school.exception.*;
import cn.school.service.serviceImpl.OpenimUserServiceImpl;
import cn.school.service.serviceImpl.UserServiceImpl;
import cn.school.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/9/25.
 */
public class openimUserServiceTest extends BaseTest {
    @Autowired
    private OpenimUserServiceImpl userService;

    @Test
    public void testAdd() {
        OpenimUser user = new OpenimUser();

        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
