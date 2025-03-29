package com.project.model;

import com.project.dto.StudentResDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "FIRST_NAME", length = 20)
    private String firstName;

    @Column(name = "LAST_NAME", length = 20)
    private String lastName;

    @Column(name = "GRADE")
    private Integer grade;

    @Column(name = "ADDRESS")
    private String address;

    public StudentResDto toDto() {
        StudentResDto resDto = new StudentResDto();
        resDto.setStudentId(this.studentId);
        resDto.setFirstName(this.firstName);
        resDto.setLastName(this.lastName);
        resDto.setAddress(this.address);
        resDto.setGrade(this.grade);

        return resDto;
    }
}