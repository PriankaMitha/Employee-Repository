package com.sonata.employeemicroservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificateDetails {
    @Id
    private long certificateId;
    private String courseName;
    private Integer criticality;
    private String validity;
    private String vendor;
    private double cost;
    private Integer score;
}
