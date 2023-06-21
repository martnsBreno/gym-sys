package martns.gymsysproject.rest;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import martns.gymsysproject.dto.AlunoDto;
import martns.gymsysproject.entity.Aluno;
import martns.gymsysproject.service.AlunoService;

@RestController
public class AlunoController {

    private AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping(path = "/createaluno")
    public ResponseEntity<String> createAluno(@Valid @RequestBody AlunoDto alunoDtoRequest) {

        alunoService.createaluno(alunoDtoRequest.getNome(), alunoDtoRequest.getEndereco(), LocalDate.now(), alunoDtoRequest.getAlunoCpf(), alunoDtoRequest.getPlano_id());

        return ResponseEntity.ok("Membro criado com sucesso!");
    }

    @PostMapping(path = "/deleteAlunoById")
    public ResponseEntity<String> deleteAlunoByid(Long id) {

        alunoService.deletealuno(id);

        return ResponseEntity.ok("Membro deletado com sucesso");

    }

    @GetMapping(path = "/findAlunoByCpf")
    public ResponseEntity<Aluno> findAlunoByCpf(String cpf) {

        Aluno alunoDb = alunoService.findAlunoByCpf(cpf);

        return ResponseEntity.ok(alunoDb);

    }

    @GetMapping(path = "/checkmembership")
    public ResponseEntity<String> checkMembership(Long id) {

        return ResponseEntity.ok(alunoService.checkIfmembershipIsValid(id));

    }

}
