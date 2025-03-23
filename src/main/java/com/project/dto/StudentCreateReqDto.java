package com.project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCreateReqDto {

    @NotBlank(message = "First name is required")
    @Size(min = 5,message = "firstname min length is 5")
    @Size(max = 15, message = "firstname max length is 15")
    @NotNull(message = "First name cannot be null")
    private String firstName;
    private String lastName;
    private Integer grade;
    private String address;

    @Min(value = 5,message = "minimum age is 5")
    @Max(value = 15,message = "maximum age is 15")
    private Integer age;

    @Positive
    private BigDecimal totalMarks;
}
