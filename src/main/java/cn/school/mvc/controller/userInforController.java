package cn.school.mvc.controller;

/**
 * Created by wang on 2017/4/30.
 */

import cn.school.dao.OpenimUserDao;
import cn.school.dao.UserInforDao;
import cn.school.domain.OpenimUser;
import cn.school.domain.ResponseObj;
import cn.school.domain.UserInfor;
import cn.school.exception.OtherThingsException;
import cn.school.exception.UserCanNotBeNullException;
import cn.school.exception.UserNameCanNotBeNullException;
import cn.school.exception.UserPwdCanNotBeNullException;
import cn.school.service.OpenimUserService;
import cn.school.service.UserInforService;
import cn.school.utils.GsonUtils;
import cn.school.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户请求相关控制器
 */
@Controller //标明本类是控制器
@RequestMapping("/rest")  //外层地址
public class userInforController {

    private ResponseObj responseObj;    //返回json数据的实体

    @Autowired
    private UserInforDao userInforDao;
    @Autowired
    private UserInforService userInforService;

    @RequestMapping(value = "/addUserInfor"   //内层地址
            , method = RequestMethod.POST   //限定请求方式
            , produces = "application/string; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object addUserInfor(JsonObject userInfo) {
        Object result;
        Gson gson = new Gson();    //注册和修改个人资料相关
        UserInfor userInfor = gson.fromJson(userInfo, UserInfor.class);
        String userid = userInfor.getUserid();
        String password = userInfor.getMinzu();
        System.out.println("rtetre" + userid);
        System.out.println("rtetre" + userInfo.toString());
        String msg = "";

        int result1 = 0; //受影响的行数默认为0
        try {
            result1 = userInforDao.add(userInfor);
        } catch (Exception e) {
            e.printStackTrace();
            msg = "添加用户失败";
            System.out.println("添加用户失败");
        }
        if (result1 > 0) {
            msg = "添加用户成功";
            System.out.println("添加用户成功");
        }
        return msg;
    }

    @RequestMapping(value = "/getUserInfor"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object getUserInfor(String userid) {
        Object result;
        UserInfor userInfor = new UserInfor(userid);

        if (StringUtils.isEmpty(userInfor.getUserid())) {
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        //查找用户
        UserInfor user1 = null;
        try {
            user1 = userInforService.find(userInfor);
        } catch (UserCanNotBeNullException e) {
            e.printStackTrace();
        } catch (UserNameCanNotBeNullException e) {
            e.printStackTrace();
        } catch (UserPwdCanNotBeNullException e) {
            e.printStackTrace();
        } catch (OtherThingsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == user1) {
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("未找到该用户");
            result = new GsonUtils().toJson(responseObj);
        } else {
            if (userInfor.getUserid().equals(user1.getUserid())) {
                responseObj = new ResponseObj<OpenimUser>();
                responseObj.setCode(ResponseObj.OK);    //登录成功，状态为1
                responseObj.setMsg(ResponseObj.OK_STR);
                responseObj.setData(user1); //登陆成功后返回用户信息
                result = new GsonUtils().toJson(responseObj);
            } else {
                responseObj = new ResponseObj<OpenimUser>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("未找到该用户");
                result = new GsonUtils().toJson(responseObj);
            }
        }
        return result;
    }
}
