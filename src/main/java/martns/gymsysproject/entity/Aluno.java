package martns.gymsysproject.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    @JsonManagedReference
    @OneToMany(mappedBy = "aluno")
    private List<CheckIns> checkIns = new ArrayList<>();

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
