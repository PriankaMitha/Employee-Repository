package com.sonata.employeemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CertificateDetailsList {
    private List<CertificateDetails> certificateList;
}

