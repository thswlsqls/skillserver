package com.ebson.skillserver.controller;

import com.ebson.skillserver.domain.TestUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TestUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("/testUser/createTestUserForm 으로 get 요청한 결과 정상인지 테스트")
    public void createTestUserFormTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/testUser/createTestUserForm").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("/testUser/retrieveAllTestUsers 으로 get 요청한 결과 정상인지 테스트")
    public void retrieveAllTestUsersTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/testUser/retrieveAllTestUsers").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("/testUser/retrieveTestUserByUserIdForm 으로 get 요청한 결과 정상인지 테스트")
    public void retrieveTestUserByUserIdFormTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/testUser/retrieveTestUserByUserIdForm").accept(MediaType.TEXT_HTML))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("/testUser/createTestUser 으로 multipart 요청한 결과 정상인지 테스트")
    @Transactional
    public void createTestUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/testUser/createTestUser").param("name", UUID.randomUUID().toString()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    @DisplayName("/testUser/retrieveTestUserByUserId 으로 multipart 요청한 결과 정상인지 테스트")
    public void retrieveTestUserByUserId() throws Exception {
        String userId = "319a4e2d8d6d4769b58082d4ea721fe7";
        mockMvc.perform(MockMvcRequestBuilders.multipart("/testUser/retrieveTestUserByUserId").param("userId", userId))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
