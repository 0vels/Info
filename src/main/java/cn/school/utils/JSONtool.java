package cn.school.utils;

import cn.school.domain.TongZhi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2017/5/4.
 */
public class JSONtool {
    //生成json字符串
    public String Tojsonstr( Object obj){
        Gson gson = new Gson();
        String str = gson.toJson(obj);
        return str;
    }
    //解析通知单个json字符串
    public TongZhi jsontotz(String jsonstr , Class cls){
        Gson gson = new Gson();
        return (TongZhi) gson.fromJson(jsonstr, cls);

    }
    //解析多个通知组成的json字符串
    public static List getTongZhis(String jsonString, Class cls) {
        List list = new ArrayList();
        try {
            Gson gson = new Gson();
            list = gson.fromJson(jsonString, new TypeToken<TongZhi>() {

            }.getType());
        } catch (Exception e) {
        }
        return list;
    }

}
