package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StateFulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService statefulService1 = ac.getBean(StateFulService.class);
        StateFulService statefulService2 = ac.getBean(StateFulService.class);

//        ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
//        ThreadA: B사용자 20000원 주문
        int userBPrice = statefulService1.order("userB", 20000);

//        ThreadA : 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
        System.out.println("price = " + userAPrice);

//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }

}