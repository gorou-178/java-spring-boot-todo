package com.example.app.controller;

import com.example.app.entity.Todo;
import com.example.app.entity.TodoUser;
import com.example.app.form.TodoForm;
import com.example.app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository repository;

    @GetMapping(path = "/")
    public String index(Model model) {
        TodoUser user = (TodoUser) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

        List<Todo> todos = repository.findByUserId(user.getId());
        model.addAttribute("userId", user.getId());
        model.addAttribute("userName", user.getUsername());
        model.addAttribute("todos", todos);

        String infoMessage = (String) model.getAttribute("info_message");
        model.addAttribute("infoMessage", infoMessage);

        return "todo/index";
    }

    @GetMapping("/edit/{todoId}")
    public String edit(
        @PathVariable("todoId") Integer todoId,
        Model model,
        TodoForm todoForm
    ) {
        Todo todo = repository.getById(todoId);
        model.addAttribute("todo", todo);
        return "todo/edit";
    }

    @PostMapping("/edit/{todoId}")
    public String update(
        @Valid TodoForm TodoForm,
        @PathVariable("todoId") Integer todoId,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "todo_edit";
        }
        Todo todo = repository.getById(todoId);
        todo.setTitle(TodoForm.getTitle());
        repository.save(todo);
        redirectAttributes.addFlashAttribute("info_message", "todoを更新しました(id: " + todo.getId() + ")");
        return "redirect:/todos/";
    }

    @GetMapping(path = "/create")
    public String create(TodoForm todoForm) {
        return "todo/create";
    }

    @PostMapping(path = "/create")
    public String save(
        @Valid TodoForm todoForm,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "projectCreateForm";
        }

        TodoUser user = (TodoUser) SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getPrincipal();

        Todo todo = new Todo();
        todo.setTitle(todoForm.getTitle());
        todo.setUserId(user.getId());
        Todo newTodo = repository.save(todo);

        redirectAttributes.addFlashAttribute("info_message", "todoを作成しました(id: " + newTodo.getId() + ")");
        return "redirect:/todos/";
    }
}
