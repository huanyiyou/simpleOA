package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.Overtime;
import cn.yht.simpleOA.model.User;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by YHT on 2015/5/29.
 */
@Controller
@Scope("prototype")
@RequestMapping("/chart")
public class ChartController extends BaseAction{
    @RequestMapping("/department")
    public String departmentView(Model model,Long userId, String year){
        Double[] overtimes = new Double[12];
        Double[] breaktimes = new Double[12];
        if(null == year || "".equals(year)){
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }
       if(null == userId || userId == 0){
           //如果是所有人
           userId = (long) 0;
           overtimes = overtimeService.getSumByYear(year);
           breaktimes = breaktimeService.getSumByYear(year);
       }
        else {
           //如果指定userId
           overtimes = overtimeService.getSumByUserIdAndYear(userId, year);
           breaktimes = breaktimeService.getSumByUserIdAndYear(userId, year);
        }
        model.addAttribute("overtimes", overtimes);
        model.addAttribute("breaktimes", breaktimes);

        List<User> users = userService.findAll();
        users.remove(0);
        model.addAttribute("userId", userId);
        model.addAttribute("users", users);
        if(userId == 0){
            model.addAttribute("userName", "部门所有人");
        }
        else {
            model.addAttribute("userName", userService.getById(userId).getName());
        }
        model.addAttribute("year", year);

        //添加years
        model.addAttribute("years", TimeHandler.getYears());
        return "/chartViews/department";
    }

    @RequestMapping("/user")
    public String userView(Model model, String year, HttpServletRequest request){
        Double[] overtimes = new Double[12];
        Double[] breaktimes = new Double[12];
        if(null == year || "".equals(year)){
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }
        overtimes = overtimeService.getSumByUserIdAndYear(((User)request.getSession().getAttribute("user")).getId(), year);
        breaktimes = breaktimeService.getSumByUserIdAndYear(((User) request.getSession().getAttribute("user")).getId(), year);
        model.addAttribute("overtimes", overtimes);
        model.addAttribute("breaktimes", breaktimes);

        model.addAttribute("year", year);
        //添加years
        model.addAttribute("years", TimeHandler.getYears());
        return "/chartViews/user";
    }
}
