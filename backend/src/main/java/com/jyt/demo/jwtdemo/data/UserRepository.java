package com.jyt.demo.jwtdemo.data;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByUsername(String username);
}
