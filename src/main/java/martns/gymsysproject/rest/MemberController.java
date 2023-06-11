package martns.gymsysproject.rest;

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

            memberService.createMember(name, address);

            return ResponseEntity.ok("Membro salvo com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao salvar um novo membro no banco de dados! " + e.getMessage());
        }
    }

    @GetMapping(path = "/findMemberWithName")
    public ResponseEntity<Member> findMemberByName(String name) {

        try {

            Member member = memberService.findMemberByName(name);

            return ResponseEntity.ok(member);

        } catch (Exception e) {
            throw new RuntimeException("Não foi possível encontrar o seguinte membro no banco de dados " + e.getMessage());
        }
    }

    @PostMapping(path = "/deleteMemberById")
    public ResponseEntity<String> deleteMemberById(Long memberId) {

            memberService.deleteMember(memberId);

            return ResponseEntity.ok("Membro deletado com sucesso");
    }
}
