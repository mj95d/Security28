package com.example.demosecurity.Repostoriy;

import com.example.demosecurity.Model.MyUser;
import com.example.demosecurity.Model.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.List;
@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    // العثور على جميع المهام التي يمتلكها مستخدم معين
    List<Todo> findAllByMyUser(MyUser myUser);

    // العثور على مهمة معينة باستخدام معرّفها
    Todo findTodoById(Integer id);
}