package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import cn.yht.simpleOA.model.Breaktime;
import cn.yht.simpleOA.model.User;
import cn.yht.simpleOA.util.QueryHelper;
import cn.yht.simpleOA.util.TimeHandler;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YHT on 2015/5/29.
 */
@Controller
@Scope("prototype")
@RequestMapping("/selfBreaktime")
public class SelfBreaktimeController extends BaseAction {
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model,
                       Integer pageNum, String year, String month, Integer pageSize){
        if(null == pageSize){
            pageSize = 15;
        }
        if(null == pageNum){
            pageNum = 1;
        }
        if(null == year || "".equals(year)){
            year = "";
        }
        if(null == month || "".equals(month)){
            month = "";
        }
        List<String> parametersKey = new ArrayList<>();
        parametersKey.add("id");
        parametersKey.add("year");
        parametersKey.add("month");
        new QueryHelper(Breaktime.class, "b", parametersKey)
                .addCondition("b.user.id = :id", ((User) request.getSession().getAttribute("user")).getId())
                .addCondition("b.year LIKE :year", "%" + year + "%")
                .addCondition("b.month LIKE :month", "%" + month + "%")
                .addOrderProperty("b.date", false)
                .preparePageBean(overtimeService, pageNum, model, pageSize);
        model.addAttribute("year", year);
        model.addAttribute("years", TimeHandler.getYears());
        model.addAttribute("month", month);
        model.addAttribute("months", TimeHandler.getMonths());
        return "selfBreaktimeViews/list";
    }
}
