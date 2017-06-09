package pers.forms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pers.forms.utils.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/7.
 */
@Controller
public class LoginController {
    private static final String key = "ljn~!@#$%^&*leon./042+";

    private static final String issue = "forms-server";
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(String email, String password, HttpServletRequest request, HttpServletResponse response)
                    throws IOException, ServletException{
        String token = JWTUtil.encode(key,issue, email, 900000);
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(900);
        response.addCookie(cookie);

        request.getRequestDispatcher("index.html").forward(request, response);

    }
}
