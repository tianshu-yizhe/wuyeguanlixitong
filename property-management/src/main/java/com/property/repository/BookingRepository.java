package com.property.repository;

import com.property.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.property.model.User;
import com.property.model.Facility;
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
    List<Booking> findByFacility(Facility facility);
    List<Booking> findByStatus(String status);
}