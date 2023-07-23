package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDistcountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    /*
    번외로
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;
    이런 방식으로 필드에 직접 의존성을 주입해주는 방법도 있지만 테스트 진행시
    객체의 값을 유동적으로 변경할 수 없어 결국 setter를 설정해줘야 한다.
    그럴빠엔 생성자로 객체 설정값 넣어주는게 낫다.
    결론 : 사용하지 말자!
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // setter를 사용해서 스프링 빈에 선택적으로 의존관계 등록 가능

//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
