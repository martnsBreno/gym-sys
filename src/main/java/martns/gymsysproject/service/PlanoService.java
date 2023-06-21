package martns.gymsysproject.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import martns.gymsysproject.entity.Plano;
import martns.gymsysproject.exception.PlanoNaoEncontradoException;
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

    public Plano findPlano(Long id) {

        Optional<Plano> plano = planoRepository.findById(id);

        if (plano.isEmpty()) {
            throw new PlanoNaoEncontradoException("Não foi possível encontrar esse plano");
        }

        System.out.println(plano);

        return plano.get();
    }

    public void deletePlano(Long id) {
        planoRepository.deleteById(id);
    }
}
