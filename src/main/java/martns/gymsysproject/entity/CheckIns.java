package martns.gymsysproject.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "checkins")
public class CheckIns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_checkin")
    private Long idCheckin;

    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

}
