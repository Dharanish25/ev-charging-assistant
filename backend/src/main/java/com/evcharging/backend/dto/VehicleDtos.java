package com.evcharging.backend.dto;

import com.evcharging.backend.entity.Vehicle;

public class VehicleDtos {

    public record VehicleRequest(
        Vehicle.VehicleType vehicleType,
        String brand,
        String model,
        Integer manufacturingYear,
        Double batteryCapacityKwh,
        Integer currentBatteryPercent,
        Double rangeKm,
        Vehicle.ConnectorType connectorType,
        Double chargingSpeedKw
    ) {}

    public record VehicleResponse(
        Long id,
        String vehicleType,
        String brand,
        String model,
        Integer manufacturingYear,
        Double batteryCapacityKwh,
        Integer currentBatteryPercent,
        Double rangeKm,
        String connectorType,
        Double chargingSpeedKw
    ) {}
}