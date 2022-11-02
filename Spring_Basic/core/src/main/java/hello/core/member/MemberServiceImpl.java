package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // Constructor

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    } // join

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    } // findMember

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }

} // end class
