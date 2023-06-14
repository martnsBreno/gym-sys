package martns.gymsysproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AlunoDto {

    @NotBlank(message = "O campo nome nao pode ficar vazio")
    private String nome;

    @NotBlank(message = "O campo endereco nao pode ficar vazio")
    private String endereco;

    @NotBlank(message = "O campo CPF nao pode ficar vazio")
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas dígitos")
    private String alunoCpf;

    private Long plano_id;

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getAlunoCpf() {
        return alunoCpf;
    }

    public Long getPlano_id() {
        return plano_id;
    }


}
