package martns.gymsysproject.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import martns.gymsysproject.entity.Plano;
import martns.gymsysproject.repository.PlanoRepository;

@Service
public class PlanoService {
    
    private PlanoRepository planoRepository;

    @Autowired
    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public Plano createPlano(String nome, String descricao, BigDecimal valor, int acessos) {
        Plano plano= new Plano();

        plano.setNome(nome);
        plano.setDescricao(descricao);
        plano.setValor(valor);
        plano.setAcessos(acessos);

        return planoRepository.save(plano);
    }

    public Optional<Plano> findPlano(Long id) {

        System.out.println("AQUI1");

        Optional<Plano> plano = planoRepository.findById(id);

        System.out.println(plano);

        return plano;
    }

    public void deletePlano(Long id) {
        planoRepository.deleteById(id);
    }
}
