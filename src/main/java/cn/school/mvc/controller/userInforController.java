package cn.school.mvc.controller;

/**
 * Created by wang on 2017/4/30.
 */

import cn.school.dao.OpenimUserDao;
import cn.school.dao.UserInforDao;
import cn.school.domain.OpenimUser;
import cn.school.domain.ResponseObj;
import cn.school.domain.UserInfor;
import cn.school.exception.*;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 用户请求相关控制器
 */
@Controller //标明本类是控制器
@RequestMapping("/userInfor")  //外层地址
public class userInforController {

    private ResponseObj responseObj;    //返回json数据的实体

    @Autowired
    private UserInforDao userInforDao;
    @Autowired
    private UserInforService userInforService;


    @RequestMapping(value = "/updateUserInfor"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object updateUserInfor(String userInfor) {
        Object result;
        Gson gson = new Gson();    //注册和修改个人资料相关
        UserInfor userInfor1 = gson.fromJson(userInfor, UserInfor.class);

        String msg = "";

        try {
            userInforService.update(userInfor1);
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

    @RequestMapping(value = "/updatetouxiang"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object updateIcon(String userid, String touxiang) {
        Object result;

//        Gson gson = new Gson();    //注册和修改个人资料相关
//        UserInfor userInfor1 = gson.fromJson(userInfor, UserInfor.class);
        UserInfor userInfor1 = new UserInfor(userid);
        userInfor1.setTouxiang(touxiang);
        String msg = "";
        int result1 = 0; //受影响的行数默认为0
        try {
            result1 = userInforDao.updateicon(userInfor1);
        } catch (Exception e) {
            System.out.println("添加用户信息失败");
            //其他用户添加失败异常
            msg = "添加用户信息失败";
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg(msg+e.getMessage());
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (result1 > 0) {
            System.out.println("添加用户信息成功");
            msg = "添加用户信息成功";
        }
        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg(msg);
        result = new GsonUtils().toJson(responseObj);
        return result;
    }

    @RequestMapping(value = "/updatezuoyouming"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object updateMotto(String userid, String zuoyouming) {
        Object result;

//        Gson gson = new Gson();    //注册和修改个人资料相关
//        UserInfor userInfor1 = gson.fromJson(userInfor, UserInfor.class);
        UserInfor userInfor1 = new UserInfor(userid);
        userInfor1.setZuoyouming(zuoyouming);
        String msg = "";
        int result1 = 0; //受影响的行数默认为0
        try {
            result1 = userInforDao.updatemotto(userInfor1);
        } catch (Exception e) {
            System.out.println("添加用户信息失败");
            //其他用户添加失败异常
            msg = "添加用户信息失败";
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg(msg+e.getMessage());
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (result1 > 0) {
            System.out.println("添加用户信息成功");
            msg = "添加用户信息成功";
        }
        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg(msg);
        result = new GsonUtils().toJson(responseObj);
        return result;
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

    //uploadFile
    @ResponseBody
    @RequestMapping(value ="uploadFile"
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/string; charset=utf-8")
    public Object uploadFile(HttpSession session, MultipartFile myfile) throws IllegalStateException, IOException{
        //原始名称
        String oldFileName = myfile.getOriginalFilename(); //获取上传文件的原名
        //存储图片的物理路径
        String file_path = session.getServletContext().getRealPath("webapps/");

        //上传图片
        if(myfile!=null && oldFileName!=null && oldFileName.length()>0){
            //新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            //新图片
            File newFile = new File(file_path+"/"+newFileName);
            //将内存中的数据写入磁盘
            try {
                myfile.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将新图片名称返回到前端
//            Map<String,Object> map=new HashMap<String,Object>();
//            map.put("success", "成功啦");
//            map.put("url",newFileName);
            return  "成功啦";
        }else{
//            Map<String,Object> map=new HashMap<String,Object>();
//            map.put("error","图片不合法");
            return "图片不合法";
        }
    }
}
