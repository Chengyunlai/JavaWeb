package top.itifrd.controller.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.itifrd.filter.utils.FilterUtils;
import top.itifrd.pojo.User;
import top.itifrd.utils.DbUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@WebServlet("/regist")
public class RegistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        // 接收来自前端发送来的数据
        StringBuilder sb = new StringBuilder();
        String res = null;
        while ((res = reader.readLine())!= null){
            sb.append(res);
        }
        // 转成JSON对象
        JSONObject jsonObject = JSON.parseObject(sb.toString());
        System.out.println(jsonObject);
        // 过滤出我们需要的内容
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        System.out.println(username + password);
        String sql = "insert into user(user_name,password) values(?,?)";
        Connection connection = DbUtils.getMySqlConnection();
        System.out.println(connection);
        int i = DbUtils.exec(sql, connection, username, password);

        String jsonString = JSON.toJSONString(i);
        //相应数据 application/json text/json
        resp.setContentType("text/json;charset=utf-8");
        // 响应数据(通用请求数据待更新)
        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
