package cn.school.mvc.controller;

/**
 * Created by wang on 2017/4/21.
 */

import cn.school.dao.CommentDao;
import cn.school.dao.PictureDao;
import cn.school.dao.TopicDao;
import cn.school.domain.*;
import cn.school.exception.OtherThingsException;
import cn.school.service.CommentService;
import cn.school.service.TopicService;
import cn.school.utils.GsonUtils;
import com.google.gson.Gson;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 用户请求相关控制器
 */
@Controller //标明本类是控制器
@RequestMapping("/comment")  //外层地址
public class commentController {

    private ResponseObj responseObj;    //返回json数据的实体

    @Autowired
    private CommentDao topicDao;
    @Autowired
    private CommentService topicService;

    @RequestMapping(value = "/addComment"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object addComment(String commentjson) {
        Object result;
        Gson gson = new Gson();
        Comment topic1 = gson.fromJson(commentjson, Comment.class);

        String msg = "";

        try {
//            Comment picture = new Comment(topic1.getTopicid());
            topicService.add(topic1);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("发送评论成功");
            responseObj.setData(topic1);
            result = new GsonUtils().toJson(responseObj);
            return result;
        } catch (OtherThingsException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }

        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.FAILED);
        responseObj.setMsg(msg);
        responseObj.setData(topic1);
        result = new GsonUtils().toJson(responseObj);
        return result;
    }

    @RequestMapping(value = "/getAllComment"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object getAllComment(String topicid) {
        Object result;
        Comment comment = new Comment(topicid);
        List<Comment> commentList = new ArrayList<>();
        String msg = "";

        try {

            commentList = topicDao.findAll(topicid);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("获取评论成功");
            responseObj.setData(commentList);
            result = new GsonUtils().toJson(responseObj);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }

        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.FAILED);
        responseObj.setMsg(msg);
        responseObj.setData(commentList);
        result = new GsonUtils().toJson(responseObj);
        return result;
    }

    @RequestMapping(value = "/delComment"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object delComment(String commentid) {
        Object result;
        Comment topic1 = new Comment(commentid);

        String msg = "";

        try {
            topicService.del(topic1);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("删除评论成功");
            result = new GsonUtils().toJson(responseObj);
            return result;
        } catch (OtherThingsException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }

        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.FAILED);
        responseObj.setMsg(msg);
        result = new GsonUtils().toJson(responseObj);
        return result;
    }


}
