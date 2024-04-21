package com.estsoft.blogjpa.tdd;

import java.util.ArrayList;
import java.util.List;

public class User {

  private String id;

  private String pw;

  private List<ICoupon> coupons;

  public User(String id, String pw){
    this.id = id;
    this.pw = pw;
    coupons = new ArrayList<ICoupon>();
  }

  public int getTotalCouponCount(){
    return coupons.size();
  }

  public void addCoupon(ICoupon coupon) {
    if(coupon.isValid()){
      coupons.add(coupon);
    }
  }
}
