package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 자바의 OCP 원칙에 위배되는 코드. MemoryMemberRepository를 DbMemberRepository로 변경시
    // 직접 코드를 변경해주어야 한다. 이를 극복하기 위해 스프링을 사용한다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member); // Repository의 save 메소드를 호출해서 저장
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
        // Repository의 findById 메소드에 memberId값을 넘겨 Member 객체를 호출한다.
    }
}
