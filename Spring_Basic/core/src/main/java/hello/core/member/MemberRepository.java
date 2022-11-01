package hello.core.member;

public interface MemberRepository {

    public abstract void save(Member member);

    public abstract Member findById(Long memberId);

} // end interface
