package cn.yht.simpleOA.util;

import cn.yht.simpleOA.model.Privilege;
import cn.yht.simpleOA.service.PrivilegeService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Collection;
import java.util.List;

/**
 * Created by YHT on 2015/5/16.
 */
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        PrivilegeService privilegeService = (PrivilegeService) applicationContext.getBean("privilegeServiceImpl");
        List<Privilege> privilegeTopList = privilegeService.getTopList();
        servletContextEvent.getServletContext().setAttribute("privilegeTopList", privilegeTopList);
        System.out.println("-----------------已准备好数据privilegeTopList----------------");

        Collection<String> allPrivilegeUrls = privilegeService.getAllUrls();
        servletContextEvent.getServletContext().setAttribute("allPrivilegeUrls", allPrivilegeUrls);
        System.out.println("-----------------已准备好数据allPrivilegeUrls-----------------");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
