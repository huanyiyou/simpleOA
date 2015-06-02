package cn.yht.simpleOA.util;


import cn.yht.simpleOA.model.Privilege;
import cn.yht.simpleOA.model.Role;
import cn.yht.simpleOA.model.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by YHT on 2015/5/17.
 */
public class CheckPrivilegeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        String uri = httpServletRequest.getRequestURI();
        System.out.println(">>>: " + "----------------preHandle------------------");
        System.out.println(">>>:uri " + uri);
        if(null == user){
            if(uri.startsWith("/user/login") ){
                return true;
            }else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/loginUI");
                return false;
            }

        }else {
            if(user.hasPrivilegeByUrl(uri)){
                return true;
            }else {
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/user/loginUI");
//                httpServletRequest.getRequestDispatcher("/user/loginUI").forward(httpServletRequest,httpServletResponse);
                return false;
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
