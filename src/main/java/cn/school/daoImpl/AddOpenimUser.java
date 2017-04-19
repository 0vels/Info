//package cn.school.daoImpl;
//
//import cn.school.dao.OpenimUserDao;
//import cn.school.dao.UserDao;
//import cn.school.domain.OpenimUser;
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * Created by wang on 2017/4/19.
// */
//public class AddOpenimUser {
//
//    @Autowired
//    private OpenimUserDao openimUserDao;
//
//    public  void addOpenimUser(JsonElement jsonElement) {
//        Gson gson = new Gson();
//        OpenimUser openimUser = gson.fromJson(jsonElement, OpenimUser.class);
//        System.out.println("userid" + openimUser.getUserid());
//
//
//        int result = 0; //受影响的行数默认为0
//        try {
//            result = openimUserDao.add(openimUser);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("添加用户失败");
//        }
//        if (result > 0)
//            System.out.println("添加用户成功");
//    }
//}
