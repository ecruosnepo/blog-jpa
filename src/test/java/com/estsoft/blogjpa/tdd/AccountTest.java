package com.estsoft.blogjpa.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTest {
  Account account;

  @BeforeEach
  void setUp() {
    account = new Account(10000);
  }

  @DisplayName("잔고 조회")
  @Test
  void testGetBalance(){
    assertEquals(account.getBalance(), 10000);

    account = new Account(100);
    assertEquals(account.getBalance(), 100);
  }

  @DisplayName("입금")
  @Test
  void testDeposit() {
    account.deposit(1000);
    assertEquals(account.getBalance(),11000);
  }

  @DisplayName("출금")
  @Test
  void testWithdraw() {
    account.withdraw(1000);
    assertThat(account.getBalance()).isEqualTo(9000);
  }
}
