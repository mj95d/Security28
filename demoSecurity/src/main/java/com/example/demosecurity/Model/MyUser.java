package com.example.demosecurity.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // اسم المستخدم
    private String username;

    // كلمة المرور
    private String password;

    // دور المستخدم (مثلاً: ADMIN، USER، GUEST)
    private String role;

    // قائمة بالمهام المرتبطة بالمستخدم
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "myUser")
    public Set<Todo> todoSet;

    // الصلاحيات التي يمتلكها المستخدم
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    // إذا كان حساب المستخدم غير منتهي الصلاحية
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // إذا كان حساب المستخدم غير مغلق
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // إذا كانت بيانات اعتماد المستخدم غير منتهية الصلاحية
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // إذا كان المستخدم ممكّنًا
    @Override
    public boolean isEnabled() {
        return true;
    }
}