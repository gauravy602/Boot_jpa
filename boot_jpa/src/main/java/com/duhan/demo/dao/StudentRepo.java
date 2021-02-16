package com.duhan.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duhan.demo.model.Student;


public interface StudentRepo extends JpaRepository<Student, Integer>{

}
