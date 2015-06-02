package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.Breaktime;
import cn.yht.simpleOA.model.BreaktimeCount;
import cn.yht.simpleOA.model.User;
import cn.yht.simpleOA.util.QueryHelper;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YHT on 2015/5/19.
 */
@Controller
@Scope("prototype")
@RequestMapping("/breaktime")
public class BreaktimeController extends BaseAction {
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
        new QueryHelper(Breaktime.class, "b", parametersKey)
                .addCondition("b.year LIKE :year","%"+year+"%")
                .addCondition("b.month LIKE :month","%"+ month+"%")
                .addCondition("b.userName LIKE :userName" ,"%"+ userName+"%")
                .preparePageBean(overtimeService, pageNum, model);
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        model.addAttribute("month", month);
        model.addAttribute("months", TimeHandler.getMonths());
        return "breaktimeViews/list";
    }
    @RequestMapping("/add")
    public String add(Breaktime  breaktime, HttpServletRequest request){
        breaktime.setUser(userService.getById(breaktime.getUserId()));
        breaktime.setUserName(breaktime.getUser().getName());
        breaktime.setYear(TimeHandler.getYearByDate(breaktime.getDate()));
        breaktime.setMonth(TimeHandler.getMonthByDate(breaktime.getDate()));
        breaktimeService.save(breaktime);


        //更新breaktimeCount
        //判断breaktimeCount表中是否含有该年月的纪录
        Long breaktimeCountId = breaktimeCountService.getSameUYMId(breaktime.getUser().getId(), breaktime.getYear(), breaktime.getMonth());
        if (null != breaktimeCountId) {
            //若有则更新
            BreaktimeCount breaktimeCount = breaktimeCountService.getById(breaktimeCountId);
            breaktimeCount.setHours(breaktimeCount.getHours() + breaktime.getDuration());
            breaktimeCountService.update(breaktimeCount);
        } else {
            //若无则添加
            BreaktimeCount breaktimeCount = new BreaktimeCount(breaktime.getYear(), breaktime.getMonth(), breaktime.getDuration(), breaktime.getUser());
            breaktimeCountService.save(breaktimeCount);
        }
        return "redirect:/breaktime/list";
    }
    @RequestMapping("/addUI")
    public String addUI(Model model){
        List<User> users = userService.findAll();
        users.remove(0);
        model.addAttribute("users", users);
        model.addAttribute("breaktime", new Breaktime());
        return "breaktimeViews/saveUI";
    }
    @RequestMapping("/edit")
    public String edit(Breaktime breaktime, HttpServletRequest request){
        Breaktime breaktimeInDatabase = breaktimeService.getById(breaktime.getId());

        //更新breaktimeCount表中数据
        Long breaktimeCountId = breaktimeCountService.getSameUYMId(breaktime.getUser().getId(), breaktime.getYear(), breaktime.getMonth());
        BreaktimeCount breaktimeCount = breaktimeCountService.getById(breaktimeCountId);
        breaktimeCount.setHours(breaktimeCount.getHours() - breaktimeInDatabase.getDuration() + breaktime.getDuration());
        breaktimeCountService.update(breaktimeCount);

        //更新breaktime表中数据
        breaktimeInDatabase.setUser(userService.getById(breaktime.getUserId()));
        breaktimeInDatabase.setUserName(breaktimeInDatabase.getUser().getName());
        breaktimeInDatabase.setDuration(breaktime.getDuration());
        breaktimeInDatabase.setDate(breaktime.getDate());
        breaktimeInDatabase.setYear(TimeHandler.getYearByDate(breaktime.getDate()));
        breaktimeInDatabase.setMonth(TimeHandler.getMonthByDate(breaktime.getDate()));

        breaktimeInDatabase.setDescription(breaktime.getDescription());




        breaktimeService.update(breaktimeInDatabase);
        return "redirect:/breaktime/list";
    }
    @RequestMapping("/editUI")
    public String editUI(Long id, Model model){
        List<User> users = userService.findAll();
        users.remove(0);
        model.addAttribute("users", users);
        Breaktime breaktime = breaktimeService.getById(id);
        breaktime.setUserId(breaktime.getUser().getId());
        model.addAttribute("id", id);
        model.addAttribute("breaktime", breaktime);
        return "breaktimeViews/saveUI";
    }
    @RequestMapping("/delete")
    public String delete(Long id){
        breaktimeService.delete(id);
        return "redirect:/breaktime/list";
    }
}
