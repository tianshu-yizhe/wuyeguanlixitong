package com.property.repository;

import com.property.model.Repair;
import com.property.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    List<Repair> findByUser(User user);
    List<Repair> findByStatus(String status);
    List<Repair> findByTechnician(User technician);
    List<Repair> findByUrgentTrueAndStatusNot(String status);
}