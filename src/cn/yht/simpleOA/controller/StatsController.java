package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.User;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

/**
 * Created by YHT on 2015/6/1.
 */
@Controller
@Scope("prototype")
@RequestMapping("/stats")
public class StatsController extends BaseAction {
    @RequestMapping("/department")
    public String department(Model model,Long userId, String year){
        Double[] overtimes = new Double[12];
        Double[] breaktimes = new Double[12];
        if(null != userId && null!= year && !"".equals(year) && userId != 0){
            overtimes = overtimeCountService.getTimeByUserIdAndYear(userId, year);
            breaktimes = breaktimeCountService.getTimeByUserIdAndYear(userId, year);
        }else {
            userId = (long) 2;
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            overtimes = overtimeCountService.getTimeByUserIdAndYear(userId, year);
            breaktimes = breaktimeCountService.getTimeByUserIdAndYear(userId, year);
        }
        List<User> users = userService.findAll();
        users.remove(0);
        model.addAttribute("overtimes", overtimes);
        model.addAttribute("breaktimes", breaktimes);
        model.addAttribute("userId", userId);
        model.addAttribute("users", users);
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        return "/statsViews/department";
    }

    @RequestMapping("/user")
    public String user(Model model, String year, HttpSession session){
        Double[] overtimes = new Double[12];
        Double[] breaktimes = new Double[12];
        Long userId = ((User)session.getAttribute("user")).getId();
        if( null == year && "".equals(year)){
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }
        overtimes = overtimeCountService.getTimeByUserIdAndYear(userId, year);
        breaktimes = breaktimeCountService.getTimeByUserIdAndYear(userId, year);

        model.addAttribute("overtimes", overtimes);
        model.addAttribute("breaktimes", breaktimes);
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        return "/statsViews/user";
    }
}
