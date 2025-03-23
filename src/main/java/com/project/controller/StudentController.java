package com.project.controller;

import com.project.dto.StudentCreateReqDto;
import com.project.dto.StudentResDto;
import com.project.model.Student;
import com.project.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Slf4j
@RestController
//@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Post Request
    @PostMapping
    public ResponseEntity<StudentResDto> saveStudent(@Valid @RequestBody StudentCreateReqDto studentCreateReqDto) {
        log.info("Inside saveStudent method {}",studentCreateReqDto);
        return studentService.saveStudent(studentCreateReqDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentResDto>> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResDto> getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResDto> updateStudent(@PathVariable Long studentId, @RequestBody Student student) {
        return studentService.updateStudent(studentId, student);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId){
        return studentService.deleteStudent(studentId);
    }

}