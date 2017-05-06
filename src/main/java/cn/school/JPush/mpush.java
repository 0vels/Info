package cn.school.JPush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;

/**
 * Created by wang on 2017/5/5.
 */
public class mpush {
    public String str=null;

    public  static JPushClient jpushClient=null;
    public static void  createjpushclient(String appKey ,String masterSecret){
        jpushClient = new JPushClient(masterSecret,appKey);
    }
    //推送全部
    // String notification_title--通知标题, String msg_content-通知内容,String eatradata--附加字段，消息详情的其他字段
    public static int sendToAllAndroid( String notification_title, String msg_content,String eatradata) {
        int result = 0;
        try {
            PushPayload pushPayload=buildPushObject_android(notification_title,msg_content,eatradata);
            System.out.println(pushPayload.toString());

            PushResult pushResult=jpushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if(pushResult.getResponseCode()==200){
                result=1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }
    //标签推送
    // String notification_title--通知标题, String msg_content-通知内容,String eatradata--附加字段，消息详情的其他字段
    public static int sendTotagAndroid( String notification_title, String msg_content,String tag,String extradata) {
        int result = 0;
        try {
            PushPayload pushPayload=buildPushObjecttag_android(notification_title,msg_content,tag,extradata);
            //  System.out.println(pushPayload.toString());

            PushResult pushResult=jpushClient.sendPush(pushPayload);
            // System.out.println(pushResult);
            if(pushResult.getResponseCode()==200){
                result=1;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    public   static PushPayload buildPushObjecttag_android(String tztitile,String mg_leirong,String tagValue,String extrasparam){
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.tag(tagValue))
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(tztitile)
                                .setTitle(mg_leirong)
                                .addExtra("jsonstr",extrasparam)
                                .build())
                        .build()
                ).build();

    }
    public   static PushPayload buildPushObject_android(String tztitile,String mg_leirong,String extrasparam){
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(tztitile)
                                .setTitle(mg_leirong)
                                .addExtra("jsonstr",extrasparam)
                                .build())
                        .build()
                ).build();

    }
}
