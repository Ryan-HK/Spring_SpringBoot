package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } // Constructor

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 1. 회원조회
        Member member = memberRepository.findById(memberId);
        // 2. 할인 정책 반영
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 3. 주문서 반환 (결과 값)
        return new Order(memberId, itemName, itemPrice, discountPrice);
    } // createOrder

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }

} // end class
