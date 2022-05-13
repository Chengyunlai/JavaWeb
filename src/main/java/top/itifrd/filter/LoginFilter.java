package top.itifrd.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import top.itifrd.filter.utils.FilterUtils;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
        // 校验用户名和密码是否符合规范
        JSONObject users = (JSONObject) servletContext.getAttribute("users");
        if (users == null){
            System.out.println("未注册");
            // 若用户未登录则转到登录界面
        }else {
            String contextUsername = users.getString("username");
            String contextPassword = users.getString("password");
            log.info("servletContext中的username:" + contextUsername);
            log.info("servletContext中的password:" + contextPassword);
            if(username.equals(contextUsername) && password.equals(contextPassword)){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                log.info("用户名或者密码错误");
            }
        }

        // filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
