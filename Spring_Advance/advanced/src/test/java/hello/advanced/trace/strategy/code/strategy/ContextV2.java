package hello.advanced.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;


/**
 * 전략을 파라미터로 전달하는 방식
 * -. 템플릿 역할을 한다.
 * -. Strategy가 CallBack 부분으로 넘어온다.
 */
@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();

        //비즈니스 로직 실행
        strategy.call();
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
