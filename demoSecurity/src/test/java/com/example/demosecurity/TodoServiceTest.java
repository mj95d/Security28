package com.example.demosecurity;

import com.example.demosecurity.Model.MyUser;
import com.example.demosecurity.Model.Todo;
import com.example.demosecurity.Repostoriy.AuthRepository;
import com.example.demosecurity.Repostoriy.TodoRepository;
import com.example.demosecurity.Service.MyUserDetailsService;
import com.example.demosecurity.Service.TodoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith( MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    TodoService todoService;
    @Mock
    Todo todoRepository;
    @Mock
    AuthRepository authRepository;

    MyUser user;

    Todo todo1,todo2,todo3;

    List<Todo> todos;

    @BeforeEach
    void setUp() {
        user=new MyUser(null,"majd","123","Admin", null);
        todo1=new Todo(null,"todo1",user);
        todo2=new Todo(null,"todo2",user);
        todo3=new Todo(null,"todo3",null);

        todos=new ArrayList<>();
        todos.add(todo1);
        todos.add(todo2);
        todos.add(todo3);
    }


    @Test
    public void getAllTodoTest(){

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
        Assertions.assertThat(todos.get(0).getMyUser().getId()).isEqualTo(myUser.getId());
    }

    @Test
    public void findTodoById(){
        todoRepository.save(todo1);
        Todo todo=todoRepository.findTodoById(todo1.getId());
        Assertions.assertThat(todo).isEqualTo(todo1);
    }


}(todoRepository.findAll()).thenReturn(todos);
        List<Todo> todoList=todoService.getTodos();
        Assertions.assertEquals(3,todoList.size());
        verify(todoRepository,times(1)).findAll();

    }
    @Test
    public void getTodoByIdTest(){
        when(authRepository.findMyUserById(user.getId())).thenReturn(user);
        when(todoRepository.findAllByMyUser(user)).thenReturn(todos);


        List<Todo> todo = todoService.getTodo(user.getId());
        Assertions.assertEquals(todo,todos);
        verify(authRepository,times(1)).findMyUserById(user.getId());
        verify(todoRepository,times(1)).findAllByMyUser(user);

    }

    @Test
    public void AddTodoTest(){

        when(authRepository.findMyUserById(user.getId())).thenReturn(user);

        todoService.addTodo(user.getId(),todo3);
        verify(authRepository,times(1)).findMyUserById(user.getId());
        verify(todoRepository,times(1)).save(todo3);
    }

    @Test
    public void updateTodoTest(){

        when(todoRepository.findTodoById(todo1.getId())).thenReturn(todo1);
        when(authRepository.findMyUserById(user.getId())).thenReturn(user);

        todoService.updateTodo(todo1.getId(),todo2,user.getId());

        verify(todoRepository,times(1)).findTodoById(todo1.getId());
        verify(authRepository,times(1)).findMyUserById(user.getId());
        verify(todoRepository,times(1)).save(todo1);

    }







}