package top.itifrd.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import top.itifrd.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @ClassName
 * @Description
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@WebFilter("/login")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // ServletContext servletContext = servletRequest.getServletContext();
        BufferedReader reader = servletRequest.getReader();
        String result = reader.readLine();
        User user = JSON.parseObject(result, User.class);
        System.out.println("拦截到数据" + user);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
