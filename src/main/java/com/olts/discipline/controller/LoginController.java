package com.olts.discipline.controller;

import com.olts.discipline.api.dao.UserDao;
import com.olts.discipline.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * o.tsoy
 * 26.04.2017
 */
@Controller
public class LoginController {

    @Resource(name = "userDao")
    private UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView displayLogin() {
        ModelAndView model = new ModelAndView("login/login");
        User user = new User();
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView executeLogin(@ModelAttribute("user") User user) {
        ModelAndView model;
        if (userDao.get(user.getLogin()) != null) {
            System.out.println("User Login Successful");
            //todo request.setAttribute("loggedInUser", loginBean.getUsername());
            model = new ModelAndView("index");
        } else {
            model = new ModelAndView("login/login");
            model.addObject("user", user);
            //todo request.setAttribute("message", "Invalid credentials!!");
        }
        return model;
    }

}