package cn.school.mvc.controller;

/**
 * Created by wang on 2017/4/21.
 */

import cn.school.dao.OpenimUserDao;
import cn.school.dao.TopicDao;
import cn.school.dao.UserInforDao;
import cn.school.domain.*;
import cn.school.exception.*;
import cn.school.openim.OpenimCommon;
import cn.school.service.IconAndMottoService;
import cn.school.service.OpenimUserService;
import cn.school.service.TopicService;
import cn.school.service.UserInforService;
import cn.school.utils.GsonUtils;
import cn.school.utils.StringUtils;
import com.google.gson.*;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimUsersAddResponse;
import com.taobao.api.response.OpenimUsersGetResponse;
import com.taobao.api.response.OpenimUsersUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户请求相关控制器
 */
@Controller //标明本类是控制器
@RequestMapping("/topic")  //外层地址
public class topicController {

    private ResponseObj responseObj;    //返回json数据的实体

    @Autowired
    private TopicDao topicDao;
    @Autowired
    private TopicService topicService;    //自动载入Service对象

    @RequestMapping(value = "/addTopic"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object addTopic(String topicjson) {
        Object result;
        Gson gson = new Gson();
        Topic topic1 = gson.fromJson(topicjson, Topic.class);

        String msg = "";

        try {
            topicService.add(topic1);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("发送话题成功");
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

    @RequestMapping(value = "/delTopic"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object delTopic(String topicid) {
        Object result;
        Topic topic1 = new Topic(topicid);

        String msg = "";

        try {
            topicService.del(topic1);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("删除话题成功");
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
