package cn.school.mvc.controller;

import cn.school.domain.OpenimUser;
import cn.school.domain.ResponseObj;
import cn.school.utils.GsonUtils;
import com.sun.imageio.plugins.common.ImageUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by wang on 2017/5/2.
 */

/**
 * 用户请求相关控制器
 */
@Controller //标明本类是控制器
@RequestMapping("/file")  //外层地址
public class fileController {

    private ResponseObj responseObj;    //返回json数据的实体

    //uploadFile
    @ResponseBody
    @RequestMapping(value = "uploadFile"
            , method = RequestMethod.POST   //限定请求方式
            , produces = "application/json; charset=utf-8")
    public Object uploadFile(MultipartFile file) throws IllegalStateException, IOException {
        //原始名称
        String oldFileName = file.getOriginalFilename(); //获取上传文件的原名
        //存储图片的物理路径
        Object result;
        String file_path = "D:/tomcat/webapps";    //本地地址
//        String file_path = "D:/tomcat/webapps";    //服务器地址
//        String file_path = session.getServletContext().getRealPath("webapps/ROOT/");

        //上传图片
        if (file != null && oldFileName != null && oldFileName.length() > 0) {
            //新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            //新图片
            File newFile = new File(file_path + "/" + newFileName);
            //将内存中的数据写入磁盘
            try {
                file.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
                responseObj = new ResponseObj<OpenimUser>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg(ResponseObj.FAILED_STR);
                responseObj.setData("上传图片失败" + e.getMessage()); //登陆成功后返回用户信息
                result = new GsonUtils().toJson(responseObj);
                return result;
            }
            //将新图片名称返回到前端
//            Map<String,Object> map=new HashMap<String,Object>();
//            map.put("success", "成功啦");
//            map.put("url",newFileName);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg(ResponseObj.OK_STR);
            responseObj.setData("上传图片成功"); //登陆成功后返回用户信息
            result = new GsonUtils().toJson(responseObj);
            return result;
        } else {
//            Map<String,Object> map=new HashMap<String,Object>();
//            map.put("error","图片不合法");
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg(ResponseObj.FAILED_STR);
            responseObj.setData("图片不合法"); //登陆成功后返回用户信息
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
    }

    //uploadFiles
    @ResponseBody
    @RequestMapping(value = "uploadFiles"
            , method = RequestMethod.POST   //限定请求方式
            , produces = "application/json; charset=utf-8")
    public Object uploadFiles(MultipartFile[] file) throws IllegalStateException, IOException {
        List list = new ArrayList();
        Object result;
        ImageUtil imageUtil = new ImageUtil();
        // String images =  imageUtil.ImageUpload(file, request,response,session);
        if (file != null && file.length > 0) {
            //循环获取file数组中得文件
            for (int i = 0; i < file.length; i++) {
                MultipartFile files = file[i];
//                //保存文件
//                String amageurl =  imageUtil.ImageUpload(files, request, response, session);
//                list.add(amageurl);
                String oldFileName = files.getOriginalFilename(); //获取上传文件的原名
                String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                String file_path = "D:/tomcat/webapps";    //本地地址
                //        String file_path = "D:/tomcat/webapps";    //服务器地址
                File newFile = new File(file_path + "/" + newFileName);
                try {
                    files.transferTo(newFile);
                    responseObj = new ResponseObj<OpenimUser>();
                    responseObj.setCode(ResponseObj.OK);
                    responseObj.setMsg(ResponseObj.OK_STR);
                    responseObj.setData("上传图片成功"); //登陆成功后返回用户信息
                    result = new GsonUtils().toJson(responseObj);
                    return result;
                } catch (IOException e) {
                    e.printStackTrace();
                    responseObj = new ResponseObj<OpenimUser>();
                    responseObj.setCode(ResponseObj.FAILED);
                    responseObj.setMsg(ResponseObj.FAILED_STR);
                    responseObj.setData("上传图片失败" + e.getMessage()); //登陆成功后返回用户信息
                    result = new GsonUtils().toJson(responseObj);
                    return result;
                }

            }
        } else {
//            Map<String,Object> map=new HashMap<String,Object>();
//            map.put("error","图片不合法");
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg(ResponseObj.FAILED_STR);
            responseObj.setData("图片不合法"); //登陆成功后返回用户信息
            result = new GsonUtils().toJson(responseObj);
            return result;
        }

//        //原始名称
//        String oldFileName = file.getOriginalFilename(); //获取上传文件的原名
//        //存储图片的物理路径
//
//        String file_path = "D:/tomcat/webapps";    //本地地址
////        String file_path = "D:/tomcat/webapps";    //服务器地址
////        String file_path = session.getServletContext().getRealPath("webapps/ROOT/");
//
//        //上传图片
//        if(file!=null && oldFileName!=null && oldFileName.length()>0){
//            //新的图片名称
//            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
//            //新图片
//            File newFile = new File(file_path+"/"+newFileName);
//            //将内存中的数据写入磁盘
//            try {
//                file.transferTo(newFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "失败啦";
//            }
//            //将新图片名称返回到前端
////            Map<String,Object> map=new HashMap<String,Object>();
////            map.put("success", "成功啦");
////            map.put("url",newFileName);
//            return  "成功啦";
//        }else{
////            Map<String,Object> map=new HashMap<String,Object>();
////            map.put("error","图片不合法");
//            return "图片不合法";
//        }
        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg(ResponseObj.OK_STR);
        responseObj.setData("如果你看到这个。。。。。。我也不知道上传有没有成功"); //登陆成功后返回用户信息
        result = new GsonUtils().toJson(responseObj);
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "download"
            , method = RequestMethod.POST   //限定请求方式
            , produces = "application/string; charset=utf-8")
    public String download(String fileName) throws IOException{

        System.out.println("--download--");

        //获取文件服务器存储位置(需要先自己创建相应文件夹)
        String file_path = "D:/tomcat/webapps/";    //本地地址
        //        String file_path = "D:/tomcat/webapps";    //服务器地址

        //读取指定位置的文件，返回为byte的数组

        byte[] buff = Files.readAllBytes(Paths.get(file_path+File.separator+fileName));

        //创建响应头对象

        HttpHeaders header = new HttpHeaders();

        //设置响应头数据

        header.add("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));

        //设置响应状态

        HttpStatus statusCode = HttpStatus.OK;

        //封装到响应对象中

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(buff,header,statusCode);

        return file_path;

    }

//    @ResponseBody
//    @RequestMapping(value = "download"
//            , method = RequestMethod.POST   //限定请求方式
//            , produces = "application/object; charset=utf-8")
//    public ResponseEntity<byte[]> download(String fileName) throws IOException{
//
//        System.out.println("--download--");
//
//        //获取文件服务器存储位置(需要先自己创建相应文件夹)
//        String file_path = "D:/tomcat/webapps";    //本地地址
//        //        String file_path = "D:/tomcat/webapps";    //服务器地址
//
//        //读取指定位置的文件，返回为byte的数组
//
//        byte[] buff = Files.readAllBytes(Paths.get(file_path+File.separator+fileName));
//
//        //创建响应头对象
//
//        HttpHeaders header = new HttpHeaders();
//
//        //设置响应头数据
//
//        header.add("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
//
//        //设置响应状态
//
//        HttpStatus statusCode = HttpStatus.OK;
//
//        //封装到响应对象中
//
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(buff,header,statusCode);
//
//        return responseEntity;
//
//    }

}
