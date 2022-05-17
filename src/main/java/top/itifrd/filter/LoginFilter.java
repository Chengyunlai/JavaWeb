package top.itifrd.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import top.itifrd.filter.utils.FilterUtils;
import top.itifrd.pojo.User;
import top.itifrd.utils.DbUtils;
import top.itifrd.utils.DruidUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;

/**
 * @ClassName
 * @Description 判断客户端发送的 cookie 以及 服务器的Session 做免密登录，若有一个不匹配则从数据库中进行查询
 * @Author:chengyunlai
 * @Date
 * @Version 1.0
 **/
@WebFilter("/login")
@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String[] urls = {"/css/","/img/","/js/"};
        /**
         * @Description: 放行资源
         * @Param: [servletRequest, servletResponse, filterChain]
         * @return: void
         * @Author: chengyunlai
         * @Date: 2022/5/13
         */
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String url = httpServletRequest.getRequestURI().toString();
        for (String u : urls){
            if (url.contains(u)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }

        // 获取上下文内容，用来将来判断上下文中是否包含有注册的信息
        ServletContext servletContext = httpServletRequest.getServletContext();
        // 获取表单的数据
        BufferedReader reader = httpServletRequest.getReader();
        // 将表单的数据转换成 JSON 对象
        JSONObject jsonObject = FilterUtils.formDataToJSONObject(reader);
        // 输出 表单数据中 姓名 和 密码 信息
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        log.info("拦截到表单的姓名" + username);
        log.info("拦截到表单的密码" + password);
        if (username.length()==0 || password.length()==0){
            log.error("用户名或者密码为空");
        }else{
            Connection connection = DruidUtils.getConnection();
            String sql =  "select * from user where (username=? and password=?)";
            User oneByCondition = DbUtils.getOneByCondition(User.class, sql, connection, username, password);
            if (oneByCondition == null){
                log.error("拦截反馈:用户名或密码不存在");
            }else{
                // 放行到LoginServlet中给客户端发放cookie，服务器存session
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
        // filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
