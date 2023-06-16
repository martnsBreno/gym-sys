package martns.gymsysproject.rest;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import martns.gymsysproject.entity.Aluno;
import martns.gymsysproject.entity.CheckIns;
import martns.gymsysproject.repository.AlunoRepository;
import martns.gymsysproject.repository.CheckInRepository;
import martns.gymsysproject.service.AlunoService;

@RestController
public class CheckInController {
    
    private CheckInRepository checkInRepository;
    private AlunoRepository alunoRepository;
    private AlunoService alunoService;

    @Autowired
    public CheckInController(CheckInRepository checkInRepository, AlunoService alunoService, AlunoRepository alunoRepository) {
        this.checkInRepository = checkInRepository;
        this.alunoService = alunoService;
        this.alunoRepository = alunoRepository;
    }

    @PostMapping(path = "/realizarCheckIn")
    @Transactional
    public void realizarCheckInAluno(String alunoCpf) {

        Aluno alunoDb = alunoService.findAlunoByCpf(alunoCpf);

        alunoDb.setAcessos(alunoDb.getAcessos() - 1);

        CheckIns checkIns = new CheckIns();

        checkIns.setAluno(alunoDb);
        checkIns.setCheckInTime(LocalDateTime.now());

        checkInRepository.save(checkIns);
        alunoRepository.save(alunoDb);
    }

    @PostMapping(path = "/deletarCheckIn")
    public void deletarCheckIn(Long checkInId) {

        checkInRepository.deleteById(checkInId);
    }
}
