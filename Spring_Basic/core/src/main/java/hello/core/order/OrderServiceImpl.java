package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDisCountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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
