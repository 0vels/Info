package cn.school.mvc.controller;

/**
 * Created by wang on 2017/4/30.
 */

import cn.school.dao.IconAndMottoDao;
import cn.school.dao.UserInforDao;
import cn.school.domain.IconAndMotto;
import cn.school.domain.OpenimUser;
import cn.school.domain.ResponseObj;
import cn.school.domain.UserInfor;
import cn.school.exception.OtherThingsException;
import cn.school.exception.UserCanNotBeNullException;
import cn.school.exception.UserNameCanNotBeNullException;
import cn.school.exception.UserPwdCanNotBeNullException;
import cn.school.service.IconAndMottoService;
import cn.school.service.UserInforService;
import cn.school.utils.GsonUtils;
import cn.school.utils.StringUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 用户请求相关控制器
 */
@Controller //标明本类是控制器
@RequestMapping("/rest")  //外层地址
public class iconAndMottoController {

    private ResponseObj responseObj;    //返回json数据的实体

    @Autowired
    private IconAndMottoDao iconAndMottoDao;
    @Autowired
    private IconAndMottoService iconAndMottoService;


    @RequestMapping(value = "/updateIconAndMotto"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object updateIconAndMotto(String iconAndMotto) {
        Object result;
        Gson gson = new Gson();    //注册和修改个人资料相关
        IconAndMotto iconAndMotto1 = gson.fromJson(iconAndMotto, IconAndMotto.class);

        String msg = "";

        try {
            iconAndMottoService.update(iconAndMotto1);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("修改用户信息成功");
            result = new GsonUtils().toJson(responseObj);
            return result;
        } catch (OtherThingsException e) {
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

    @RequestMapping(value = "/getIconAndMotto"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/string; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object getIconAndMotto(String userid) {
        Object result;
        IconAndMotto iconAndMotto = new IconAndMotto(userid);

        if (StringUtils.isEmpty(iconAndMotto.getUserid())) {
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        //查找用户
        IconAndMotto user1 = null;
        try {
            user1 = iconAndMottoService.find(iconAndMotto);
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
            if (iconAndMotto.getUserid().equals(user1.getUserid())) {
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
//        return result;
        return "123";
    }

}
