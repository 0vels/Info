package cn.school.test.service;

import cn.school.domain.OpenimUser;
import cn.school.domain.UserInfor;
import cn.school.service.UserInforService;
import cn.school.service.serviceImpl.OpenimUserServiceImpl;
import cn.school.service.serviceImpl.UserInforServiceImpl;
import cn.school.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/9/25.
 */
public class openiminfoServiceTest extends BaseTest {
    @Autowired
    private UserInforServiceImpl userInforService;

    @Test
    public void testAdd() {
        UserInfor userInfor = new UserInfor();

        try {
            userInfor.setBanji("34234");
            userInfor.setUserid("ttest");
            userInfor.setJia("空间发的刷卡缴费rere");
            userInforService.add(userInfor);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
