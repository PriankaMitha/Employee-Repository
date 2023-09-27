package com.sonata.employeemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EmployeePrimaryKeys.class)
public class EmployeeDetails {
    @Id
    private Long employeeId;
    private String employeeName;
    private String competency;
    @Id
    private Long certificateId;
}
