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
    @ResponseBody
    public String insertTestDomain(){
        TestDomain td = new TestDomain();
        td.setCreate_user("test");
        td.setLast_update_user("test");
        testService.insertTestDomain(td);
        log.info("TestController");
        return "/test/new";
    }

}
