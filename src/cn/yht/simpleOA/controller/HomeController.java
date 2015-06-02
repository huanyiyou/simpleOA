package cn.yht.simpleOA.controller;

import cn.yht.simpleOA.base.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by admin on 2015/5/15.
 */
@Controller
@Scope("prototype")
@RequestMapping("/home")
public class HomeController extends BaseAction {
    @RequestMapping("/index")
    public String index(){
        return "/homeViews/index";
    }
    @RequestMapping("/left")
    public String left(){
        return "/homeViews/left";
    }
    @RequestMapping("/right")
    public String right(){
        return "/homeViews/right";
    }
    @RequestMapping("/top")
    public String top(){
        return "/homeViews/top";
    }
    @RequestMapping("/bottom")
    public String bottom(){
        return "/homeViews/bottom";
    }

}
