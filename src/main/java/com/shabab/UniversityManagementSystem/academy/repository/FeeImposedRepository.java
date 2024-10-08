package com.shabab.UniversityManagementSystem.academy.repository;

import com.shabab.UniversityManagementSystem.academy.model.FeeImposed;
import com.shabab.UniversityManagementSystem.academy.model.FeeImposed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 24/08/2024
 */

@Repository
public interface FeeImposedRepository extends JpaRepository<FeeImposed, Long> {

    @Query("SELECT f FROM FeeImposed f WHERE f.fee.semester.universityId = :universityId")
    Optional<List<FeeImposed>> getAll(@Param("universityId") Long universityId);

    @Query("SELECT f FROM FeeImposed f WHERE f.id = :id AND f.fee.semester.universityId = :universityId")
    Optional<FeeImposed> getById(@Param("id") Long id,
                                 @Param("universityId") Long universityId);

    //findByStudentAndFee
    @Query("""
            SELECT f FROM FeeImposed f
            WHERE f.student.id = :studentId
            AND f.fee.id = :feeId
            """)
    Optional<FeeImposed> findByStudentAndFee(@Param("studentId") Long studentId,
                                             @Param("feeId") Long feeId);

    Optional<List<FeeImposed>> findAllByStudentId(Long studentId);
    
}

