package com.sonata.employeemicroservice.serviceimpl;

import com.sonata.employeemicroservice.model.CertificateDetails;
import com.sonata.employeemicroservice.model.EmployeeDetails;
import com.sonata.employeemicroservice.repository.EmployeeRepository;
import com.sonata.employeemicroservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Value("${employee-microservice-url}")
    private String certificateUrl;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public List<EmployeeDetails> getAllEmployees() {
        logger.info("Retrieving all employees");
        List<EmployeeDetails> employees = employeeRepository.findAll();
        logger.info("Retrieved {} employees", employees.size());
        return employees;
    }

    @Override
    public List<EmployeeDetails> getEmployee(long employeeId) {
        logger.info("Getting employee details for employee ID: {}", employeeId);

        List<EmployeeDetails> employees = employeeRepository.findAll().stream()
                .filter(e -> e.getEmployeeId() == employeeId)
                .collect(Collectors.toList());

        logger.info("Found {} employees with ID {}", employees.size(), employeeId);

        return employees;
    }

    @Override
    public EmployeeDetails saveEmployee(EmployeeDetails employee) {
        try {
            logger.info("Saving employee: {}", employee);
            EmployeeDetails savedEmployee = employeeRepository.save(employee);
            logger.info("Employee saved: {}", savedEmployee);
            return savedEmployee;
        } catch (Exception e) {
            logger.error("Error saving employee: {}", e.getMessage());
            throw new RuntimeException("Failed to save employee", e);
        }
    }


    @Override
    public List<EmployeeDetails> deleteEmployee(long employeeId) {
        try {
            logger.info("Deleting employees with ID: {}", employeeId);

            List<EmployeeDetails> employeeList = employeeRepository.findAll().stream()
                    .filter(e -> e.getEmployeeId() == employeeId)
                    .collect(Collectors.toList());

            for (EmployeeDetails employee : employeeList) {
                logger.info("Deleting employee: {}", employee);
                employeeRepository.delete(employee);
            }

            logger.info("Deleted {} employees with ID: {}", employeeList.size(), employeeId);

            return employeeList;
        } catch (Exception e) {
            logger.error("Error deleting employees: {}", e.getMessage());
            throw new RuntimeException("Failed to delete employees", e);
        }
    }


    @Override
    public List<EmployeeDetails> updateEmployee(EmployeeDetails employee) {
        try {
            logger.info("Updating employee with ID: {}", employee.getEmployeeId());

            List<EmployeeDetails> existingEmployeeList = employeeRepository.findAll().stream()
                    .filter(e -> e.getEmployeeId() == employee.getEmployeeId())
                    .collect(Collectors.toList());

            List<EmployeeDetails> updatedList = new ArrayList<>();

            for (EmployeeDetails e1 : existingEmployeeList) {
                if (employee.getEmployeeName() != null) {
                    e1.setEmployeeName(employee.getEmployeeName());
                }
                if (employee.getCompetency() != null) {
                    e1.setCompetency(employee.getCompetency());
                }
                if (employee.getCertificateId() != null) {
                    e1.setCertificateId(employee.getCertificateId());
                }

                employeeRepository.save(e1);
                updatedList.add(e1);

                logger.info("Updated employee: {}", e1);
            }

            logger.info("Updated {} employees with ID: {}", updatedList.size(), employee.getEmployeeId());

            return updatedList;
        } catch (Exception e) {
            logger.error("Error updating employees: {}", e.getMessage());
            throw new RuntimeException("Failed to update employees", e);
        }
    }


    @Override
    public List<CertificateDetails> getCertificates(long employeeId) {
        try {
            logger.info("Getting certificates for employee with ID: {}", employeeId);

            List<EmployeeDetails> employeeList = employeeRepository.findAll().stream()
                    .filter(e -> e.getEmployeeId() == employeeId)
                    .collect(Collectors.toList());

            List<Long> certificateIdList = new ArrayList<>();

            for (EmployeeDetails employee : employeeList) {
                certificateIdList.add(employee.getCertificateId());
            }

            List<CertificateDetails> certificateList = new ArrayList<>();

            for (Long certificateId : certificateIdList) {
                CertificateDetails certificate = restTemplate
                        .getForObject(certificateUrl + "/{certificateId}", CertificateDetails.class, certificateId);
                certificateList.add(certificate);
            }

            logger.info("Retrieved {} certificates for employee with ID: {}", certificateList.size(), employeeId);

            return certificateList;
        } catch (Exception e) {
            logger.error("Error getting certificates for employee with ID {}: {}", employeeId, e.getMessage());
            throw new RuntimeException("Failed to get certificates for employee", e);
        }
    }

}
