package com.estsoft.blogjpa.repository;

import com.estsoft.blogjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 매개변수로 받은 email과 일치하는 user 정보를 찾아옴
    Optional<User> findByEmail(String email);
}
