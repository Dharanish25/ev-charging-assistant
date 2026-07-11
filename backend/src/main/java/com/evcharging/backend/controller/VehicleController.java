package com.evcharging.backend.controller;

import com.evcharging.backend.dto.VehicleDtos.*;
import com.evcharging.backend.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public VehicleResponse addVehicle(Authentication auth, @RequestBody VehicleRequest request) {
        return vehicleService.addVehicle(auth.getName(), request);
    }

    @GetMapping
    public List<VehicleResponse> getUserVehicles(Authentication auth) {
        return vehicleService.getUserVehicles(auth.getName());
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(Authentication auth, @PathVariable Long id) {
        vehicleService.deleteVehicle(auth.getName(), id);
    }
}