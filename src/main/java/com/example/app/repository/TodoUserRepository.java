package com.example.app.repository;


import com.example.app.entity.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUser, Integer> {
    TodoUser getById(Integer id);
    TodoUser getByUsername(String userName);
    TodoUser save(TodoUser todoUser);
}
