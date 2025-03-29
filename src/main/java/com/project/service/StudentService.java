package com.project.service;

import com.project.dao.StudentDao;
import com.project.dto.StudentCreateReqDto;
import com.project.dto.StudentResDto;
import com.project.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
//@RequiredArgsConstructor
public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public ResponseEntity<StudentResDto> saveStudent(StudentCreateReqDto studentCreateReqDto) {
        log.info("Inside saveStudent service");

        if (studentCreateReqDto.getAge() > 18) {
            log.error("Student can't register to the system");
            throw new RuntimeException("Student can't register to the system.");
        }

        Student student = new Student();
        student.setFirstName(studentCreateReqDto.getFirstName());
        student.setLastName(studentCreateReqDto.getLastName());
        student.setGrade(studentCreateReqDto.getGrade());
        student.setAddress(studentCreateReqDto.getAddress());

        student = studentDao.save(student);

        if (student.getStudentId() == null) {
            throw new RuntimeException("Student save request failed.");
        } else {

            return ResponseEntity.ok(student.toDto());
        }
    }

    public ResponseEntity<List<StudentResDto>> getAllStudents() {
        List<Student> studentList = studentDao.findAll();

        List<StudentResDto> response = new ArrayList<>();
        for (Student student : studentList) {
            StudentResDto resDto = student.toDto();
            response.add(resDto);
        }
        return ResponseEntity.ok(response);

    }

    public ResponseEntity<StudentResDto> getStudentById(Long studentId) {
        if (studentId == null) {
            throw new RuntimeException("Student ID is required");
        } else {
            Optional<Student> optStudent = studentDao.findById(studentId);
            if (optStudent.isPresent()) {
                return ResponseEntity.ok(optStudent.get().toDto());
            } else {
                throw new RuntimeException("Data not found for given Student ID");
            }
        }
    }

    public ResponseEntity<StudentResDto> updateStudent(Long studentId, Student student) {
        if (studentId == null) {
            throw new RuntimeException("Student ID required");
        } else {

            Optional<Student> optStudent = studentDao.findById(studentId);
            if (optStudent.isPresent()) {
                Student studentUpdateObj = optStudent.get();
                studentUpdateObj.setFirstName(student.getFirstName());
                studentUpdateObj.setLastName(student.getLastName());
                studentUpdateObj.setGrade(student.getGrade());
                studentUpdateObj.setAddress(student.getAddress());

                studentUpdateObj = studentDao.save(studentUpdateObj);
                return ResponseEntity.ok(studentUpdateObj.toDto());
            } else {
                throw new RuntimeException("Data not found for given Student ID");
            }

        }
    }

    public ResponseEntity<String> deleteStudent(Long studentId) {
        if (studentId == null) {
            throw new RuntimeException("Student ID required");
        } else {
            Optional<Student> optStudent = studentDao.findById(studentId);
            if (optStudent.isPresent()) {
                studentDao.deleteById(studentId);
                return ResponseEntity.ok("Student deleted");
            } else {
                throw new RuntimeException("Data not found for given Student ID");
            }
        }

    }

    public ResponseEntity<List<StudentResDto>> searchStudent(String search) {
        if (search.isEmpty()) {
            throw new RuntimeException("Search value required");
        } else {
            List<StudentResDto> responseList = new ArrayList<>();
            List<Student> studentList = studentDao.findBySearchValue(search.toLowerCase());
            for (Student s : studentList) {
                responseList.add(s.toDto());
            }

            return ResponseEntity.ok(responseList);
        }
    }

    public ResponseEntity<List<StudentResDto>> getStudentDataWithPagination(Integer page, Integer per_page, String search, String direction, String sort) {
        Page<Student> data;
        if (direction.equalsIgnoreCase("asc")) {
            data = studentDao.findStudentDataWithPagination(search, PageRequest.of(page, per_page, Sort.by(Sort.Direction.ASC, sort)));
        } else {
            data = studentDao.findStudentDataWithPagination(search, PageRequest.of(page, per_page, Sort.by(Sort.Direction.DESC, sort)));
        }
        List<Student> studentList = data.getContent();
        List<StudentResDto> responseList = new ArrayList<>();
        for (Student s : studentList) {
            responseList.add(s.toDto());
        }
        return ResponseEntity.ok(responseList);
    }
}
