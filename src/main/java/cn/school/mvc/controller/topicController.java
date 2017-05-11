package cn.school.mvc.controller;

/**
 * Created by wang on 2017/4/21.
 */

import cn.school.dao.PictureDao;
import cn.school.dao.TopicDao;
import cn.school.domain.*;
import cn.school.exception.*;
import cn.school.service.TopicService;
import cn.school.service.UserInforService;
import cn.school.utils.GsonUtils;
import com.google.gson.*;
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
@RequestMapping("/topic")  //外层地址
public class topicController {

    private ResponseObj responseObj;    //返回json数据的实体

    @Autowired
    private TopicDao topicDao;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private TopicService topicService;    //自动载入Service对象
    @Autowired
    private UserInforService userInforService;

    @RequestMapping(value = "/addTopic"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object addTopic(String topicjson) {
        Object result;
        Gson gson = new Gson();
        Topic topic1 = gson.fromJson(topicjson, Topic.class);
        String userid = topic1.getAuthorid();
        String msg = "";

        try {
//            UserInfor userInfor = userInforService.find(new UserInfor(userid));
//            String iconurl = userInfor.getTouxiang();
//            topic1.setIcon(iconurl);
            topicService.add(topic1);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("发送话题成功");
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
        result = new GsonUtils().toJson(responseObj);
        return result;
    }

    @RequestMapping(value = "/getAllTopic"   //内层地址
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object getAllTopic() {
        Object result;
        List<Topic> topicList;

        String msg = "";

        try {
            topicList = topicService.findAll();
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("获取话题列表成功");
            responseObj.setData(topicList);
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

    @ResponseBody
    @RequestMapping(value = "addPhotos"
            , method = RequestMethod.POST   //限定请求方式
            , produces = "application/json; charset=utf-8")
    public Object addPhotos(String topicid, MultipartFile[] file) throws IllegalStateException, IOException {
        List list = new ArrayList();
        Object result = null;
        ImageUtil imageUtil = new ImageUtil();
        // String images =  imageUtil.ImageUpload(file, request,response,session);
        if (file != null && file.length > 0) {
            List<String> urls = new ArrayList<>();
            List<File> newFiles = new ArrayList<>();
            //循环获取file数组中得文件
            for (int i = 0; i < file.length; i++) {
                MultipartFile files = file[i];
//                //保存文件
//                String amageurl =  imageUtil.ImageUpload(files, request, response, session);
//                list.add(amageurl);
                String oldFileName = files.getOriginalFilename(); //获取上传文件的原名
                String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
//                String file_path = "D:/tomcat/webapps";    //本地地址
                String file_path = "C:/hostadmin/tomcat80/webapps/ROOT/file";    //服务器地址
                File newFile = new File(file_path + "/" + newFileName);
                String iconurl = "http://120.25.202.192/file/" + newFileName;
                newFiles.add(newFile);
                urls.add(iconurl);
            }
            try {
                for (int i = 0; i < file.length; i++) {
                    MultipartFile files = file[i];
                    files.transferTo(newFiles.get(i));

                }
                for (int i = 0; i < urls.size(); i++) {
                    Picture picture = new Picture(topicid, urls.get(i));
//                    topic.setPhotos(iconurl);
                    pictureDao.add(picture);
                }
//                    Topic topic = new Topic(topicid);

//                    topicDao.addPhotos(topic);
                responseObj = new ResponseObj<OpenimUser>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                responseObj.setData("上传图片成功"); //登陆成功后返回用户信息
                result = new GsonUtils().toJson(responseObj);

            } catch (IOException e) {
                e.printStackTrace();
                responseObj = new ResponseObj<OpenimUser>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg(ResponseObj.FAILED_STR);
                responseObj.setData("上传图片失败" + e.getMessage()); //登陆成功后返回用户信息
                result = new GsonUtils().toJson(responseObj);

            }


        } else {

            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg(ResponseObj.FAILED_STR);
            responseObj.setData("图片不合法");
            result = new GsonUtils().toJson(responseObj);

        }

//        responseObj = new ResponseObj<OpenimUser>();
//        responseObj.setCode(ResponseObj.OK);
//        responseObj.setMsg(ResponseObj.OK_STR);
//        responseObj.setData("如果你看到这个。。。。。。我也不知道上传有没有成功"); //登陆成功后返回用户信息
//        result = new GsonUtils().toJson(responseObj);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "getPhotos"
            , method = RequestMethod.GET   //限定请求方式
            , produces = "application/json; charset=utf-8")
    public Object getPhotos(String topicid) {
        List<Picture> list;
        List<String> returnlist = new ArrayList<>();
        Object result;
//        Topic topic = new Topic(topicid);
//        Picture picture = new Picture();
        list = pictureDao.findAll(topicid);

        for (Picture picture1 : list) {
            returnlist.add(picture1.getPicpath());

        }
//            pictureDao.add(picture);
//                    topicDao.addPhotos(topic);
        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg(ResponseObj.OK_STR);
        responseObj.setData(returnlist);
        result = new GsonUtils().toJson(responseObj);
        return result;


    }
}
