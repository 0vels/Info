package cn.school.test.dao;

import cn.school.dao.*;
import cn.school.domain.*;
import cn.school.test.BaseTest;
import com.google.gson.Gson;
import org.junit.Test;
import org.omg.IOP.Encoding;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GsonTest extends BaseTest {

    @Autowired
    TopicDao topicDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    QuerenDao querenDao;

    @Test
    public void testAdd() {
//        User user = new User();
//        user.setLoginId("123455678");
//        user.setName("添加一个账号");
//        user.setPwd("654321");
//        user.setSex("女");

//        String s = "{\\\"isSelectAll\\\":\\\"1\\\",\\\"messageType\\\":\\\"1\\\",\\\"content\\\":\\\"测试111\\\",\\\"title\\\":\\\"测试1111\\\",\\\"sendTo\\\":\\\"{[{\\\\\\\"计算机系\\\\\\\"},{\\\\\\\"云计算系\\\\\\\"}]}\\\"}";
//        topicDao.findOneById("1111112");

//        Date date = new Date();
//        SimpleDateFormat sf = new SimpleDateFormat("yyy-MM-dd HH:mm");
//        String time = sf.format(date);
//        System.out.println(date+"date");
//        System.out.println(time+"time");

//List<Comment> commentList = commentDao.findAll("7777");
//        List<Queren> querenList = querenDao.findAll("506224851");
    }

    @Test
    public void testget() {
//        List<Picture> list = new ArrayList<>();
//        list = pictureDao.findAll("6666");
//        System.out.println(list);
    }


}
