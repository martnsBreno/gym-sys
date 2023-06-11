package martns.gymsysproject.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import martns.gymsysproject.entity.Member;
import martns.gymsysproject.repository.MemberRepository;

@Service
public class MemberService {
    
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(String name, String address, boolean isMembershipPaid, LocalDate lastPaymentDate) {
        Member member = new Member();

        member.setName(name);
        member.setAddress(address);
        member.setMembershipPaid(isMembershipPaid);
        member.setLastPaymentDate(lastPaymentDate);

        return memberRepository.save(member);
    }

    public Member findMemberByName(String name) {

        return memberRepository.findByName(name);
    }

    public void deleteMember(Long memberId) {

        memberRepository.deleteById(memberId);
    }
}
