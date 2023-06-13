package martns.gymsysproject.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import martns.gymsysproject.entity.Member;
import martns.gymsysproject.exception.CpfJaCadastradoException;
import martns.gymsysproject.exception.MembroNaoEncontradoException;
import martns.gymsysproject.repository.MemberRepository;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(String name, String address, LocalDate lastPaymentDate,
            String cpf) {
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

        if (memberDb.isPresent())
            throw new CpfJaCadastradoException("CPF já cadastrado na base de dados!" + memberDb.get().getMemberCpf());
    }

    public Optional<Member> findMemberByCpf(String cpf) {

        return memberRepository.findByMemberCpf(cpf);

    }

    public String checkIfMembershipIsValid(Long id) {

        Optional<Member> memberDb = memberRepository.findByMemberId(id);

        if (memberDb.isPresent()) {

            LocalDate memberLastPaymentDate = memberDb.get().getLastPaymentDate();
            LocalDate thisMoment = LocalDate.now();
            long monthsSinceLastPayment = memberLastPaymentDate.until(thisMoment, ChronoUnit.MONTHS);

            return membershipValidation(monthsSinceLastPayment) ? "Matrícula Expirada!"
                    : "Matrícula Válida!";

        } else {
            throw new MembroNaoEncontradoException("Nao foi possível encontrar um membro para o ID: " + id);
        }

    }

    private boolean membershipValidation(long monthsSinceLastPayment) {
        return monthsSinceLastPayment > 1;
    }

    public void deleteMember(Long memberId) {

        memberRepository.deleteById(memberId);
    }
}
