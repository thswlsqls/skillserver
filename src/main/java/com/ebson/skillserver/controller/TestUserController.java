package com.ebson.skillserver.controller;

import com.ebson.skillserver.domain.TestUser;
import com.ebson.skillserver.service.TestUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/testUser")
public class TestUserController {

    @Autowired
    private final TestUserService testUserService;

    @GetMapping("/createTestUserForm")
    public String createTestUserForm(Model model) {
        model.addAttribute("testUser", new TestUser());
        return "/testUser/createTestUserForm";
    }

    @PostMapping("/createTestUser")
    public String createTestUser(TestUser testUser, BindingResult result){
        // BindingResult는 폼의 값에 오류가 있을 경우에 그 값을 대신 갖고 실행함
        if(result.hasErrors()) {
            return "/testUser/createTestUserForm";
        }
        TestUser tu = new TestUser();
        tu.setCreateUser("system");
        tu.setLastUpdateUser("system");
        tu.setName(testUser.getName());

        testUserService.createTestUser(tu);
        return "redirect:/testUser/retrieveAllTestUsers";
    }

    @GetMapping("/retrieveAllTestUsers")
    public String retrieveAllTestUsers(Model model){
        List<TestUser> testUserList = testUserService.retrieveAllTestUsers();
        model.addAttribute("testUserList", testUserList);
        return "/testUser/retrieveAllTestUsers";
    }

    @GetMapping("/retrieveTestUserByUserIdForm")
    public String retrieveTestUserByUserIdForm(Model model){
        return "/testUser/retrieveTestUserByUserIdForm";
    }

    @PostMapping("/retrieveTestUserByUserId")
    public String retrieveTestUserByUserId(String userId, Model model){
        TestUser retrivedTestUser = testUserService.findOne(userId);
        model.addAttribute("retrivedTestUser", retrivedTestUser);
        return "/testUser/retrieveTestUserByUserIdForm";
    }
}
