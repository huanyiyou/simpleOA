package cn.yht.simpleOA.base;

import cn.yht.simpleOA.service.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by admin on 2015/5/15.
 */
@Controller
@Scope("prototype")
public abstract class BaseAction {
    @Resource
    protected UserService userService;
    @Resource
    protected RoleService roleService;
    @Resource
    protected PrivilegeService privilegeService;
    @Resource
    protected OvertimeService overtimeService;
    @Resource
    protected PreOvertimeService preOvertimeService;
    @Resource
    protected BreaktimeService breaktimeService ;
    @Resource
    protected OvertimeCountService overtimeCountService;
    @Resource
    protected BreaktimeCountService breaktimeCountService;
}
