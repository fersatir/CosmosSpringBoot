package com.cos.service;

import com.cos.domain.Teacher;
import com.cos.exception.ConflictException;
import com.cos.exception.ResourceNotFoundException;
import com.cos.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImp implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void teacherCreate(Teacher teacher) {
         teacherRepository.save(teacher);
    }
    @Override
    public Teacher findTeacherId(Long id) throws ResourceNotFoundException {
        return teacherRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Teacher not found"+id));
    }

    @Override
    public List<Teacher> findTeacherBranch(String branch) {
        return teacherRepository.findByBranch(branch);
    }

    @Override
    public Page<Teacher> findTeacherPage(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public List<Teacher> getAllTeacher() {return teacherRepository.findAll();}

    @Override
    public void updateTeacher(Long id, Teacher teacher) {
        Boolean emailExists = teacherRepository.existsByEmail(teacher.getEmail());
        Teacher t = findTeacherId(id);// db'den update edilecek veri alındı

        if(emailExists && !t.getEmail().equals(teacher.getEmail())){
            throw new ConflictException("Email is already exist!");
        }

        t.setFirstName(teacher.getFirstName());
        t.setLastName(teacher.getLastName());
        t.setAge(teacher.getAge());
        t.setEmail(teacher.getEmail());
        t.setBranch(teacher.getBranch());

        teacherRepository.save(t);

    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }




}
