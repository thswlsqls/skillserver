package com.ebson.skillserver.controller;

import com.ebson.skillserver.domain.TestUser;
import com.ebson.skillserver.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "test";
    }

    @GetMapping("/testUser/createTestUserForm")
    public String createTestUserForm(Model model) {
        model.addAttribute("testUser", new TestUser());
        return "/testUser/createTestUserForm";
    }

    @PostMapping("/testUser/create")
    public String create(TestUser testUser, BindingResult result){
        log.info(testUser.toString());
        // BindingResult는 폼의 값에 오류가 있을 경우에 그 값을 대신 갖고 실행함
        if(result.hasErrors()) {
            return "/testUser/createTestUserForm";
        }
        TestUser tu = new TestUser();
        tu.setCreate_user("system");
        tu.setLast_update_user("system");
        tu.setName(testUser.getName());

        testService.insertTestUser(tu);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String insertTestDomain(){
        TestUser tu = new TestUser();
        tu.setCreate_user("test");
        tu.setLast_update_user("test");

        // 랜덤문자열 생성법 참조 - https://vmpo.tistory.com/125
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        tu.setName(generatedString);
        testService.insertTestUser(tu);
        log.info("TestController");
        return "test";
    }

}
