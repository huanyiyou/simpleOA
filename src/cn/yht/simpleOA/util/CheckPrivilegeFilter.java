package cn.yht.simpleOA.util;


import cn.yht.simpleOA.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by YHT on 2015/5/17.
 */
public class CheckPrivilegeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("----过滤器初始化----");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String currentURL = httpRequest.getRequestURI();//取得根目录所对应的绝对路径:
        String targetURL = currentURL.substring(currentURL.indexOf("/", 1), currentURL.length());
        HttpSession session = httpRequest.getSession(false);

        System.out.println("CheckPrivilegeFilter 执行前！！！");

        System.out.println("currentURL=" + currentURL);
        System.out.println("targetURL=" + targetURL);
        if (! currentURL.startsWith("/user/login")) {//判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
            if (session == null || session.getAttribute("user") == null) {//*用户登录以后需手动添加session
                System.out.println("request.getContextPath()=" + httpRequest.getContextPath());
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/user/loginUI");//如果session为空表示用户没有登录就重定向到login.jsp页面
                return;
            } else {
                User user = (User) httpRequest.getSession().getAttribute("user");
                if (user.hasPrivilegeByUrl(currentURL)) {
                    chain.doFilter(request, response);
                } else {
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/user/loginUI");
                }
            }
        }
        System.out.println("CheckPrivilegeFilter 执行后！！！");
    }

    @Override
    public void destroy() {

        System.out.println("----过滤器销毁----");
    }
}
