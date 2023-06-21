package martns.gymsysproject.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import martns.gymsysproject.entity.Aluno;
import martns.gymsysproject.entity.CheckIns;
import martns.gymsysproject.repository.AlunoRepository;
import martns.gymsysproject.repository.CheckInRepository;

@Service
public class CheckInService {

    private AlunoService alunoService;
    private AlunoRepository alunoRepository;
    private CheckInRepository checkInRepository;

    @Autowired
    public CheckInService(AlunoService alunoService, AlunoRepository alunoRepository, CheckInRepository checkInRepository) {
        this.alunoService = alunoService;
        this.alunoRepository = alunoRepository;
        this.checkInRepository = checkInRepository;
    }

    public CheckIns realizarCheckIn(String alunoCpf) {

        Aluno alunoDb = alunoService.findAlunoByCpf(alunoCpf);

        alunoDb.setAcessos(alunoDb.getAcessos() - 1);

        CheckIns checkIn = new CheckIns();

        checkIn.setAluno(alunoDb);
        checkIn.setCheckInTime(LocalDateTime.now());

        CheckIns checkInMade = checkInRepository.save(checkIn);
        alunoRepository.save(alunoDb);

        return checkInMade;

    }

    public void deletarCheckIn(Long checkInId) {
        checkInRepository.deleteById(checkInId);
    }
}
