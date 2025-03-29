package com.anmory.worryreliefdog.controller;

import com.anmory.worryreliefdog.model.Advice;
import com.anmory.worryreliefdog.model.User;
import com.anmory.worryreliefdog.model.Worry;
import com.anmory.worryreliefdog.service.AdviceService;
import com.anmory.worryreliefdog.service.GetAdviceFromLLMService;
import com.anmory.worryreliefdog.service.WorryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Anmory/李梦杰
 * @description TODO
 * @date 2025-03-29 下午2:22
 */

@Slf4j
@RestController
public class AdviceController {
    @Autowired
    AdviceService adviceService;
    @Autowired
    WorryService worryService;

    @Autowired
    GetAdviceFromLLMService getAdviceFromLLMService;

    public String getAdvice(String content) throws IOException {
        return getAdviceFromLLMService.chat(content);
    }

    @RequestMapping("/addAdvice")
    public int addAdvice(HttpServletRequest request, String content) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session_user_key");
        Worry worry = worryService.getWorryByUserIdAndContent(user.getUserId(),content);
        String advice  = getAdvice(worry.getContent());
        int worryId = worry.getWorryId();
        adviceService.updateWorry(worryId, advice);
        return adviceService.addAdvice(user.getUserId(), worryId, advice);
    }

//    @RequestMapping("/getAdvice")
//    public String getAdvice(HttpServletRequest request,String content) {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("session_user_key");
//    }

    @RequestMapping("/getAdviceRandomly")
    public Advice getAdviceRandomly(HttpServletRequest request, String content) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("session_user_key");
        Worry worry = worryService.getWorryByUserIdAndContent(user.getUserId(),content);
        int worryId = worry.getWorryId();
        log.info("worryId:" + worryId);
        Advice advice = adviceService.getAdviceRandomly(worryId);
        log.info("advice:" + advice);
        return  advice;
    }
}
