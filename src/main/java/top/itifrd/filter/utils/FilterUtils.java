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
        // String result = null;
        JSONObject jsonObject = null;
        // 接收来自前端发送来的数据
        StringBuilder sb = new StringBuilder();
        try {
            String res = null;
            while ((res = formData.readLine())!= null){
                sb.append(res);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jsonObject = JSON.parseObject(sb.toString());
        return jsonObject;
    }
}
