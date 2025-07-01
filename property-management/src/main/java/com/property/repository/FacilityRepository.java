package com.property.repository;

import com.property.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    List<Facility> findByActiveTrue();
    List<Facility> findByRequiresBookingTrue();
}