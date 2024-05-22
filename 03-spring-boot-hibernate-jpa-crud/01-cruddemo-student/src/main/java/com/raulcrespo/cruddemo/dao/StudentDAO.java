package com.raulcrespo.cruddemo.dao;

import com.raulcrespo.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {

    void save(Student student);
    Student findById(Integer id);
    List<Student> findAll();
}
