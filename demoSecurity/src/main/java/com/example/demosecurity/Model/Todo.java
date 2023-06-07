package com.example.demosecurity.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Todo {

    // id معرّف المهمة المفتاح الرائيسي
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //  id نص الرسالة الخاصة بالمهمة
    private String message;

    // user المستخدم الذي يمتلك المهمة
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private MyUser myUser;
}