package com.example.demosecurity;

import com.example.demosecurity.Model.MyUser;
import com.example.demosecurity.Model.Todo;
import com.example.demosecurity.Repostoriy.TodoRepository;
import com.example.demosecurity.Service.MyUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith( SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoTests {


    @Autowired
    TodoRepository todoRepository;

    @Autowired
    MyUserDetailsService myUserRepository;

    Todo todo1,todo2,todo3;
    MyUser myUser;

    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"Maha" , "12345" , "ADMIN" , null);
        todo1 = new Todo(null , "todo1", "body1" , myUser );
        todo2 = new Todo(null , "todo2", "body2" , myUser );
        todo3 = new Todo(null , "todo3", "body3" , myUser );
    }


    @Test
    public void findAllByMyUserTesting(){
        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);
        List<Todo> todos= todoRepository.findAllByMyUser(myUser);
        Assertions.assertThrows(todos.get(0).getMyUser().getId()).isEqualTo(myUser.getId());
    }

    @Test
    public void findTodoById(){
        todoRepository.save(todo1);
        Todo todo=todoRepository.findTodoById(todo1.getId());
        Assertions.assertThrows(todo).isEqualTo(todo1);
    }


}