package com.property.service;

import com.property.model.Fee;

import java.util.List;

public interface FeeService {
    List<Fee> getAllFees();
    List<Fee> getRecentFees();
    List<Fee> getUnpaidFees();
    Fee getFeeById(Long id);
    Fee generateFee(Fee fee);
    void payFee(Long feeId);
    void deleteFee(Long id);
}