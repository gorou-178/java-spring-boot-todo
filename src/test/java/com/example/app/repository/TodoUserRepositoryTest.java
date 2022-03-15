package com.example.app.repository;

import com.example.app.entity.TodoUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TodoUserRepositoryTest {

    @Autowired
    private TodoUserRepository repository;

    @Test
    public void testExample() {
        TodoUser newUser = repository.save(new TodoUser("test", "pass"));

        TodoUser user = this.repository.getById(newUser.getId());
        assertThat(user.getUsername()).isEqualTo("test");
        assertThat(user.getId()).isEqualTo(newUser.getId());
    }

    @Test
    public void testGetByUsername() {
        TodoUser newUser = repository.save(new TodoUser("test1", "pass"));

        TodoUser user = this.repository.getByUsername("test1");
        assertThat(user.getUsername()).isEqualTo("test1");
        assertThat(user.getId()).isEqualTo(newUser.getId());
    }

}
