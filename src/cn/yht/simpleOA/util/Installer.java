package cn.yht.simpleOA.util;

import cn.yht.simpleOA.model.Privilege;
import cn.yht.simpleOA.model.Role;
import cn.yht.simpleOA.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by YHT on 2015/5/16.
 */
@Component
public class Installer {
    @Resource
    private SessionFactory sessionFactory;
    @Transactional
    public void install(){
        Session session = sessionFactory.getCurrentSession();
        //添加部门管理员角色和普通用户角色
        Role departmentAdmin = new Role("部门管理员", "拥有除系统管理的其他权限");
        Role commonUser = new Role("普通用户", "拥有除系统管理和部门管理的其他基本权限");
        session.save(departmentAdmin);
        session.save(commonUser);

        //新建部门管理员角色集合和普通用户角色集合，用于创建用户时添加。
        Set<Role> departmentAdminRole = new HashSet<>();
        departmentAdminRole.add(departmentAdmin);
        Set<Role> commonUserRole = new HashSet<>();
        commonUserRole.add(commonUser);

        //保存用户
        session.save(new User("admin", "超级管理员", DigestUtils.md5Hex("admin")));
        session.save(new User("cuijinying", "崔金英", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("daiqingyi", "戴晴宜", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("fangjie", "方杰", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("jiaying", "贾颖", DigestUtils.md5Hex("111111"), departmentAdminRole));
        session.save(new User("suntao", "孙涛", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("suyuwen", "苏宇文", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("wangshushu", "王姝姝", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("wangjing", "王静", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("wuhaizhou", "吴海洲", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("yangqian", "杨倩", DigestUtils.md5Hex("111111"), departmentAdminRole));
        session.save(new User("yanjia", "颜佳", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("yeting", "叶婷", DigestUtils.md5Hex("111111"), commonUserRole));
        session.save(new User("yuanhaitao", "袁海涛", DigestUtils.md5Hex("111111"), commonUserRole));



        
        //保存权限信息
        Privilege menu, menu1, menu2, menu3, menu4, menu5;
        //系统管理
        menu = new Privilege("系统管理", null, null);
        menu1 = new Privilege("角色管理", "/role/list", menu);
        menu2 = new Privilege("用户管理", "/user/list", menu);
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        
        session.save(new Privilege("角色列表", "/role/list", menu1));
        session.save(new Privilege("角色删除", "/role/delete", menu1));
        session.save(new Privilege("角色添加", "/role/add", menu1));
        session.save(new Privilege("角色修改", "/role/edit", menu1));
        session.save(new Privilege("设置权限", "/role/setPrivilege", menu1));


        session.save(new Privilege("用户列表", "/user/list", menu2));
        session.save(new Privilege("用户删除", "/user/delete", menu2));
        session.save(new Privilege("用户添加", "/user/add", menu2));
        session.save(new Privilege("用户修改", "/user/edit", menu2));
        session.save(new Privilege("初始化密码", "/user/initPassword", menu2));

        //部门管理
        menu = new Privilege("部门管理", null, null);
        menu1 = new Privilege("部门加班记录", "/overtime/list", menu);
        menu2 = new Privilege("审核加班记录申请", "/preOvertime/list", menu);
        menu3 = new Privilege("休假记录管理", "/breaktime/list", menu);
        menu4 = new Privilege("部门统计图", "/chart/department", menu);
        menu5 = new Privilege("部门统计表", "/stats/department", menu);
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);
        session.save(menu4);
        session.save(menu5);

        session.save(new Privilege("部门加班记录列表", "/overtime/list", menu1));


        session.save(new Privilege("加班记录申请列表", "/preOvertime/list", menu2));
        session.save(new Privilege("加班记录申请编辑审核意见", "/preOvertime/edit", menu2));
        session.save(new Privilege("加班记录申请审核通过", "/preOvertime/approve", menu2));
        session.save(new Privilege("加班记录申请审核拒绝", "/preOvertime/deny", menu2));


        session.save(new Privilege("休假记录列表", "/breaktime/list", menu3));
        session.save(new Privilege("休假记录删除", "/breaktime/delete", menu3));
        session.save(new Privilege("休假记录添加", "/breaktime/add", menu3));
        session.save(new Privilege("休假记录修改", "/breaktime/edit", menu3));


        //个人加班记录
        menu = new Privilege("个人加班休假记录", null, null);
        menu1 = new Privilege("加班申请", "/selfPreOvertime/list", menu);
        menu2 = new Privilege("休假记录", "/selfBreaktime/list", menu);
        menu3 = new Privilege("加班记录", "/selfOvertime/list", menu);
        session.save(menu);
        session.save(menu1);
        session.save(menu2);
        session.save(menu3);
        session.save(new Privilege("加班申请列表", "/selfPreOvertime/list", menu1));
        session.save(new Privilege("加班申请删除", "/selfPreOvertime/delete", menu1));
        session.save(new Privilege("加班申请添加", "/selfPreOvertime/add", menu1));
        session.save(new Privilege("加班申请修改", "/selfPreOvertime/edit", menu1));
        session.save(new Privilege("休假记录", "/selfBreaktime/list", menu2));
        session.save(new Privilege("加班记录", "/selfOvertime/list", menu3));


        //记录统计
        menu = new Privilege("记录统计", null, null);
        session.save(menu);
        session.save(new Privilege("统计图", "/chart/user", menu));
        session.save(new Privilege("统计表", "/stats/user", menu));


    }

    public static void main(String args[]){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
        Installer installer = (Installer) applicationContext.getBean("installer");
        installer.install();
    }
}
