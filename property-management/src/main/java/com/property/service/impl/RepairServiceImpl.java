package com.property.service.impl;

import com.property.dto.RepairForm;
import com.property.model.Repair;
import com.property.model.User;
import com.property.repository.RepairRepository;
import com.property.repository.UserRepository;
import com.property.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RepairServiceImpl implements RepairService {

    private final RepairRepository repairRepository;
    private final UserRepository userRepository;
    private final String UPLOAD_DIR = "uploads/repairs/";

    @Autowired
    public RepairServiceImpl(RepairRepository repairRepository, UserRepository userRepository) {
        this.repairRepository = repairRepository;
        this.userRepository = userRepository;

        // Create upload directory if not exists
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    @Override
    public List<Repair> getAllRepairs() {
        return repairRepository.findAll();
    }

    @Override
    public List<Repair> getPendingRepairs() {
        return repairRepository.findByStatus("PENDING");
    }

    @Override
    public List<Repair> getCompletedRepairs() {
        return repairRepository.findByStatus("COMPLETED");
    }

    @Override
    public Repair getRepairById(Long id) {
        return repairRepository.findById(id).orElse(null);
    }

    @Override
    public Repair saveRepair(Repair repair) {
        return repairRepository.save(repair);
    }

    @Override
    public Repair saveRepairFromForm(RepairForm repairForm) {
        Repair repair = new Repair();
        repair.setTitle(repairForm.getTitle());
        repair.setDescription(repairForm.getDescription());
        repair.setType(repairForm.getType());
        repair.setLocation(repairForm.getLocation());
        repair.setStatus("PENDING");
        repair.setReportDate(LocalDateTime.now());
        repair.setUrgent(repairForm.isUrgent());

        // Get current user (you need to implement this based on your authentication)
        // User user = getCurrentUser();
        // repair.setUser(user);

        // Handle image uploads
        if (repairForm.getImages() != null && repairForm.getImages().length > 0) {
            List<String> imagePaths = new ArrayList<>();
            for (MultipartFile file : repairForm.getImages()) {
                if (!file.isEmpty()) {
                    try {
                        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                        Path path = Paths.get(UPLOAD_DIR + fileName);
                        Files.copy(file.getInputStream(), path);
                        imagePaths.add(fileName);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to store file", e);
                    }
                }
            }
            repair.setImagePaths(imagePaths);
        }

        return repairRepository.save(repair);
    }

    @Override
    public void assignTechnician(Long repairId, Long technicianId) {
        Repair repair = repairRepository.findById(repairId).orElseThrow();
        User technician = userRepository.findById(technicianId).orElseThrow();

        repair.setTechnician(technician);
        repair.setStatus("PROCESSING");
        repairRepository.save(repair);
    }

    @Override
    public void completeRepair(Long repairId) {
        Repair repair = repairRepository.findById(repairId).orElseThrow();
        repair.setStatus("COMPLETED");
        repair.setCompletionDate(LocalDateTime.now());
        repairRepository.save(repair);
    }

    @Override
    public void deleteRepair(Long id) {
        repairRepository.deleteById(id);
    }
}