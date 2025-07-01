package com.property.service;

import com.property.model.Facility;
import com.property.model.Booking;

import java.util.List;

public interface FacilityService {
    List<Facility> getAllFacilities();
    List<Facility> getActiveFacilities();
    Facility getFacilityById(Long id);
    Facility saveFacility(Facility facility);
    void deleteFacility(Long id);

    List<Booking> getAllBookings();
    List<Booking> getUpcomingBookings();
    List<Booking> getUserBookings();
    Booking bookFacility(Long facilityId, String date, String time);
    void cancelBooking(Long bookingId);
}