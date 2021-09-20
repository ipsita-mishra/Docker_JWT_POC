package com.learning.zoro.concepts.Repository;

import com.learning.zoro.concepts.Domain.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LoginUser,Long> {
    LoginUser findByUserName(String name);
}
