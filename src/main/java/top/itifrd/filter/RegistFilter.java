package top.itifrd.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import top.itifrd.filter.utils.FilterUtils;
import top.itifrd.pojo.User;

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
@WebFilter("/regist")
@Slf4j
public class RegistFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        System.out.println("数据过滤处理");
        // 获取表单的数据
        httpServletRequest.setCharacterEncoding("utf-8");
        BufferedReader reader = httpServletRequest.getReader();
        // 将表单的数据转换成 JSON 对象
        JSONObject jsonObject = FilterUtils.formDataToJSONObject(reader);
        User user = JSON.parseObject(jsonObject.toJSONString(), User.class);
        // 输出 表单数据中 姓名 和 密码 信息
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        log.info("拦截到表单的姓名" + username);
        log.info("拦截到表单的密码" + password);
        if (username.length()==0 || password.length()==0){
            log.error("用户名或者密码为空");
        }else {
            // httpServletRequest.setAttribute("username",username);
            // httpServletRequest.setAttribute("password",password);
            httpServletRequest.setAttribute("user",jsonObject);
            filterChain.doFilter(servletRequest,servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
