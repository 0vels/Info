package cn.school.openim;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersDeleteRequest;
import com.taobao.api.request.OpenimUsersGetRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import com.taobao.api.response.OpenimUsersAddResponse;
import com.taobao.api.response.OpenimUsersDeleteResponse;
import com.taobao.api.response.OpenimUsersGetResponse;
import com.taobao.api.response.OpenimUsersUpdateResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang on 2017/4/3.
 */
public class OpenimLogin {

    private static String url = OpenimCommon.OpenimUrl;
    private static String appkey = OpenimCommon.Appkey;
    private static String secret = OpenimCommon.AppSecret;


    public void get() {
//        TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);// url参数输入： http://gw.api.taobao.com/router/rest
//        TaeItemDetailGetRequest req=new TaeItemDetailGetRequest();
//        req.setId("AAEkwBGKAAXszj-DOJ-KKVll");
//        req.setFields("itemInfo,priceInfo,skuInfo,stockInfo,rateInfo,descInfo,sellerInfo,mobileDescInfo,deliveryInfo,storeInfo");
//        TaeItemDetailGetResponse response = client.execute(req);
    }

    public static void main(String[] args) throws ApiException {
        getIMUser();
//        delIMUser();
    }

    public static void getIMUser() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersGetRequest req = new OpenimUsersGetRequest();
        req.setUserids("wangzhennan");
        OpenimUsersGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
        Gson gson = new Gson();
        JsonObject userinfos = gson.fromJson(rsp.getBody(),JsonObject.class);
        System.out.println(userinfos);
//        JsonArray userinfos = gson.fromJson(rsp.getBody(),JsonArray.class);

    }

    public static void delIMUser() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersDeleteRequest req = new OpenimUsersDeleteRequest();
        req.setUserids("addtest1");
        OpenimUsersDeleteResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    public static void updateIMUser() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersUpdateRequest req = new OpenimUsersUpdateRequest();
        List<Userinfos> list2 = new ArrayList<Userinfos>();
        Userinfos obj3 = new Userinfos();
        list2.add(obj3);
        obj3.setNick("king");
        obj3.setIconUrl("http://xxx.com/xxx");
        obj3.setEmail("uid@taobao.com");
        obj3.setMobile("18600000000");
        obj3.setUserid("imuser123");
        obj3.setPassword("xxxxxx");
        obj3.setExtra("{}");
        obj3.setName("demo");
        obj3.setAge(123L);
        obj3.setGender("M");
        req.setUserinfos(list2);
        OpenimUsersUpdateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

    public static void addIMUser() throws ApiException {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        OpenimUsersAddRequest req = new OpenimUsersAddRequest();
        List<Userinfos> list2 = new ArrayList<Userinfos>();
        Userinfos obj3 = new Userinfos();
        list2.add(obj3);
        obj3.setNick("king");
        obj3.setIconUrl("http://xxx.com/xxx");
        obj3.setEmail("uid@taobao.com");
        obj3.setMobile("18600000000");
        obj3.setUserid("addtest1");
        obj3.setPassword("123456");
        obj3.setExtra("{}");
        obj3.setName("demo");
        obj3.setAge(123L);
        obj3.setGender("M");
        req.setUserinfos(list2);
        OpenimUsersAddResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }

}
