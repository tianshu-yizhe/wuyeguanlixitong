package com.property.controller;

import com.property.model.Repair;
import com.property.service.FacilityService;
import com.property.service.FeeService;
import com.property.service.RepairService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resident")
public class ResidentController {

    private final RepairService repairService;
    private final FeeService feeService;
    private final FacilityService facilityService;

    public ResidentController(RepairService repairService, FeeService feeService, FacilityService facilityService) {
        this.repairService = repairService;
        this.feeService = feeService;
        this.facilityService = facilityService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pendingRepairs", repairService.getPendingRepairs());
        model.addAttribute("completedRepairs", repairService.getCompletedRepairs());
        model.addAttribute("fees", feeService.getRecentFees());
        model.addAttribute("facilityBookings", facilityService.getUpcomingBookings());
        return "resident/dashboard";
    }

    @GetMapping("/repairs")
    public String repairs(Model model) {
        model.addAttribute("repairs", repairService.getAllRepairs());
        return "resident/repairs";
    }

    @GetMapping("/repairs/new")
    public String showRepairForm(Model model) {
        model.addAttribute("repair", new Repair());
        return "resident/new-repair";
    }

    @PostMapping("/repairs")
    public String submitRepair(@ModelAttribute Repair repair) {
        repairService.saveRepair(repair);
        return "redirect:/resident/repairs";
    }

    @GetMapping("/fees")
    public String fees(Model model) {
        model.addAttribute("fees", feeService.getAllFees());
        model.addAttribute("unpaidFees", feeService.getUnpaidFees());
        return "resident/fees";
    }

    @PostMapping("/fees/pay/{id}")
    public String payFee(@PathVariable Long id) {
        feeService.payFee(id);
        return "redirect:/resident/fees";
    }

    @GetMapping("/facilities")
    public String facilities(Model model) {
        model.addAttribute("facilities", facilityService.getAllFacilities());
        model.addAttribute("bookings", facilityService.getUserBookings());
        return "resident/facilities";
    }

    @PostMapping("/facilities/book")
    public String bookFacility(@RequestParam Long facilityId,
                               @RequestParam String date,
                               @RequestParam String time) {
        facilityService.bookFacility(facilityId, date, time);
        return "redirect:/resident/facilities";
    }
}