package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 고정 할인 금액
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // enum은 == 비교 연산자로 비교해야 한다.
            return  discountFixAmount;
        } else {
            return 0;
        }

    }
}
