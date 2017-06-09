package pers.forms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.forms.utils.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/3.
 */
@Controller
public class PageController {
    private static final String key = "ljn~!@#$%^&*leon./042+";

    private static final String issue = "forms-server";

    @RequestMapping("/")
    public void indexPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    JWTUtil.VerifyResult verifyResult = JWTUtil.verify(key, issue, token);
                    if (verifyResult.isSuccess()) {
                        request.getRequestDispatcher("index.html").forward(request, response);
                        return;
                    } else {
                        System.out.println(verifyResult.getMessage());
                    }
                    break;
                }
            }
        }
        request.getRequestDispatcher("loginpage.html").forward(request, response);

    }
}
