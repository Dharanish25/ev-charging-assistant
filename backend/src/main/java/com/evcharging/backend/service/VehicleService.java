package com.evcharging.backend.service;

import com.evcharging.backend.dto.VehicleDtos.*;
import com.evcharging.backend.entity.User;
import com.evcharging.backend.entity.Vehicle;
import com.evcharging.backend.repository.UserRepository;
import com.evcharging.backend.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public VehicleResponse addVehicle(String userEmail, VehicleRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vehicle vehicle = new Vehicle();
        vehicle.setUser(user);
        vehicle.setVehicleType(request.vehicleType());
        vehicle.setBrand(request.brand());
        vehicle.setModel(request.model());
        vehicle.setManufacturingYear(request.manufacturingYear());
        vehicle.setBatteryCapacityKwh(request.batteryCapacityKwh());
        vehicle.setCurrentBatteryPercent(request.currentBatteryPercent());
        vehicle.setRangeKm(request.rangeKm());
        vehicle.setConnectorType(request.connectorType());
        vehicle.setChargingSpeedKw(request.chargingSpeedKw());

        vehicleRepository.save(vehicle);
        return toResponse(vehicle);
    }

    public List<VehicleResponse> getUserVehicles(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return vehicleRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public void deleteVehicle(String userEmail, Long vehicleId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (!vehicle.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You do not own this vehicle");
        }

        vehicleRepository.delete(vehicle);
    }

    private VehicleResponse toResponse(Vehicle v) {
        return new VehicleResponse(
                v.getId(),
                v.getVehicleType().name(),
                v.getBrand(),
                v.getModel(),
                v.getManufacturingYear(),
                v.getBatteryCapacityKwh(),
                v.getCurrentBatteryPercent(),
                v.getRangeKm(),
                v.getConnectorType().name(),
                v.getChargingSpeedKw()
        );
    }
}