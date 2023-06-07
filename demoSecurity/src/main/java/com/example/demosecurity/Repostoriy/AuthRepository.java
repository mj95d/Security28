package com.example.demosecurity.Repostoriy;



import com.example.demosecurity.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthRepository extends JpaRepository<MyUser, Integer> {

    // العثور على مستخدم باستخدام اسم المستخدم
    MyUser findMyUserByUsername(String username);

    // العثور على مستخدم باستخدام معرّفه
    MyUser findMyUserById(Integer id);
}