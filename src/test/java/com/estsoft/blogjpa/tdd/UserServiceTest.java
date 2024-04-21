package com.estsoft.blogjpa.tdd;

import com.estsoft.blogjpa.domain.User;
import com.estsoft.blogjpa.dto.AddUserRequest;
import com.estsoft.blogjpa.repository.UserRepository;
import com.estsoft.blogjpa.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  UserRepository userRepository; // 가짜 객체, stub 처리

  @Spy
  BCryptPasswordEncoder bCryptPasswordEncoder; // 가짜 객체, encoder, encode()

  @InjectMocks
  UserService userService;

  @Test
  void  testSave(){
    //given
    AddUserRequest request = new AddUserRequest("mock_email@1", "pw");
    String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
    doReturn(new User(request.getEmail(), encodedPassword))
        .when(userRepository).save(any(User.class)); // stub

    //when
    User returnUser = userService.save(request);

    //then
    assertThat(returnUser.getEmail()).isEqualTo(request.getEmail());
    assertThat(returnUser.getPassword()).isEqualTo(encodedPassword);

    verify(bCryptPasswordEncoder, times(2)).encode(any(String.class));
    verify(userRepository, times(1)).save(any(User.class));

  }

}
