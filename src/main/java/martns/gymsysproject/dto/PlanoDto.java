package martns.gymsysproject.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class PlanoDto {
    
    private String nome;

    private String descricao;

    private BigDecimal valor;

    private int acessos;
}
