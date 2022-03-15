package com.example.app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void statusIsOk() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk());
    }

    @Test
    public void containsTitle() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(content().string(containsString("Todo App")));
    }

    @Test
    public void containsRegistrationLabel() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(content().string(containsString("新規登録")));
    }

}
