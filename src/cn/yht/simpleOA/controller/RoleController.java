package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.Privilege;
import cn.yht.simpleOA.model.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;

/**
 * Created by admin on 2015/5/14.
 */
@Controller
@Scope("prototype")
@RequestMapping("/role")
public class RoleController extends BaseAction{
    @RequestMapping("/list")
    public ModelAndView list(){
        List<Role> roleList = roleService.findAll();
        return new ModelAndView("roleViews/list", "roleList", roleList);
    }
    @RequestMapping("/add")
    public String add(@ModelAttribute("role")Role  role){
        roleService.save(role);
        return "redirect:/role/list";
    }
    @RequestMapping("/addUI")
    public String addUI(Model model){
        model.addAttribute("role", new Role());
        return "roleViews/saveUI";
    }
    @RequestMapping("/edit")
    public String edit(Role role){
        if(role != null) {
            Role roleIndatabase = roleService.getById(role.getId());
            roleIndatabase.setName(role.getName());
            roleIndatabase.setDescription(role.getDescription());
            roleService.update(roleIndatabase);
        }
        return "redirect:/role/list";
    }
    @RequestMapping("/editUI")
    public String editUI(Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("role",roleService.getById(id));
        return "roleViews/saveUI";
    }
    @RequestMapping("/delete")
    public String delete(Long id){
        roleService.delete(id);
        return "redirect:/role/list";
    }


    @RequestMapping("/setPrivilegeUI")
    public String setPrivilegeUI(Long id, Model model){
        Role role = roleService.getById(id);


        if(null != role.getPrivileges()){
            int index = 0;
            Long[] temp = new Long[role.getPrivileges().size()];
            for(Privilege privilege : role.getPrivileges()){
                temp[index++] = privilege.getId();
            }
            role.setPrivilegeIds(temp);
        }

        List<Privilege> privilegeList = privilegeService.findAll();

        model.addAttribute("privilegeList", privilegeList);
        model.addAttribute("role", role);
        return "/roleViews/setPrivilegeUI";
    }

    @RequestMapping("/setPrivilege")
    public String setPrivilege(Long id, Role role){
        Role roleInDatabase = roleService.getById(id);
        List<Privilege> privilegeList = privilegeService.getByIds(role.getPrivilegeIds());
        roleInDatabase.setPrivileges(new HashSet<>(privilegeList));
        roleService.update(roleInDatabase);
        return "redirect:/role/list";
    }

}
