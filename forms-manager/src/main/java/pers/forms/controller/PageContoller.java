package pers.forms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/6/3.
 */
@Controller
public class PageContoller {

    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }
}
