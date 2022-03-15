package com.example.app.repository;

import com.example.app.entity.Todo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Integer> {
    Todo getById(Integer id);
    List<Todo> findByUserId(Integer userId);
}
