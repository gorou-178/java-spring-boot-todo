package com.example.app.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TodoForm {

    @NotNull
    @Size(min = 2, max = 128)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
