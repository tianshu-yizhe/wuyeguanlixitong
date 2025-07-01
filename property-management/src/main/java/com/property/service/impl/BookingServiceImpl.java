package com.property.service.impl;

import com.property.model.Booking;
import com.property.repository.BookingRepository;
import com.property.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> getUserBookings() {
        // 需要实现获取当前用户的逻辑
        // User user = getCurrentUser();
        // return bookingRepository.findByUser(user);
        return null;
    }

    @Override
    public List<Booking> getFacilityBookings(Long facilityId) {
        // 需要实现获取设施的查询
        // Facility facility = facilityService.getFacilityById(facilityId);
        // return bookingRepository.findByFacility(facility);
        return null;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBookingStatus(Long id, String status) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public void cancelBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}