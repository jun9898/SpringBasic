package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        // 스프링 컨테이너에 등록된 bean에 대한 정보를 String 배열로 가져온다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // 해당 빈을 String 상태로 반복문 돌리기
        for (String beanDefinitionName : beanDefinitionNames) {
            // bean의 이름을 가지고 Spring에 등록된 Bean Object를 리턴한다.
            Object bean = ac.getBean(beanDefinitionName);
            // 해당 bean의 이름과 객체를 출력한다.
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            // 스프링 컨테이너에 등록된 모든 bean을 출력하는 것이 아닌 내가 직접 입력한 bean만 출력한다
            // 내가 입력한 bean만 출력하는 코드는 BeanDefinition.ROLE_APPLICATION
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
