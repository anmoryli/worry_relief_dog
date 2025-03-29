package com.anmory.worryreliefdog.controller;

import com.anmory.worryreliefdog.model.User;
import com.anmory.worryreliefdog.service.FullNameService;
import com.anmory.worryreliefdog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午1:21
 */

@Slf4j
//@RequestMapping("/ user")
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    FullNameService fullNameService;
    @RequestMapping("/login")
    @ResponseBody
    public User login(String realName, String username, String password, HttpSession session) {
        log.info("[UserLogin]" + realName + username + password);
        if(!StringUtils.hasLength(username)) {
            log.error("用户名为空");
            return null;
        }
        User user = userService.getUserByName(username);
        fullNameService.addFullName(user.getUserId(),realName);
        if(user == null) {
            log.error("用户不存在");
            return null;
        }
        if(password.equals(user.getPassword())) {
            session.setAttribute("session_user_key",user);
            log.info("登录成功" + user);
            return user;
        }
        return null;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session_user_key");
        return user;
    }

    @RequestMapping("/register")
    @ResponseBody
    public int register(String username, String password, String email) {
        log.info("[UserRegister]" + username + password + email);
        User user = userService.getUserByName(username);
        if(user != null) {
            log.error("用户已注册");
            return -1;
        }
        log.info("注册成功" + user);
        return userService.addUser(username,password,email);
    }

    @RequestMapping("changePassword")
    @ResponseBody
    public int changePassword(HttpServletRequest request,String username, String newPassword, String newEmail) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session_user_key");
        log.info("[UserChangePassword]" + user);
        return userService.changePassword(username,newPassword,newEmail);
    }
}
