package com.sonata.employeemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePrimaryKeys implements Serializable {
    private long employeeId;
    private long certificateId;
}
