package top.itifrd.filter.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
public class FilterUtils {
    public static JSONObject formDataToJSONObject(BufferedReader formData){
        System.out.println("接收到表单数据:" + formData);
        String result = null;
        JSONObject jsonObject = null;
        try {
            result = formData.readLine();
            System.out.println("拦截到数据:"+ result);

        }catch (Exception e){
            e.printStackTrace();
        }
        jsonObject = JSON.parseObject(result);
        return jsonObject;
    }
}
