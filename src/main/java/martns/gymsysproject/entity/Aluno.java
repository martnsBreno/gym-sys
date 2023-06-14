package martns.gymsysproject.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Long alunoId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "data_ultimo_pagamento")
    private LocalDate dataUltimoPagamento;

    @Column(name = "cpf")
    private String alunoCpf;

    @OneToOne
    @JoinColumn(name = "id_plano")
    Plano plano;

    @Column(name = "acessos")
    private int acessos;

    public Aluno(String nome, String endereco, LocalDate dataUltimoPagamento, String alunoCpf, Plano plano,
            int acessos) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataUltimoPagamento = dataUltimoPagamento;
        this.alunoCpf = alunoCpf;
        this.plano = plano;
        this.acessos = acessos;
    }

    
    
}
