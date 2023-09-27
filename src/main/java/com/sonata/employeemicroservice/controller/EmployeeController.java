package com.sonata.employeemicroservice.controller;

import com.sonata.employeemicroservice.model.CertificateDetailsList;
import com.sonata.employeemicroservice.model.EmployeeDetails;
import com.sonata.employeemicroservice.model.EmployeeDetailsList;
import com.sonata.employeemicroservice.serviceimpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    EmployeeDetailsList employeeDetailsList;

    @Autowired
    CertificateDetailsList certificateDetailsList;

    @GetMapping("/employee")
    public EmployeeDetailsList getAllEmployees(){
        employeeDetailsList.setEmployeeList(employeeServiceImpl.getAllEmployees());
        return employeeDetailsList;
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDetailsList getEmployee(@PathVariable("employeeId") long employeeId){
        employeeDetailsList.setEmployeeList(employeeServiceImpl.getEmployee(employeeId));
        return employeeDetailsList;
    }

    @PostMapping("/employee")
    public ResponseEntity<EmployeeDetails> saveEmployee(@RequestBody EmployeeDetails employee){
        return ResponseEntity.ok(employeeServiceImpl.saveEmployee(employee));
    }

    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeDetails>> deleteEmployee(@PathVariable("employeeId") long employeeId) {
        return ResponseEntity.ok(employeeServiceImpl.deleteEmployee(employeeId));
    }

    @PutMapping("/employee")
    public ResponseEntity<List<EmployeeDetails>> updateEmployee(@RequestBody EmployeeDetails employee){
        return ResponseEntity.ok(employeeServiceImpl.updateEmployee(employee));
    }

    @GetMapping("/employee/certificate/{employeeId}")
    public CertificateDetailsList getCertificatesOfEmployee(@PathVariable("employeeId") long employeeId){
        certificateDetailsList.setCertificateList(employeeServiceImpl.getCertificates(employeeId));
        return  certificateDetailsList;
    }
}
