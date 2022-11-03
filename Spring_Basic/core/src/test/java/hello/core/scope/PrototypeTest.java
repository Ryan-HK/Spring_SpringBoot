package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean singletonBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean singletonBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeBean1 = " + singletonBean1);
        System.out.println("PrototypeBean2 = " + singletonBean2);
        Assertions.assertThat(singletonBean1).isNotSameAs(singletonBean2);

        // Client가 직접 종료 시점의 메소드를 수행하여야 한다.
        singletonBean1.destroy();
        singletonBean2.destroy();
        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
