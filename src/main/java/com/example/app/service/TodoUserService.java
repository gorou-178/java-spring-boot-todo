package com.example.app.service;

import com.example.app.entity.TodoUser;
import com.example.app.form.UserForm;
import com.example.app.repository.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TodoUserService implements UserDetailsService {

    @Autowired
    private TodoUserRepository todoUserRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TodoUser todoUser = todoUserRepository.getByUsername(username);
        if (todoUser == null) {
            throw new UsernameNotFoundException(username + " was not found");
        }

        return todoUser;
    }

    public UserDetails create(UserForm userForm) {
        TodoUser todoUser = new TodoUser();
        todoUser.setPassword(passwordEncoder.encode(userForm.getPassword()));
        todoUser.setUsername(userForm.getUsername());
        todoUserRepository.save(todoUser);
        return todoUser;
    }

    public TodoUser build(String userName, String password) {
        TodoUser todoUser = new TodoUser();
        todoUser.setPassword(passwordEncoder.encode(password));
        todoUser.setUsername(userName);
        todoUserRepository.save(todoUser);
        return todoUser;
    }
}
