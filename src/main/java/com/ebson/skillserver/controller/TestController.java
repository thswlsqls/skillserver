package com.ebson.skillserver.controller;

import com.ebson.skillserver.domain.TestDomain;
import com.ebson.skillserver.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    @Autowired
    private final TestService testService;

    @GetMapping("")
    public String Test() {
        log.info("TestController");
        return "test";
    }

    @GetMapping("/new")
    public String insertTestDomain(){
        TestDomain td = new TestDomain();
        td.setCreate_user("test");
        td.setLast_update_user("test");

        // 랜덤문자열 생성법 참조 - https://vmpo.tistory.com/125
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        td.setName(generatedString);
        testService.insertTestDomain(td);
        log.info("TestController");
        return "test";
    }

}
