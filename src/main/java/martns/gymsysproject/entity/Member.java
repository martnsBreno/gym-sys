package martns.gymsysproject.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name")
    @NotBlank(message = "O campo nome nao pode ficar vazio")
    private String name;

    @Column(name = "address")
    @NotBlank(message = "O campo endereco nao pode ficar vazio")
    private String address;

    @Column(name = "last_payment_date")
    private LocalDate lastPaymentDate;

    @Column(name = "cpf")
    @NotBlank(message = "O campo CPF nao pode ficar vazio")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas dígitos")
    private String memberCpf;
}
