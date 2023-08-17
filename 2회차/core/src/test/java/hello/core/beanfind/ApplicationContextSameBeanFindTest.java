package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanTypeDuplicate(){
//        MemberRepository bean = ac.getBean(MemberRepository.class);
        // 타입만 지정했음으로 sameBeanConfig에 등록된 2개의 Bean이 충돌해 예외가 터질수밖에 없다.
        // No qualifying bean of type 'hello.core.member.MemberRepository' available: expected single matching bean but found 2: memberRepository1,memberRepository2
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    void findBeanByName() {
        // getBean 단계에서 빈의 이름을 매개변수로 전달하면 된다.
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기.")
    void findAllBeanByType() {
        // MemberRepository의 타입을 가지고 있는 bean의 개수가 2개라서 Map 형태로 리턴하게 된다.
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        // for문으로 해당 타입을 모두 조회해보자.
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }


    // 테스트를 위한 빈을 등록해주는 Configuration
    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }
}
