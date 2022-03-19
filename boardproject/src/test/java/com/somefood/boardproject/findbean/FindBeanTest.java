package com.somefood.boardproject.findbean;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootTest
public class FindBeanTest {

    @Test
    @DisplayName("내가 등록한 빈 보기")
    void findBean() throws Exception {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext();

        // when
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }

        // then
    }
}
