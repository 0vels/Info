package cn.school.mvc.controller;

/**
 * Created by wang on 2017/5/4.
 */

import cn.school.JPush.mpush;
import cn.school.dao.QuerenDao;
import cn.school.dao.TongzhiDao;
import cn.school.domain.*;
import cn.school.utils.GsonUtils;
import cn.school.utils.JSONtool;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static cn.school.common.JPushCommon.appKey;
import static cn.school.common.JPushCommon.masterSecret;

/**
 * 用户请求相关控制器
 */
@Controller //标明本类是控制器
@RequestMapping("/jpush")  //外层地址
public class jPushController {

    private ResponseObj responseObj;    //返回json数据的实体
    private long TID;

    @Autowired
    private TongzhiDao tongzhiDao;
    @Autowired
    private QuerenDao querenDao;

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
//            likeService.add(topic1);
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("点赞成功");
            result = new GsonUtils().toJson(responseObj);
            return result;
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

    @RequestMapping(value = "/fabu"   //内层地址
            , method = RequestMethod.POST   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object fabu(String tongzhijson) {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat timesdf = new SimpleDateFormat("MMddHHmmss");
        String dateNowStr = sdf.format(now.getTime()); //获得当前时间即通知发布时间年月日
        String TIDstr = timesdf.format(now.getTime());//生成通知id 月日时分秒组成
        TID = Integer.parseInt(TIDstr);//生成TID
        boolean flag = false;
        String type = null;
        JSONtool jt = new JSONtool(); //生成json 解析工具
        Gson gson = new Gson();
        TongZhi tz;
        Object result;
        JsonObject returnData = new JsonParser().parse(tongzhijson).getAsJsonObject();
//        TongZhi tz = jt.jsontotz(tongzhijson, TongZhi.class);//解析json存到TongZhi 类
        try {
            tz = gson.fromJson(returnData, TongZhi.class);
        } catch (IllegalStateException | JsonSyntaxException exception) {
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("json格式有误");
            result = new GsonUtils().toJson(responseObj);
            return result;

        }

        mpush.createjpushclient(appKey, masterSecret);//初始化极光推送

        if (tz.getMessageType() == 1)
            type = "重要信息:";
        if (tz.getMessageType() == 0)
            type = "普通信息:";
        int tag = -1;
        Gson names = new Gson();
        List<String> list = names.fromJson(tz.getnames(), new TypeToken<List<String>>() {
        }.getType());
        extradata extra = new extradata(dateNowStr, tz.getSendPersonName(), tz.getMessageType(), tz.getnames(), TID);
        //传递的附加字段:通知时间，接收群体，消息类型， 通知存在数据库的ID

        if (tz.getIsSelectAll() == 0) {
            //没有选择全选，进行标签推送
            for (String name : list) {
                //循环读取标签组然后进行推送
                tag = mpush.sendTotagAndroid(type + tz.getTitle(), tz.getContent(), name, jt.Tojsonstr(extra));
                if (tag == 1)
                // System.out.println("成功推送");
                //只要一个发送成功就说明消息发送成功（老师发到服务器成功）

                {
//                    flag = true;
                    // 推送成功写入数据库
                    tz.setTime(dateNowStr);
                    tz.setTID(TID);
//                    TZtomysql(tz, TID);

                    tongzhiDao.add(tz);
                    responseObj = new ResponseObj<OpenimUser>();
                    responseObj.setCode(ResponseObj.OK);
                    responseObj.setMsg("推送成功");
                    result = new GsonUtils().toJson(responseObj);
                    return result;
                }
            }
        } else {
            //选择了全选，进行广播推送
            tag = mpush.sendToAllAndroid(type + tz.getTitle(), tz.getContent(), jt.Tojsonstr(extra));
            if (tag == 1) {
//                flag = true;
                tz.setTime(dateNowStr);
                tz.setTID(TID);
//                TZtomysql(tz, TID);
                tongzhiDao.add(tz);
                tongzhiDao.add(tz);
                responseObj = new ResponseObj<OpenimUser>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg("推送成功");
                result = new GsonUtils().toJson(responseObj);
                return result;
            }
        }
        responseObj = new ResponseObj<OpenimUser>();
        responseObj.setCode(ResponseObj.FAILED);
        responseObj.setMsg("推送。。。失败");
        result = new GsonUtils().toJson(responseObj);
        return result;
//        return flag;//推送标志成功返回 true 失败返回false
        //
    }


    @RequestMapping(value = "/queren"   //内层地址
            , method = RequestMethod.POST   //限定请求方式
            , produces = "application/json; charset=utf-8") //设置返回值是json数据类型
    @ResponseBody
    public Object queren(String userid, long TID) {

        Gson gson = new Gson();
        Object result1;
        Queren queren = new Queren(userid,TID);
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String dateNowStr = sdf.format(now.getTime());
        int result = 0; //受影响的行数默认为0
        try {
            result = querenDao.add(queren);
        } catch (Exception e) {
            System.out.println("确认失败" + e.getMessage());
            //其他失败异常
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("确认失败");
            responseObj.setData(e.getMessage());
            result1 = new GsonUtils().toJson(responseObj);
            return result1;
        }
        if (result > 0) {
            System.out.println("确认成功");
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("确认成功");
            result1 = new GsonUtils().toJson(responseObj);
            return result1;

        }else {
            responseObj = new ResponseObj<OpenimUser>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("确认失败");
            result1 = new GsonUtils().toJson(responseObj);
            return result1;
        }


//        return flag;//推送标志成功返回 true 失败返回false
        //
    }

    private static final long serialVersionUID = 1L;
//    public void mqrtoTZdz(Queren  imqr){
//        //将信息插入到Tqrdz(qian-ru-dui-ying)表：存放所有关于嵌入式的通知，
//        //已经存在的字段有学号--姓名-电话，需要添加的字段为Tqueren(通知确认)=yes,Tqueshi（确认时间，当前系统年月日）
//        Calendar now = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
//        String dateNowStr = sdf.format(now.getTime());
//        //数据库操作
//        DBSQL dbBmanage=new DBSQL();
//        dbBmanage.dblink();
////		dbBmanage.inserttoTqrdz(xuehao, TId, nowtime);
//    }
}
