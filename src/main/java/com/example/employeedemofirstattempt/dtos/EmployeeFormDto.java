package com.example.employeedemofirstattempt.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EmployeeFormDto {
    private Long id = null;
    private String first_name;

    private String last_name;

    private String email;

    public EmployeeFormDto(String first_name, String last_name, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }
}
