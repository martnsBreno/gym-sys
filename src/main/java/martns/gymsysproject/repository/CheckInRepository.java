package martns.gymsysproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import martns.gymsysproject.entity.CheckIns;

public interface CheckInRepository extends JpaRepository<CheckIns, Long>{
    
}
