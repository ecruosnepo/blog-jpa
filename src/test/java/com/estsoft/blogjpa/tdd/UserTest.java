package com.estsoft.blogjpa.tdd;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;


public class UserTest {
  @Test
  public void testAddCoupon() {
    User user = new User("area00", "pw");
    assertEquals(0, user.getTotalCouponCount()); // 쿠폰수령전

    ICoupon coupon = Mockito.mock(ICoupon.class);
    Mockito.doReturn(true).when(coupon).isValid();
//    BDDMockito.given(coupon.isValid()).willReturn(true);        // isValid() 호출시 리턴값은 true

    user.addCoupon(coupon);
    assertEquals(1, user.getTotalCouponCount());
  }

  @DisplayName("쿠폰 발급 실패 (유효하지 않은 쿠폰일 경우)")
  @Test
  public void testNoAddCoupon() {
    User user = new User("area00", "pw");
    assertEquals(0, user.getTotalCouponCount());

    ICoupon coupon = Mockito.mock(ICoupon.class);
    Mockito.doReturn(false).when(coupon).isValid();

    user.addCoupon(coupon);
    assertEquals(0, user.getTotalCouponCount());
  }
}