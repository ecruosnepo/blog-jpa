package com.estsoft.blogjpa.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.estsoft.blogjpa.domain.User;
import com.estsoft.blogjpa.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  void testFindByEmail(){
    //given
    User user = new User("mock_email", "mock_pw");
    userRepository.save(user);

    //when
    User returnUser = userRepository.findByEmail("mock_email").orElseThrow();

    //then
    assertEquals(returnUser.getEmail(), user.getEmail());
    assertEquals(returnUser.getPassword(), user.getPassword());
  }

  @Test
  void testFindAll(){
    User user = new User("mock_email", "mock_pw");
    userRepository.save(user);

    List<User> all = userRepository.findAll();

    assertEquals(1, all.size());
  }

  @Test
  void testSaveUser(){
    // given
    User user = new User("mock_email", "mock_pw");

    // when
    User returnUser = userRepository.save(user);

    // then
    assertEquals(user.getEmail(), returnUser.getEmail());
    assertEquals(user.getPassword(), returnUser.getPassword());
  }

}
