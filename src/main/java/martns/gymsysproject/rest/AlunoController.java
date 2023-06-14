package martns.gymsysproject.rest;

import java.time.LocalDate;
import java.util.Optional;

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

        String name = alunoDtoRequest.getNome();
        String address = alunoDtoRequest.getEndereco();
        String cpf = alunoDtoRequest.getAlunoCpf();
        LocalDate lastPaymentDate = LocalDate.now();
        Long plano_id = alunoDtoRequest.getPlano_id();

        alunoService.createaluno(name, address, lastPaymentDate, cpf, plano_id);

        return ResponseEntity.ok("Membro criado com sucesso!");
    }

    @GetMapping(path = "/findAlunoByCpf")
    public ResponseEntity<String> findAlunoByCpf(String cpf) {

        Optional<Aluno> aluno = alunoService.findalunoByCpf(cpf);

        Aluno alunoDb = aluno.get();

        String resposta = "  Aluno encontrado: " + alunoDb.getNome() + "\n Endereco: " + alunoDb.getEndereco()
                + " \n Data do último pagamento: " + alunoDb.getDataUltimoPagamento() + "\n Detalhes do plano: "
                + alunoDb.getPlano().getNome() + " " + alunoDb.getPlano().getDescricao() + "\n Acessos: "
                + alunoDb.getPlano().getAcessos() + "\n Mensalidade: " + alunoDb.getPlano().getValor();

        return ResponseEntity.ok(resposta);

    }

    @GetMapping(path = "/checkmembership")
    public ResponseEntity<String> checkMembership(Long id) {

        return ResponseEntity.ok(alunoService.checkIfmembershipIsValid(id));

    }

    @PostMapping(path = "/deleteAlunoById")
    public ResponseEntity<String> deleteAlunoByid(Long id) {

        alunoService.deletealuno(id);

        return ResponseEntity.ok("Membro deletado com sucesso");

    }
}
