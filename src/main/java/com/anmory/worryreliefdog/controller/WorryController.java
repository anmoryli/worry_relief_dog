package com.anmory.worryreliefdog.controller;

import com.anmory.worryreliefdog.model.User;
import com.anmory.worryreliefdog.model.Worry;
import com.anmory.worryreliefdog.service.AdviceService;
import com.anmory.worryreliefdog.service.WorryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午2:09
 */

@Slf4j
@RestController
public class WorryController {
    @Autowired
    WorryService worryService;

    @Autowired
    AdviceService adviceService;
    @RequestMapping("/addWorry")
    public Worry addWorry(HttpServletRequest request,String content) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session_user_key");
        Worry worry = new Worry();
        worry.setUserId(user.getUserId());
        worry.setContent(content);

        int ret = worryService.addWorry(worry.getUserId(),worry.getContent());

//        根据worryId获取worry
        worry = worryService.getWorryByUserIdAndContent(user.getUserId(),worry.getContent());

        if(ret == 1) {
            session.setAttribute("session_worry_key",worry);
            log.info("worry:"  + worry);
            return worry;
        }
        return null;
    }

    @RequestMapping("/getWorryList")
    public List<Worry> getWorsList(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session_user_key");
        return worryService.getWorry(user.getUserId());
    }

    @RequestMapping("/resolveWorry")
    public int resolveWorry(HttpServletRequest request,String content) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session_user_key");
        Worry worry = worryService.getWorryByUserIdAndContent(user.getUserId(),content);
        int worryId = worry.getWorryId();
        int ret = worryService.resolveWorry(user.getUserId(),worryId);
        if(ret == 1) {

            return ret;
        }
        return -1;
    }

    @RequestMapping("/deleteWorry")
    public int deleteWorry(int worryId) {
        adviceService.deleteAdvice(worryId);
        return worryService.deleteWorry(worryId);
    }

    @RequestMapping("/getWorryListByFullName")
    public List<Worry> getWorryByFullName(String fullName) {
        log.info("getWorryListByFullName:" + fullName);
        return worryService.getWorryByFullName(fullName);
    }
}
