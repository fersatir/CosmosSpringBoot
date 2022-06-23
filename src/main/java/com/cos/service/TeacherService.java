package com.cos.service;

import com.cos.domain.Teacher;
import com.cos.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TeacherService {

    void teacherCreate(Teacher teacher);
    List<Teacher> getAllTeacher();
    void updateTeacher(Long id,Teacher teacher);
    void deleteTeacher(Long id);
    Teacher findTeacherId(Long id) throws ResourceNotFoundException;
    List<Teacher> findTeacherBranch(String branch) throws ResourceNotFoundException;
    Page<Teacher> findTeacherPage(Pageable pageable);



}
