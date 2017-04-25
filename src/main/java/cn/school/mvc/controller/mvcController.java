package cn.school.mvc.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/mvc")
public class mvcController {


    @RequestMapping(value = "/hello",
            method = RequestMethod.GET,
            produces = "application/string; encoding=UTF-8;charset=UTF-8")
    @ResponseBody
    public String hello() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("123","trytry");
        Gson gson = new Gson();
        gson.toJson("Hello123个fdgfdgfdg");
        return "Hello123个fdgfdgfdg";
    }
}
