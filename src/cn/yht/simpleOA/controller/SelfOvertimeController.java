package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.Overtime;
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
@RequestMapping("/selfOvertime")
public class SelfOvertimeController extends BaseAction {
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model,
                       Integer pageNum, String year, String month){
        if(pageNum == null){
            pageNum = 1;
        }
        if(year == null){
            year = "";
        }
        if(month == null){
            month = "";
        }

        List<String> parametersKey = new ArrayList<>();
        parametersKey.add("id");
        parametersKey.add("year");
        parametersKey.add("month");
        new QueryHelper(Overtime.class, "o", parametersKey)
                .addCondition("o.user.id = :id", ((User) request.getSession().getAttribute("user")).getId())
                .addCondition("o.year LIKE :year", "%" + year + "%")
                .addCondition("o.month LIKE :month","%"+ month+"%")
                .preparePageBean(overtimeService, pageNum, model);
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        model.addAttribute("month", month);
        model.addAttribute("months", TimeHandler.getMonths());
        return "selfOvertimeViews/list";
    }


}
