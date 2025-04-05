package com.example.trainingzero;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    @Query("SELECT t FROM Trainer t WHERE " +
            "(:startDate is null or t.startDate >= :startDate) AND " +
            "(:endDate is null or t.endDate <= :endDate)")
    List<Trainer> findByCourseDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT t FROM Trainer t WHERE :skill MEMBER OF t.skills")
    List<Trainer> findBySkill(@Param("skill") String skill);

}