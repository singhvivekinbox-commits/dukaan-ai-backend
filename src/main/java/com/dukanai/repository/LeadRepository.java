package com.dukanai.repository;

import com.dukanai.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    Optional<Lead> findByCustomerPhone(String customerPhone);
}