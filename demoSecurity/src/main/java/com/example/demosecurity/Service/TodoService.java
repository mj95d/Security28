package com.example.demosecurity.Service;

import com.example.demosecurity.ApiException.ApiException;
import com.example.demosecurity.Model.MyUser;
import com.example.demosecurity.Model.Todo;

import com.example.demosecurity.Repostoriy.AuthRepository;
import com.example.demosecurity.Repostoriy.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final AuthRepository authRepository;

    // الحصول على جميع المهام
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    // الحصول على جميع المهام التي تنتمي إلى مستخدم معين
    public List<Todo> getTodoByUserId(Integer userId) {
        MyUser myUser = authRepository.findMyUserById(userId);
        return todoRepository.findAllByMyUser(myUser);
    }

    // إضافة مهمة جديدة إلى قاعدة البيانات
    public void addTodo(Integer userId, Todo todo) {
        MyUser myUser = authRepository.findMyUserById(userId);
        todo.setMyUser(myUser);
        todoRepository.save(todo);
    }

    // تحديث مهمة موجودة في قاعدة البيانات
    public void updateTodo(Integer id, Todo newTodo, Integer authId) {
        Todo oldTodo = todoRepository.findTodoById(id);
        MyUser myUser = authRepository.findMyUserById(authId);

        // التأكد من أن المهمة المراد تحديثها موجودة
        if (oldTodo == null) {
            throw new ApiException("Todo not found");
        }
        // التأكد من أن المستخدم المراد تحديث المهمة الخاصة به
        if (oldTodo.getMyUser().getId() != authId) {
            throw new ApiException("Sorry, You do not have the authority to update this Todo!");
        }

        newTodo.setId(id);
        newTodo.setMyUser(myUser);

        todoRepository.save(newTodo);
    }

    // الحصول على مهمة معينة باستخدام معرف المهمة
    public Todo getTodoById(Integer id, Integer authId) {
        Todo todo = todoRepository.findTodoById(id);

        // التأكد من أن المهمة المراد الحصول عليها موجودة
        if (todo == null) {
            throw new ApiException("Todo not Found");
        }
        // التأكد من أن المستخدم المراد الحصول على المهمة الخاصة به
        if (todo.getMyUser().getId() != authId) {
            throw new ApiException("Sorry, You do not have the authority to get this Todo!");
        }

        return todo;
    }

    // حذف مهمة موجودة في قاعدة البيانات
    public void deleteTodoById(Integer userId, Integer todoId) {
        Todo todo = todoRepository.findTodoById(todoId);

        // التأكد من أن المستخدم المراد حذف المهمة الخاصة به
        if (todo.getMyUser().getId() != userId) {
            throw new ApiException("You don't have authority to delete this Todo!");
        }

        todoRepository.delete(todo);
    }
}