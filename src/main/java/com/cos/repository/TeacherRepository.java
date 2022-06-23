package com.cos.repository;

import com.cos.domain.Teacher;
import com.cos.exception.ConflictException;
import com.cos.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long>{

    Boolean existsByEmail(String email) throws ConflictException;

    List<Teacher> findByBranch(String branch) throws ResourceNotFoundException;

}
