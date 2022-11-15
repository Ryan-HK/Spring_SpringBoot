package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();   //static 사용
    private static long sequence = 0L;  //static 사용

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save : member = {}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findId(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        List<Member> all = findAll();

//        for (Member m : all) {
//            if (m.getLoginId().equals(loginId)) {
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();

        // Lambda와 Stream을 이용한, 코드 리팩토링
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll() {
        //map객체에서 values()메소드를 이용하면, value값만 Collection 객체로 불러올 수 있다.
        return new ArrayList<>(store.values());
    }

}
