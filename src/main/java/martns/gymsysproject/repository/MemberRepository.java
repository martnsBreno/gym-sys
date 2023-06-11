package martns.gymsysproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import martns.gymsysproject.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
    
    public Member findByName(String name);

    public Optional<Member> findBymemberCpf(String cpf);
}
