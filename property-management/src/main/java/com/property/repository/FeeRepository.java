package com.property.repository;

import com.property.model.Fee;
import com.property.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeRepository extends JpaRepository<Fee, Long> {
    List<Fee> findByUser(User user);
    List<Fee> findByUserAndPaidFalse(User user);
    List<Fee> findByPaidFalse();
}