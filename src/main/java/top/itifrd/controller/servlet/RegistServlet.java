package top.itifrd.controller.servlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import top.itifrd.res.R;
import top.itifrd.utils.DbUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@WebServlet("/regist")
@Slf4j
public class RegistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jsonObject = (JSONObject) req.getAttribute("user");
        System.out.println(jsonObject);
        // 过滤出我们需要的内容
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        System.out.println(username + password);
        String sql = "insert into user(username,password) values(?,?)";
        Connection connection = DbUtils.getMySqlConnection();
        // System.out.println(connection);
        int i = DbUtils.exec(sql, connection, username, password);
        if (i != 0){
            log.info("注册成功");
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            String res = JSON.toJSONString(new R(true));
            writer.write(res);
            // resp.setHeader("Location","/JavaWeb/index.html");
            // req.getRequestDispatcher("index.html").forward(req, resp);
        }else {
            log.info("注册失败");
        }




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
