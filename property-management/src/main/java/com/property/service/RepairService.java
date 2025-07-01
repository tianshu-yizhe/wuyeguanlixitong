package com.property.service;

import com.property.model.Repair;
import com.property.dto.RepairForm;

import java.util.List;

public interface RepairService {
    List<Repair> getAllRepairs();
    List<Repair> getPendingRepairs();
    List<Repair> getCompletedRepairs();
    Repair getRepairById(Long id);
    Repair saveRepair(Repair repair);
    Repair saveRepairFromForm(RepairForm repairForm);
    void assignTechnician(Long repairId, Long technicianId);
    void completeRepair(Long repairId);
    void deleteRepair(Long id);
}