package com.project.dto;

import com.project.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResDto {
    private Long studentId;
    private String firstName;
    private String lastName;
    private Integer grade;
    private String address;

    public Student toEntity() {
        Student student = new Student();
        student.setStudentId(this.studentId);
        student.setFirstName(this.firstName);
        student.setLastName(this.lastName);
        student.setGrade(this.grade);
        student.setAddress(this.address);

        return student;
    }
}
