package cn.school.mvc.controller;

/**
 * Created by wang on 2017/4/21.
 */

import cn.school.dao.OpenimUserDao;
import cn.school.dao.QuerenDao;
import cn.school.dao.UserInforDao;
import cn.school.domain.*;
import cn.school.exception.*;
import cn.school.common.OpenimCommon;
import cn.school.service.IconAndMottoService;
import cn.school.service.OpenimUserService;
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
@RequestMapping("/rest")  //外层地址
public class openimUserController {

    private static String url = OpenimCommon.OpenimUrl;
    private static String appkey = OpenimCommon.Appkey;
    private static String secret = OpenimCommon.AppSecret;
    private ResponseObj responseObj;    //返回json数据的实体

    @Autowired
    private OpenimUserDao openimUserDao;
    @Autowired
    private OpenimUserService openimUserService;    //自动载入Service对象
    @Autowired
    private UserInforDao userInforDao;
    @Autowired
    private UserInforService userInforService;
    @Autowired
    private IconAndMottoService iconAndMottoService;
    @Autowired
    private QuerenDao querenDao;

/*
将阿里数据库已存在，但是自己服务器不存在的用户加进数据库
 */
    @RequestMapping(value = "/add"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/string; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public String add(String userId) {
        Object result = null;
        OpenimUser openimUser = new OpenimUser();
//        String userId = "wangzhennan";
        try {
//            OpenimController openimController = new OpenimController();
//            openimController.getIMUser(userId);
            getIMUser(userId);
        } catch (ApiException e) {
            e.printStackTrace();
        }
//        int result1 = 0; //受影响的行数默认为0
//        try {
//            result1 = openimUserDao.add(openimUser);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("添加用户失败");
//        }
//        if (result1 > 0)
//            System.out.println("添加用户成功");


        return "添加成功";
    }


    int result = 0; //受影响的行数默认为0

    private void getIMUser(String userId) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersGetRequest req = new OpenimUsersGetRequest();
        req.setUserids(userId);
        OpenimUsersGetResponse rsp = client.execute(req);
        String r = rsp.getBody();
        System.out.println(rsp.getBody());

        JsonObject json = new JsonParser().parse(r).getAsJsonObject();
        JsonObject genius_1 = json.get("openim_users_get_response").getAsJsonObject();
        JsonObject genius_2 = genius_1.get("userinfos").getAsJsonObject();
        JsonArray genius_3 = genius_2.get("userinfos").getAsJsonArray();
        for (JsonElement je : genius_3) {
            System.out.println("jeeeeee" + je);
            Gson gson = new Gson();
            OpenimUser openimUser = gson.fromJson(je, OpenimUser.class);
            result = openimUserDao.add(openimUser);
//            System.out.println("jeeeeeeresult"+result);
        }

    }


    /*
    注册
     */
    @RequestMapping(value = "/register"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object register(String userid, String password) throws ApiException {
        Object result = null;
        OpenimUser openimUser = new OpenimUser(userid, password);

        addIMUser(userid, password);
//        Queren queren = new Queren(userid);
        int result1 = 0; //受影响的行数默认为0
        String msg;
        try {
            openimUserService.add(openimUser);
//            querenDao.add(queren);// TODO: 2017/5/10 add确认和话题排序
            addUserInfor(userid);
            addIconAndMotto(userid);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("注册成功");
            result = new GsonUtils().toJson(responseObj);
           return result;
        } catch (UserCanNotBeNullException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (UserAireadyExistException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (UserPwdCanNotBeNullException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
//        try {
//            result1 = openimUserDao.add(openimUser);
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseObj = new ResponseObj<OpenimUser>();
//            responseObj.setCode(ResponseObj.FAILED);
//            responseObj.setMsg("添加用户失败");
//            result = new GsonUtils().toJson(responseObj);
//            System.out.println("添加用户失败");
//        }

        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.FAILED);
        responseObj.setMsg(msg);
        result = new GsonUtils().toJson(responseObj);
        System.out.println(msg);


        return result;
    }

//注册成功就添加一个用户的信息，方便后面更新
    private Object addUserInfor(String userid) {
        Object result;
        Gson gson = new Gson();    //注册和修改个人资料相关

        UserInfor userInfor = new UserInfor(userid);
        userInfor.setShenfen("teacher");
        String msg = "";

        try {
            userInforService.add(userInfor);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("修改用户信息成功");
            result = new GsonUtils().toJson(responseObj);
            return result;
        } catch (OtherThingsException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (UserAireadyExistException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (UserNameCanNotBeNullException e) {
            e.printStackTrace();
            msg = e.getMessage();
        }catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }

        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.FAILED);
        responseObj.setMsg(msg);
        result = new GsonUtils().toJson(responseObj);
        return result;
    }

    private Object addIconAndMotto(String userid) {
        Object result;
        Gson gson = new Gson();    //注册和修改个人资料相关

        IconAndMotto iconAndMotto = new IconAndMotto(userid);
        String msg = "";

        try {
            iconAndMottoService.add(iconAndMotto);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("修改用户信息成功");
            result = new GsonUtils().toJson(responseObj);
            return result;
        } catch (OtherThingsException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (UserAireadyExistException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (UserNameCanNotBeNullException e) {
            e.printStackTrace();
            msg = e.getMessage();
        }catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }

        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.FAILED);
        responseObj.setMsg(msg);
        result = new GsonUtils().toJson(responseObj);
        return result;
    }

    private OpenimUsersAddResponse addIMUser(String userid, String password) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
        List<Userinfos> list2 = new ArrayList<Userinfos>();
        Userinfos obj3 = new Userinfos();
        list2.add(obj3);
        obj3.setUserid(userid);
        obj3.setPassword(password);
        req.setUserinfos(list2);
        OpenimUsersAddResponse rsp = client.execute(req);
        System.out.println("addIMUser" + rsp.getBody());
        return rsp;
    }


    /**
     * 登录接口，因为json数据外层一般都是Object类型，所以返回值必须是Object<br/>
     * 这里的地址是： 域名/rest/login
     *
     * @return
     */
    @RequestMapping(value = "/login"    //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object login(String userid, String password) {
        Object result;
        OpenimUser openimUser = new OpenimUser(userid, password);
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
        //查找用户
        OpenimUser user1 = null;
//        OpenimUser mimaUser = new OpenimUser();
        try {
            user1 = openimUserService.find(openimUser);
            OpenimUser mimaUser = openimUserService.find(new OpenimUser(userid));

            UserInfor userInfor = new UserInfor(userid);
            userInfor.setMima(mimaUser.getPassword());
            userInforService.update(userInfor);
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
            if (openimUser.getPassword().equals(user1.getPassword())) {
                responseObj = new ResponseObj<OpenimUser>();
                responseObj.setCode(ResponseObj.OK);    //登录成功，状态为1
                responseObj.setMsg(ResponseObj.OK_STR);
                responseObj.setData(user1); //登陆成功后返回用户信息
                result = new GsonUtils().toJson(responseObj);
            } else {
                responseObj = new ResponseObj<OpenimUser>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
                result = new GsonUtils().toJson(responseObj);
            }
        }
        return result;
    }

    /**
     * 修改资料接口，因为json数据外层一般都是Object类型，所以返回值必须是Object<br/>
     * 这里的地址是： 域名/rest/changePassword
     *
     * @return
     */
    @RequestMapping(value = "/changePassword"    //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object changePassword(String userid, String oldPassword,String newPassword) {
        Object result;
        Gson gson = new Gson();    //注册和修改个人资料相关
        OpenimUser openimUser = new OpenimUser(userid,oldPassword);
        OpenimUser openimUser1 = new OpenimUser(userid,newPassword);

//        String userid = openimUser.getUserid();
//        String password = openimUser.getPassword();

        if (null == openimUser) {
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.EMPUTY);
            responseObj.setMsg("用户信息不能为空");
            result = new GsonUtils().toJson(responseObj);   //通过gson把java bean转换为json
            return result; //返回json
        }
        if (StringUtils.isEmpty(openimUser.getUserid()) || StringUtils.isEmpty(openimUser.getPassword())||StringUtils.isEmpty(newPassword)) {
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        //查找用户
        OpenimUser user1 = null;
        String msg = "";


        try {
            user1 = openimUserService.find(openimUser);
            updateIMUser(userid, newPassword);
            openimUserService.update(openimUser1);
            userInforDao.changePassword(new UserInfor(userid, newPassword));
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);    //登录成功，状态为1
            responseObj.setMsg(ResponseObj.OK_STR);
//                responseObj.setData(user1); //登陆成功后返回用户信息
            result = new GsonUtils().toJson(responseObj);
            return result;
        } catch (UserCanNotBeNullException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (UserNameCanNotBeNullException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (UserPwdCanNotBeNullException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (OtherThingsException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (ApiException e) {
            e.printStackTrace();
            msg = e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }

        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.FAILED);
        responseObj.setData(msg);
        result = new GsonUtils().toJson(responseObj);
        return result;
//        if (null == user1) {
//            responseObj = new ResponseObj<OpenimUser>();
//            responseObj.setCode(ResponseObj.EMPUTY);
//            responseObj.setMsg("未找到该用户");
//            result = new GsonUtils().toJson(responseObj);
//        } else {
//            if (openimUser.getPassword().equals(user1.getPassword())) {
//                responseObj = new ResponseObj<OpenimUser>();
//                responseObj.setCode(ResponseObj.OK);    //登录成功，状态为1
//                responseObj.setMsg(ResponseObj.OK_STR);
////                responseObj.setData(user1); //登陆成功后返回用户信息
//                result = new GsonUtils().toJson(responseObj);
//            } else {
//                responseObj = new ResponseObj<OpenimUser>();
//                responseObj.setCode(ResponseObj.FAILED);
//                responseObj.setData(msg);
//                result = new GsonUtils().toJson(responseObj);
//            }
//        }
//        return result;
    }

    public static void updateIMUser(String userid,String newPassword) throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
        List<Userinfos> list2 = new ArrayList<Userinfos>();
        Userinfos obj3 = new Userinfos();
        list2.add(obj3);
        obj3.setUserid(userid);
        obj3.setPassword(newPassword);
        req.setUserinfos(list2);
        OpenimUsersUpdateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

}
