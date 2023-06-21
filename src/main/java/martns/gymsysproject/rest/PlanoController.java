package martns.gymsysproject.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import martns.gymsysproject.dto.PlanoDto;
import martns.gymsysproject.entity.Plano;
import martns.gymsysproject.service.PlanoService;

@RestController
public class PlanoController {
    
    PlanoService planoService;

    @Autowired
    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @PostMapping(path = "/createPlano")
    public ResponseEntity<String> criarPlano(@RequestBody PlanoDto planoDto) {

        planoService.createPlano(planoDto.getNome(), planoDto.getDescricao(), planoDto.getValor(), planoDto.getAcessos());

        return ResponseEntity.ok("Plano criado com sucesso!");
    }

    @PostMapping(path = "/deletePlano")
    public void deletePlano(Long id) {
        planoService.deletePlano(id);
    }

    @GetMapping(path = "/findPlano")
    public ResponseEntity<Plano> findPlano(Long id) {

        Plano planoDb = planoService.findPlano(id);
        
        return ResponseEntity.ok(planoDb);
    }
}
