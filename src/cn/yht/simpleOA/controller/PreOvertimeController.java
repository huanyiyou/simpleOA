package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.Overtime;
import cn.yht.simpleOA.model.OvertimeCount;
import cn.yht.simpleOA.model.PreOvertime;
import cn.yht.simpleOA.model.User;
import cn.yht.simpleOA.util.QueryHelper;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by YHT on 2015/5/19.
 */
@Controller
@Scope("prototype")
@RequestMapping("/preOvertime")
public class PreOvertimeController extends BaseAction {
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model,
                       Integer pageNum, String year, String month, Long userId) {
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
        new QueryHelper(PreOvertime.class, "p", parametersKey)
                .addCondition("p.year LIKE :year","%"+year+"%")
                .addCondition("p.month LIKE :month","%"+ month+"%")
                .addCondition("p.userName LIKE :userName" ,"%"+ userName+"%")
                .addCondition("p.submitted = true")
                .preparePageBean(preOvertimeService, pageNum, model);
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        model.addAttribute("month", month);
        model.addAttribute("months", TimeHandler.getMonths());
        return "preOvertimeViews/list";
    }

    //    @RequestMapping("/add")
//    public String add(PreOvertime  preOvertime, HttpServletRequest request){
//        preOvertime.setUserName(((User) request.getSession().getAttribute("user")).getName());
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        preOvertime.setCreatTime(df.format(new Date()));
//        preOvertime.setUpdateTime(df.format(new Date()));
//        preOvertimeService.save(preOvertime);
//        return "redirect:/preOvertime/list";
//    }
//    @RequestMapping("/addUI")
//    public String addUI(Model model){
//        model.addAttribute("preOvertime", new PreOvertime());
//        return "preOvertimeViews/saveUI";
//    }
    @RequestMapping("/edit")
    public String edit(PreOvertime preOvertime, HttpServletRequest request) {
        PreOvertime preOvertimeInDatabase = preOvertimeService.getById(preOvertime.getId());
        preOvertimeInDatabase.setRemark(preOvertime.getRemark());
        preOvertimeService.update(preOvertimeInDatabase);
        return "redirect:/preOvertime/list";
    }

    @RequestMapping("/editUI")
    public String editUI(Long id, Model model) {
        PreOvertime preOvertime = preOvertimeService.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("preOvertime", preOvertime);
        return "preOvertimeViews/saveUI";
    }

    //    @RequestMapping("/delete")
//    public String delete(Long id){
//        preOvertimeService.delete(id);
//        return "redirect:/preOvertime/list";
//    }
    @RequestMapping("/approve")
    public String approve(Long id) {
        //更新preOvertime纪录
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PreOvertime preOvertimeInDatabase = preOvertimeService.getById(id);
        preOvertimeInDatabase.setApproved(true);
        preOvertimeInDatabase.setSubmitted(false);
        preOvertimeService.update(preOvertimeInDatabase);

        //新增overtime纪录
        Overtime overtime = new Overtime();
        overtime.setDate(preOvertimeInDatabase.getDate());
        overtime.setStartTime(preOvertimeInDatabase.getStartTime());
        overtime.setEndTime(preOvertimeInDatabase.getEndTime());
        overtime.setTimeSpan(preOvertimeInDatabase.getTimeSpan());
        overtime.setUserName(preOvertimeInDatabase.getUserName());
        overtime.setDescription(preOvertimeInDatabase.getDescription());
        overtime.setCreatTime(df.format(new Date()));
        overtime.setUpdateTime(df.format(new Date()));
        overtime.setYear(preOvertimeInDatabase.getYear());
        overtime.setMonth(preOvertimeInDatabase.getMonth());
        overtime.setUser(preOvertimeInDatabase.getUser());
        overtimeService.save(overtime);

        //更新overtimeCount
        //判断overtimeCount表中是否含有该年月的纪录
        Long overtimeCountId = overtimeCountService.getSameUYMId(overtime.getUser().getId(), overtime.getYear(), overtime.getMonth());
        if (null != overtimeCountId) {
            //若有则更新
            OvertimeCount overtimeCount = overtimeCountService.getById(overtimeCountId);
            overtimeCount.setHours(overtimeCount.getHours() + TimeHandler.getHoursByTimeSpan(overtime.getTimeSpan()));
            overtimeCountService.update(overtimeCount);
        } else {
            //若无则添加
            OvertimeCount overtimeCount = new OvertimeCount(overtime.getYear(), overtime.getMonth(), TimeHandler.getHoursByTimeSpan(overtime.getTimeSpan()), overtime.getUser());
            overtimeCountService.save(overtimeCount);
        }

        return "redirect:/preOvertime/list";
    }

    @RequestMapping("/deny")
    public String deny(Long id) {
        PreOvertime preOvertimeInDatabase = preOvertimeService.getById(id);
        preOvertimeInDatabase.setSubmitted(false);
        preOvertimeInDatabase.setApproved(false);
        preOvertimeService.update(preOvertimeInDatabase);
        return "redirect:/preOvertime/list";
    }
}
