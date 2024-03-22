package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.domain.User;
import com.estsoft.blogjpa.dto.AddUserRequest;
import com.estsoft.blogjpa.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach
    public void cleanUp(){
        userRepository.deleteAll();
    }

    @Test
    void signup() throws Exception {
        //given - 회원 가입에 필요한 정보 설정
        AddUserRequest request = new AddUserRequest("mock_email", "mock_pw");

        //when - 회원 정보 저장 동작
        ResultActions response = mockMvc.perform(post("/user")
                    .param("email","mock_email")
                    .param("password","mock_pw"));
        //then - 회원 정보 저장 후 결과
        response.andExpect(status().is3xxRedirection());

        User byEmail = userRepository.findByEmail(request.getEmail()).orElseThrow();
        Assertions.assertNotNull(byEmail);
    }

    @Test
    void logout() {
    }
}