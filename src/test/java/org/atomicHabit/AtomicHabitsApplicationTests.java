package org.atomicHabit;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AtomicHabitsApplicationTests {


    @Test
    void itShouldAddNumbers(){

        //given
        int numberOne = 20;
        int numberTwo = 30;
        //when
        int res=numberTwo+numberOne;
        //then
        assertThat(50).isEqualTo(res);
    }
    @Test
    void contextLoads(){
        System.out.println(123);
    }
}