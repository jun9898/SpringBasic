package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        // bean의 이름으로도 조회가 가능하다.
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // 등록된 Bean이 MemberServiceImpl의 구현체기 때문에 테스트가 성공한다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType() {
        // bean의 타입으로도 조회가 가능하다.
        MemberService memberService = ac.getBean(MemberService.class);
        // 등록된 Bean이 MemberServiceImpl의 구현체기 때문에 테스트가 성공한다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        // 자바의 다형성으로 인해 구현체로도 조회가 가능하다.
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // 등록된 Bean이 MemberServiceImpl의 구현체기 때문에 테스트가 성공한다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){
        MemberService memberService = ac.getBean("xxxx", MemberService.class);
    }
}
