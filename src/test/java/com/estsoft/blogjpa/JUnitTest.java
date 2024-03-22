package com.estsoft.blogjpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JUnitTest {
    @DisplayName("1+2는 귀요미")
    @Test
    public void test(){
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum,a+b);
        assertThat(a+b).isEqualTo(sum);
    }
}
