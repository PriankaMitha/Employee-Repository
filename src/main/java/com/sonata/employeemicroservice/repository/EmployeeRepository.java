package com.sonata.employeemicroservice.repository;

import com.sonata.employeemicroservice.model.EmployeeDetails;
import com.sonata.employeemicroservice.model.EmployeePrimaryKeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetails, EmployeePrimaryKeys> {
}

