package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.PageBean;
import cn.yht.simpleOA.model.PreOvertime;
import cn.yht.simpleOA.model.User;
import cn.yht.simpleOA.service.PreOvertimeService;
import cn.yht.simpleOA.util.QueryHelper;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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
@RequestMapping("selfPreOvertime")
public class SelfPreOvertimeController extends BaseAction {


    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model,
                        Integer pageNum, String year, String month, String approved) {
//        List<PreOvertime> selfPreOvertimeList = preOvertimeService.findAllByUserId(((User) request.getSession().getAttribute("user")).getId());
//        return new ModelAndView("selfPreOvertimeViews/list", "selfPreOvertimeList", selfPreOvertimeList);
        if(pageNum == null){
            pageNum = 1;
        }
        if(year == null){
            year = "";
        }
        if(month == null){
            month = "";
        }
        if(approved == null){
            approved = "";
        }
//        PageBean result = preOvertimeService.getPageBeanByUserId(pageNum, 10, ((User) request.getSession().getAttribute("user")).getId());
//        String hql = "FROM PreOvertime p WHERE p.user.id = ?";
//        List<Object> parameters = new ArrayList<Object>();
//        parameters.add(((User) request.getSession().getAttribute("user")).getId());
        List<String> parametersKey = new ArrayList<>();
        parametersKey.add("id");
        parametersKey.add("year");
        parametersKey.add("month");
        parametersKey.add("approved");
        new QueryHelper(PreOvertime.class, "p", parametersKey)
                .addCondition("p.user.id = :id", ((User) request.getSession().getAttribute("user")).getId())
                .addCondition("p.year LIKE :year","%"+year+"%")
                .addCondition("p.month LIKE :month","%"+ month+"%")
                .addCondition(!approved.equals(""),"p.approved = :approved", approved.equals("1"))
                .preparePageBean(preOvertimeService, pageNum, model);
        model.addAttribute("approved",approved);
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        model.addAttribute("month", month);
        model.addAttribute("months", TimeHandler.getMonths());
//        PageBean pageBean = preOvertimeService.getPageBean(pageNum, 10, queryHelper.getListQueryHql(), queryHelper.getParameters());
        return "selfPreOvertimeViews/list";
    }
    @RequestMapping("/add")
    public String add(PreOvertime  preOvertime, HttpServletRequest request){
        preOvertime.setUserName(((User) request.getSession().getAttribute("user")).getName());
        preOvertime.setUser(((User) request.getSession().getAttribute("user")));
        preOvertime.setSubmitted(true);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        preOvertime.setCreatTime(df.format(new Date()));
        preOvertime.setUpdateTime(df.format(new Date()));
        preOvertime.setYear(TimeHandler.getYearByDate(preOvertime.getDate()));
        preOvertime.setMonth(TimeHandler.getMonthByDate(preOvertime.getDate()));
        preOvertimeService.save(preOvertime);
        return "redirect:/selfPreOvertime/list";
    }
    @RequestMapping("/addUI")
    public String addUI(Model model){
        PreOvertime preOvertime = new PreOvertime();
        preOvertime.setType(1);
        model.addAttribute("preOvertime", preOvertime);
        return "selfPreOvertimeViews/saveUI";
    }
    @RequestMapping("/edit")
    public String edit(PreOvertime preOvertime, HttpServletRequest request){
        PreOvertime preOvertimeInDatabase = preOvertimeService.getById(preOvertime.getId());
        preOvertimeInDatabase.setDate(preOvertime.getDate());
        preOvertimeInDatabase.setStartTime(preOvertime.getStartTime());
        preOvertimeInDatabase.setEndTime(preOvertime.getEndTime());
        preOvertimeInDatabase.setTimeSpan(preOvertime.getTimeSpan());
        preOvertimeInDatabase.setDescription(preOvertime.getDescription());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        preOvertimeInDatabase.setUpdateTime(df.format(new Date()));// new Date()为获取当前系统时间


        preOvertimeInDatabase.setRemark(preOvertime.getRemark());

        preOvertimeInDatabase.setSubmitted(true);

        preOvertimeInDatabase.setType(preOvertime.getType());

        preOvertimeInDatabase.setYear(TimeHandler.getYearByDate(preOvertime.getDate()));
        preOvertimeInDatabase.setMonth(TimeHandler.getMonthByDate(preOvertime.getDate()));
        preOvertimeService.update(preOvertimeInDatabase);
        return "redirect:/selfPreOvertime/list";
    }
    @RequestMapping("/editUI")
    public String editUI(Long id, Model model){
        PreOvertime preOvertime = preOvertimeService.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("preOvertime", preOvertime);
        return "selfPreOvertimeViews/saveUI";
    }
    @RequestMapping("/delete")
    public String delete(Long id){
        preOvertimeService.delete(id);
        return "redirect:/selfPreOvertime/list";
    }
}
