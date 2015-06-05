package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.Overtime;
import cn.yht.simpleOA.model.User;
import cn.yht.simpleOA.util.QueryHelper;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by YHT on 2015/5/19.
 */
@Controller
@Scope("prototype")
@RequestMapping("/overtime")
public class OvertimeController extends BaseAction {
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model,
                       Integer pageNum, String year, String month, Long userId){
        String userName;
        if(pageNum == null){
            pageNum = 1;
        }
        if(null == year || "".equals(year)){
            year = "";
        }
        if(null == month || "".equals(month)){
            month = "";
        }
        if(null == userId || userId == 0){
            //如果是所有人
            userId = (long) 0;
        }

        List<User> users = userService.findAll();
        users.remove(0);
        model.addAttribute("userId", userId);
        model.addAttribute("users", users);
        if(userId == 0){
            userName = "";
        }
        else {
            userName = userService.getById(userId).getName();
        }
        List<String> parametersKey = new ArrayList<>();
        parametersKey.add("year");
        parametersKey.add("month");
        parametersKey.add("userName");
        new QueryHelper(Overtime.class, "o", parametersKey)
                .addCondition("o.year LIKE :year","%"+year+"%")
                .addCondition("o.month LIKE :month","%"+ month+"%")
                .addCondition("o.userName LIKE :userName" ,"%"+ userName+"%")
                .preparePageBean(overtimeService, pageNum, model);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        model.addAttribute("month", month);
        model.addAttribute("months", TimeHandler.getMonths());

        return "overtimeViews/list";
    }
    @RequestMapping("/add")
    public String add(Overtime  overtime, HttpServletRequest request){
        overtime.setUserName(((User) request.getSession().getAttribute("user")).getName());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        overtime.setCreatTime(df.format(new Date()));
        overtime.setUpdateTime(df.format(new Date()));
        overtimeService.save(overtime);
        return "redirect:/overtime/list";
    }
    @RequestMapping("/addUI")
    public String addUI(Model model){
        model.addAttribute("overtime", new Overtime());
        return "overtimeViews/saveUI";
    }
    @RequestMapping("/edit")
    public String edit(Overtime overtime, HttpServletRequest request){
        Overtime overtimeInDatabase = overtimeService.getById(overtime.getId());
        overtimeInDatabase.setDate(overtime.getDate());
        overtimeInDatabase.setStartTime(overtime.getStartTime());
        overtimeInDatabase.setEndTime(overtime.getEndTime());
        overtimeInDatabase.setTimeSpan(overtime.getTimeSpan());
        overtimeInDatabase.setDescription(overtime.getDescription());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        overtimeInDatabase.setUpdateTime(df.format(new Date()));// new Date()为获取当前系统时间

        overtimeInDatabase.setYear(overtime.getYear());
        overtimeInDatabase.setMonth(overtime.getMonth());
        overtimeInDatabase.setRemark(overtime.getRemark());

        overtimeService.update(overtimeInDatabase);
        return "redirect:/overtime/list";
    }
    @RequestMapping("/editUI")
    public String editUI(Long id, Model model){
        Overtime overtime = overtimeService.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("overtime", overtime);
        return "overtimeViews/saveUI";
    }
    @RequestMapping("/delete")
    public String delete(Long id){
        overtimeService.delete(id);
        return "redirect:/overtime/list";
    }
}
