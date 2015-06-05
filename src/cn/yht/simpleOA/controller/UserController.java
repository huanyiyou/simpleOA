package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.Role;
import cn.yht.simpleOA.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;

/**
 * Created by admin on 2015/5/13.
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController extends BaseAction {
    @RequestMapping("/list")
    public ModelAndView list(){
        List<User> userList = userService.findAll();
        return new ModelAndView("userViews/list", "userList", userList);
    }
    @RequestMapping("/add")
    public String add(User  user){
        if(userService.hasSameLoginName(user)){
            return "redirect:userViews/saveUI";
        }else {
            user.setPassword(DigestUtils.md5Hex("111111"));
            user.setRoles(new HashSet<>(roleService.getByIds(user.getRoleIds())));
            userService.save(user);
            return "redirect:/user/list";
        }
    }
    @RequestMapping("/addUI")
    public String addUI(Model model){
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);
        model.addAttribute("user", new User());
        return "userViews/saveUI";
    }
    @RequestMapping("/edit")
    public String edit(User user){
        User userInDatabase = userService.getById(user.getId());
        userInDatabase.setLoginName(user.getLoginName());
        userInDatabase.setName(user.getName());
        userInDatabase.setRoles(new HashSet<>(roleService.getByIds(user.getRoleIds())));
        userService.update(userInDatabase);
        return "redirect:/user/list";
    }
    @RequestMapping("/editUI")
    public String editUI(Long id, Model model){
        User user = userService.getById(id);
        model.addAttribute("roleList", roleService.findAll());
        int index = 0;
        Long[] temp = new Long[user.getRoles().size()];
        for(Role role : user.getRoles()){
            temp[index++] = role.getId();
        }
        user.setRoleIds(temp);
        model.addAttribute("id", id);
        model.addAttribute("user", user);
        return "userViews/saveUI";
    }
    @RequestMapping("/delete")
    public String delete(Long id){
        userService.delete(id);
        return "redirect:/user/list";
    }
    @RequestMapping("/initPassword")
    public String initPassword(Long id){
        User user = userService.getById(id);
        user.setPassword(DigestUtils.md5Hex("111111"));
        userService.update(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/loginUI")
    public String loginUI(Model model){
        model.addAttribute("message", "");
        return "/userViews/loginUI";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("loginName") String loginName, @RequestParam("password") String password, HttpSession session, Model model){
        User userInDatabase = userService.findByLoginNameAndPassword(loginName, password);
        if(null == userInDatabase){
            model.addAttribute("message", "用户名或密码错误");
            return "/userViews/loginUI";

        }else {
            session.setAttribute("user", userInDatabase);
            return "redirect:/home/index";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "/userViews/logout";
    }

    @RequestMapping("/setting")
    public String setting(HttpSession session, String currentPassword, String newPassword, Model model){
        String message;
        //判断是否登陆用户
        if(null != session.getAttribute("user")){
            //判断当前密码是否与数据库中吻合
            User userInDatabase = userService.findByLoginNameAndPassword(((User)session.getAttribute("user")).getLoginName(), currentPassword);
            if(null == userInDatabase){
                message =  "当前密码错误";
            }else {
                userInDatabase.setPassword(DigestUtils.md5Hex(newPassword));
                userService.update(userInDatabase);
                message = "密码修改成功";
            }
            model.addAttribute("message", message);
            return "userViews/settingUI";
        }
        else {
            return "redirect:/user/loginUI";
        }
    }
    @RequestMapping("/settingUI")
    public String settingUI(Model model){
        model.addAttribute("message", "");
        return "userViews/settingUI";
    }
}
