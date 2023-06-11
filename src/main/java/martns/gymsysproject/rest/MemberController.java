package martns.gymsysproject.rest;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import martns.gymsysproject.dto.MemberDto;
import martns.gymsysproject.entity.Member;
import martns.gymsysproject.repository.MemberRepository;
import martns.gymsysproject.service.MemberService;

@RestController
public class MemberController {

    private MemberService memberService;
    private MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @PostMapping(path = "/createMember")
    public ResponseEntity<String> createMember(@RequestBody MemberDto memberDtoRequest) {

        try {

            String name = memberDtoRequest.getName();
            String address = memberDtoRequest.getAddress();
            String cpf = memberDtoRequest.getMemberCpf();
            boolean isMembershipPaid = true;
            LocalDate lastPaymentDate = LocalDate.now();

            memberService.createMember(name, address, isMembershipPaid, lastPaymentDate, cpf);

            return ResponseEntity.ok("Membro salvo com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao salvar um novo membro no banco de dados! " + e.getMessage());
        }
    }

    @GetMapping(path = "/findMemberByCpf")
    public ResponseEntity<Optional<Member>> findMemberByName(String cpf) {

        try {

            Optional<Member> member = memberService.findMemberByCpf(cpf);

            return ResponseEntity.ok(member);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Não foi possível encontrar o seguinte membro no banco de dados " + e.getMessage());
        }
    }

    @PostMapping(path = "/deleteMemberById")
    public ResponseEntity<String> deleteMemberById(Long memberId) {

        try {
            memberService.deleteMember(memberId);

            return ResponseEntity.ok("Membro deletado com sucesso");
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao tentar deletar o membro com o Id recebido " + e.getMessage());

        }
    }
}
