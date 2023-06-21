package martns.gymsysproject.rest;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import martns.gymsysproject.entity.CheckIns;
import martns.gymsysproject.service.CheckInService;

@RestController
public class CheckInController {
    
    private CheckInService checkInService;

    @Autowired
    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping(path = "/realizarCheckIn")
    @Transactional
    public ResponseEntity<String> realizarCheckInAluno(String alunoCpf) {

        CheckIns checkIn = checkInService.realizarCheckIn(alunoCpf);

        return ResponseEntity.ok("Check in para o CPF: " + alunoCpf + " realizado com sucesso!" + checkIn.getCheckInTime());
    }

    @PostMapping(path = "/deletarCheckIn")
    public ResponseEntity<String> deletarCheckIn(Long checkInId) {

        checkInService.deletarCheckIn(checkInId);

        return ResponseEntity.ok("Check in deletado com sucesso da base de dados");
    }
}
