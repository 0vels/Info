package cn.school.mvc.controller;

/**
 * Created by wang on 2017/4/21.
 */

import cn.school.dao.OpenimUserDao;
import cn.school.domain.OpenimUser;
import cn.school.domain.ResponseObj;
import cn.school.domain.User;
import cn.school.exception.OtherThingsException;
import cn.school.exception.UserCanNotBeNullException;
import cn.school.exception.UserNameCanNotBeNullException;
import cn.school.exception.UserPwdCanNotBeNullException;
import cn.school.openim.OpenimUserController;
import cn.school.service.OpenimUserService;
import cn.school.service.serviceImpl.UserServiceImpl;
import cn.school.utils.GsonUtils;
import cn.school.utils.StringUtils;
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
public class openimUserController {
    @Autowired
    private OpenimUserService openimUserService;    //自动载入Service对象
    private ResponseObj responseObj;    //返回json数据的实体
    private OpenimUserDao openimUserDao;


    @RequestMapping(value = "/add"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object add() {
        Object result = null;
        OpenimUser openimUser = new OpenimUser();
        String userId = "wangzhennan";
        try {
            OpenimUserController openimUserController = new OpenimUserController();
            openimUserController.getIMUser(userId);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        int result1 = 0; //受影响的行数默认为0
        try {
            result1 = openimUserDao.add(openimUser);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加用户失败");
        }
        if (result1 > 0)
            System.out.println("添加用户成功");


        return "5435435二恶午饭" ;
    }
    /*
    注册
     */
    @RequestMapping(value = "/register"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object register( String userid,String password) {
        Object result = null;
        OpenimUser openimUser = new OpenimUser(userid,password);
        if (null == openimUser) {
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("登录信息不能为空");
            result = new GsonUtils().toJson(responseObj);   //通过gson把java bean转换为json
            return result; //返回json
        }
        if (StringUtils.isEmpty(openimUser.getUserid()) || StringUtils.isEmpty(openimUser.getPassword())) {
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }

        int result1 = 0; //受影响的行数默认为0
        try {
            result1 = openimUserDao.add(openimUser);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("添加用户失败");
        }
        if (result1 > 0)
            System.out.println("添加用户成功");


        return result;
    }


//    /**
//     * 登录接口，因为json数据外层一般都是Object类型，所以返回值必须是Object<br/>
//     *  这里的地址是： 域名/rest/login
//     *
//     *
//     * @return
//     */
//    @RequestMapping(value = "/login"    //内层地址
//            , method = RequestMethod.GET   //限定请求方式
//            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
//    @ResponseBody
//    public Object login( String name,String pwd) {
//        Object result;
//        User user= new User(name,pwd);
//        if (null == user) {
//            responseObj = new ResponseObj<User>();
//            responseObj.setCode(ResponseObj.EMPUTY);
//            responseObj.setMsg("登录信息不能为空");
//            result = new GsonUtils().toJson(responseObj);   //通过gson把java bean转换为json
//            return result; //返回json
//        }
//        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
//            responseObj = new ResponseObj<User>();
//            responseObj.setCode(ResponseObj.FAILED);
//            responseObj.setMsg("用户名或密码不能为空");
//            result = new GsonUtils().toJson(responseObj);
//            return result;
//        }
//        //查找用户
//        User user1 = null;
//        try {
//            user1 = userService.find(user);
//        } catch (UserCanNotBeNullException e) {
//            e.printStackTrace();
//        } catch (UserNameCanNotBeNullException e) {
//            e.printStackTrace();
//        } catch (UserPwdCanNotBeNullException e) {
//            e.printStackTrace();
//        } catch (OtherThingsException e) {
//            e.printStackTrace();
//        }
//        if (null == user1) {
//            responseObj = new ResponseObj<User>();
//            responseObj.setCode(ResponseObj.EMPUTY);
//            responseObj.setMsg("未找到该用户");
//            result = new GsonUtils().toJson(responseObj);
//        } else {
//            if (user.getPwd().equals(user1.getPwd())) {
//                responseObj = new ResponseObj<User>();
//                responseObj.setCode(ResponseObj.OK);    //登录成功，状态为1
//                responseObj.setMsg(ResponseObj.OK_STR);
//                responseObj.setData(user1); //登陆成功后返回用户信息
//                result = new GsonUtils().toJson(responseObj);
//            } else {
//                responseObj = new ResponseObj<User>();
//                responseObj.setCode(ResponseObj.FAILED);
//                responseObj.setMsg("用户密码错误");
//                result = new GsonUtils().toJson(responseObj);
//            }
//        }
//        return result;
//    }
}
