package com.example.demosecurity.Service;

import com.example.demosecurity.Model.MyUser;

import com.example.demosecurity.Repostoriy.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepository;

    // تحميل بيانات المستخدم باستخدام اسم المستخدم
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = authRepository.findMyUserByUsername(username);

        // إذا لم يتم العثور على المستخدم، يتم رفع استثناء
        if (myUser == null) {
            throw new UsernameNotFoundException("Wrong username or password");
        }

        return myUser;
    }
}