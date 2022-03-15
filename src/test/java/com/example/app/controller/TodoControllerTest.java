package com.example.app.controller;

import com.example.app.annotation.WithMockTodoUser;
import com.example.app.entity.Todo;
import com.example.app.entity.TodoUser;
import com.example.app.repository.TodoRepository;
import com.example.app.repository.TodoUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional(rollbackForClassName={"Exception"})
public class TodoControllerTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoUserRepository todoUserRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockTodoUser(username = "test")
    public void statusIsOk() throws Exception {
        mockMvc.perform(get("/todos/"))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockTodoUser(username = "test")
    public void showMyTodoOne() throws Exception {
        TodoUser user = todoUserRepository.getByUsername("test");

        Todo todo = new Todo();
        todo.setTitle("testTodoTitle");
        todo.setUserId(user.getId());
        todoRepository.save(todo);

        mockMvc.perform(get("/todos/"))
            .andExpect(MockMvcResultMatchers.content().string(containsString("testTodoTitle")));
    }

    @Test
    @WithMockTodoUser(username = "test")
    public void showMyTodoMany() throws Exception {
        TodoUser user = todoUserRepository.getByUsername("test");

        Todo todo1 = new Todo();
        todo1.setTitle("testTodoTitle1");
        todo1.setUserId(user.getId());
        todoRepository.save(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("testTodoTitle2");
        todo2.setUserId(user.getId());
        todoRepository.save(todo2);

        mockMvc.perform(get("/todos/"))
            .andExpect(MockMvcResultMatchers.content().string(containsString("testTodoTitle1")))
            .andExpect(MockMvcResultMatchers.content().string(containsString("testTodoTitle2")));
    }

    @Test
    public void anonymousToRedirection() throws Exception {
        mockMvc.perform(get("/todos/"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrlPattern("**/"));
    }

}
