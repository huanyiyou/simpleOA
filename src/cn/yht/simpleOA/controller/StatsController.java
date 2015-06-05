package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.User;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
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
        DecimalFormat df = new DecimalFormat("#.###");
        double[] overtimes;
        double[] breaktimes;
        if(null != userId && null!= year && !"".equals(year) && userId != 0){
            overtimes = overtimeService.getSumByUserIdAndYear(userId, year);
            breaktimes = breaktimeService.getSumByUserIdAndYear(userId, year);
        }else {
            userId = (long) 2;
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            overtimes = overtimeService.getSumByUserIdAndYear(userId, year);
            breaktimes = breaktimeService.getSumByUserIdAndYear(userId, year);
        }
        List<User> users = userService.findAll();
        users.remove(0);
        model.addAttribute("overtimes", overtimes);
        model.addAttribute("breaktimes", breaktimes);

        //计算年度数据
        double overtimeSum = 0.0;
        double breaktimeSum = 0.0;
        for(double ot :overtimes){
            overtimeSum += ot;
        }
        for(double bt : breaktimes){
            breaktimeSum += bt;
        }

        model.addAttribute("overtimeSum", df.format(overtimeSum));
        model.addAttribute("breaktimeSum", df.format(breaktimeSum));
        model.addAttribute("yearResult", df.format(overtimeSum - breaktimeSum));
        model.addAttribute("userId", userId);
        model.addAttribute("users", users);
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        return "/statsViews/department";
    }

    @RequestMapping("/user")
    public String user(Model model, String year, HttpSession session){
        DecimalFormat df = new DecimalFormat("#.###");
        double[] overtimes;
        double[] breaktimes;
        Long userId = ((User)session.getAttribute("user")).getId();
        if( null == year || "".equals(year)){
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        }
        overtimes = overtimeService.getSumByUserIdAndYear(userId, year);
        breaktimes = breaktimeService.getSumByUserIdAndYear(userId, year);

        model.addAttribute("overtimes", overtimes);
        model.addAttribute("breaktimes", breaktimes);

        //计算年度数据
        Double overtimeSum = 0.0;
        Double breaktimeSum = 0.0;
        for(Double ot :overtimes){
            if(null != ot){
                overtimeSum += ot;
            }
        }
        for(Double bt : breaktimes){
            if(null != bt){
                breaktimeSum += bt;
            }
        }

        model.addAttribute("overtimeSum", df.format(overtimeSum));
        model.addAttribute("breaktimeSum", df.format(breaktimeSum));
        model.addAttribute("yearResult", df.format(overtimeSum - breaktimeSum));
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        return "/statsViews/user";
    }
}
