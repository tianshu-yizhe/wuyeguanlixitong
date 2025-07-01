package com.property.service;

import com.property.model.Booking;
import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    List<Booking> getUserBookings();
    List<Booking> getFacilityBookings(Long facilityId);
    Booking createBooking(Booking booking);
    Booking updateBookingStatus(Long id, String status);
    void cancelBooking(Long id);
}