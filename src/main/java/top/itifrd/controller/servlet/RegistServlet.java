package top.itifrd.controller.servlet;

import com.alibaba.fastjson.JSONObject;
import top.itifrd.filter.utils.FilterUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@WebServlet("/regist")
public class RegistServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 获取上下文内容，用来将来判断上下文中是否包含有注册的信息
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletContext servletContext = httpServletRequest.getServletContext();
        System.out.println("servlet获取表单数据:" + servletContext.getAttribute("users"));
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
