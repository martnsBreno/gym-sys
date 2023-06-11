package martns.gymsysproject.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.PersistenceException;

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

    public Member createMember(String name, String address, boolean isMembershipPaid, LocalDate lastPaymentDate, String cpf) {
        Member member = new Member();

        member.setName(name);
        member.setAddress(address);
        member.setLastPaymentDate(lastPaymentDate);
        member.setMemberCpf(cpf);

        verificarMembroCadastrado(member);

        return memberRepository.save(member);
    }

    private void verificarMembroCadastrado(Member member) {

        Optional<Member> memberDb = memberRepository.findByMemberCpf(member.getMemberCpf());

        if(memberDb.isPresent()) throw new PersistenceException("CPF já cadastrado na base de dados!");

    }

    public Optional<Member> findMemberByCpf(String cpf) throws Exception {
        
        Optional<Member> member = memberRepository.findByMemberCpf(cpf);

        if(member.isPresent()) {
            return member;
        } else {
            throw new Exception("Membro nao encontrado para esse CPF");
        }
        
    }

    public void deleteMember(Long memberId) {

        memberRepository.deleteById(memberId);
    }
}
