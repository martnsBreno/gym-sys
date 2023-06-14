package martns.gymsysproject.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import martns.gymsysproject.entity.Aluno;
import martns.gymsysproject.entity.Plano;
import martns.gymsysproject.exception.CpfJaCadastradoException;
import martns.gymsysproject.exception.PlanoNaoEncontradoException;
import martns.gymsysproject.exception.AlunoNaoEncontradoException;
import martns.gymsysproject.repository.AlunoRepository;
import martns.gymsysproject.repository.PlanoRepository;

@Service
public class AlunoService {

    private AlunoRepository alunoRepository;
    private PlanoRepository planoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, PlanoRepository planoRepository) {
        this.alunoRepository = alunoRepository;
        this.planoRepository = planoRepository;
    }

    public Aluno createaluno(String nome, String endereco, LocalDate dataUltimoPagamento,
            String cpf, Long plano_id) {

        Optional<Plano> plano = planoRepository.findById(plano_id);

        if (plano.isPresent()) {

            Plano planoDb = plano.get();
            int acessosAluno = planoDb.getAcessos();

            Aluno aluno = new Aluno(nome, endereco, dataUltimoPagamento, cpf, planoDb, acessosAluno);

            return alunoRepository.save(aluno);
        } else {
            throw new PlanoNaoEncontradoException("Plano nao encontrado na base de dados");
        }

    }

    public Optional<Aluno> findalunoByCpf(String cpf) {

        return alunoRepository.findByalunoCpf(cpf);

    }

    public String checkIfmembershipIsValid(Long id) {

        Optional<Aluno> alunoDb = alunoRepository.findByalunoId(id);

        if (alunoDb.isPresent()) {

            LocalDate alunoLastPaymentDate = alunoDb.get().getDataUltimoPagamento();
            LocalDate thisMoment = LocalDate.now();
            long monthsSinceLastPayment = alunoLastPaymentDate.until(thisMoment, ChronoUnit.MONTHS);

            return membershipValidation(monthsSinceLastPayment) ? "Matrícula Expirada!"
                    : "Matrícula Válida!";

        } else {
            throw new AlunoNaoEncontradoException("Nao foi possível encontrar um membro para o ID: " + id);
        }

    }

    private boolean membershipValidation(long monthsSinceLastPayment) {
        return monthsSinceLastPayment > 1;
    }

    public void deletealuno(Long alunoId) {

        alunoRepository.deleteById(alunoId);
    }
}
