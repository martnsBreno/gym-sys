package martns.gymsysproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import martns.gymsysproject.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
    
    public Member findByName(String name);
}
