package hello.advanced.trace;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TraceStatus {

    private TraceId traceId;            //내부에 트랜잭션ID와 level
    private long startTimeMs;           //로그 시작시간
    private String message;             //시작 시, 사용한 메시지 (로그 종료 시, 해당 메시지 사용)

}
