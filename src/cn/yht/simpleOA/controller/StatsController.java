package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.Breaktime;
import cn.yht.simpleOA.model.Overtime;
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

        //分月记录
        model.addAttribute("overtimes", overtimes);
        model.addAttribute("breaktimes", breaktimes);

        //年度统计
        model.addAttribute("overtimeSum", TimeHandler.getArraySum(overtimes));
        model.addAttribute("breaktimeSum", TimeHandler.getArraySum(breaktimes));
        model.addAttribute("yearResult", TimeHandler.getArraySum(overtimes) - TimeHandler.getArraySum(breaktimes));

        //所有记录统计
        double allOvertimeSum = TimeHandler.getArraySum(overtimeService.getSumByUserId(userId));
        double allBreaktimeSum = TimeHandler.getArraySum(breaktimeService.getSumByUserId(userId));
        String allLeft = df.format(allOvertimeSum - allBreaktimeSum);
        model.addAttribute("overtimeAllSum", allOvertimeSum);
        model.addAttribute("breaktimeAllSum", allBreaktimeSum);
        model.addAttribute("allResult", allLeft);

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

        //分月记录
        model.addAttribute("overtimes", overtimes);
        model.addAttribute("breaktimes", breaktimes);

        //年度统计
        model.addAttribute("overtimeSum", TimeHandler.getArraySum(overtimes));
        model.addAttribute("breaktimeSum", TimeHandler.getArraySum(breaktimes));
        model.addAttribute("yearResult", TimeHandler.getArraySum(overtimes) - TimeHandler.getArraySum(breaktimes));

        //所有记录统计
        double allOvertimeSum = TimeHandler.getArraySum(overtimeService.getSumByUserId(userId));
        double allBreaktimeSum = TimeHandler.getArraySum(breaktimeService.getSumByUserId(userId));
        String allLeft = df.format(allOvertimeSum - allBreaktimeSum);
        model.addAttribute("overtimeAllSum", allOvertimeSum);
        model.addAttribute("breaktimeAllSum", allBreaktimeSum);
        model.addAttribute("allResult", allLeft);

        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        return "/statsViews/user";
    }
}
