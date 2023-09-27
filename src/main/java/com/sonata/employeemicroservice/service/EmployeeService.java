package com.sonata.employeemicroservice.service;

import com.sonata.employeemicroservice.model.CertificateDetails;
import com.sonata.employeemicroservice.model.EmployeeDetails;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeDetails> getAllEmployees();
    public List<EmployeeDetails> getEmployee(long employeeId);
    public EmployeeDetails saveEmployee(EmployeeDetails employee);
    public List<EmployeeDetails> deleteEmployee(long employeeId);
    public List<EmployeeDetails> updateEmployee(EmployeeDetails employee);
    public List<CertificateDetails> getCertificates(long employeeId);

}
