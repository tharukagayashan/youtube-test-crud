package com.project.dao;

import com.project.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {

    @Query("SELECT S FROM Student S WHERE LOWER(S.firstName) LIKE %?1% OR LOWER(S.lastName) LIKE %?1% OR LOWER(S.address) LIKE %?1% ")
    List<Student> findBySearchValue(@Param("search") String search);

    @Query("SELECT S FROM Student S WHERE LOWER(S.firstName) LIKE %?1% OR LOWER(S.lastName) LIKE %?1% OR LOWER(S.address) LIKE %?1% ")
    Page<Student> findStudentDataWithPagination(String search, PageRequest pageRequest);
}
