package cn.school.mvc.controller;

/**
 * Created by wang on 2017/4/21.
 */

import cn.school.dao.LikeDao;
import cn.school.dao.TopicDao;
import cn.school.domain.Like;
import cn.school.domain.OpenimUser;
import cn.school.domain.ResponseObj;
import cn.school.domain.Topic;
import cn.school.exception.OtherThingsException;
import cn.school.service.LikeService;
import cn.school.service.TopicService;
import cn.school.utils.GsonUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户请求相关控制器
 */
@Controller //标明本类是控制器
@RequestMapping("/like")  //外层地址
public class likeController {

    private ResponseObj responseObj;    //返回json数据的实体

    @Autowired
    private LikeDao topicDao;
    @Autowired
    private LikeService topicService;    //自动载入Service对象

    @RequestMapping(value = "/addLike"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object addLike(String likejson) {
        Object result;
        Gson gson = new Gson();
        Like topic1 = gson.fromJson(likejson, Like.class);

        String msg = "";

        try {
            topicService.update(topic1);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("点赞成功");
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

    @RequestMapping(value = "/removeLike"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object removeLike(String topicid) {
        Object result;
        Like topic1 = new Like(topicid);

        String msg = "";

        try {
            topicService.del(topic1);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("取消点赞成功");
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
