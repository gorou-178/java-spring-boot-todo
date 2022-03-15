# TODO App

## Description
- Spring Boot Todo Application

## メモ
- / -> top page
- /login -> login page
- /logout -> logout page
  - redirect to /
- /{user_id}/todos/
  - ?filter=all or completed
  - ?sort=createdAt or updatedAt
  - ?order=asc or desc
- /{user_id}/todos/create
- /{user_id}/todos/{todo_id}/edit
- /{user_id}/todos/{todo_id}/update
- /{user_id}/todos/{todo_id}/completed
- /{user_id}/todos/{todo_id}/delete
