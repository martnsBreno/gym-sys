package martns.gymsysproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import martns.gymsysproject.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
    
    public Aluno findByNome(String name);

    public Optional<Aluno> findByalunoCpf(String cpf);

    public Optional<Aluno> findByalunoId(Long id);

}
