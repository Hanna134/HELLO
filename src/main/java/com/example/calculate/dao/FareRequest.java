package com.example.calculate.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class FareRequest {

    @NotNull
    @Pattern(regexp = "^R\\d{5}$", message = "rideId must be in format 'R12345'")
    private String rideId;

    @Positive(message = "Distance must be positive")
    private double distance;

    @Positive(message = "Duration must be positive")
    private int duration;

    @NotNull(message = "Time of day must not be null")
    private TimeOfDay timeOfDay;

    @NotNull(message = "Vehicle type must not be null")
    private VehicleType vehicleType;

    @NotNull(message = "Loyalty tier must not be null")
    private LoyaltyTier passengerLoyaltyTier;

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public LoyaltyTier getPassengerLoyaltyTier() {
        return passengerLoyaltyTier;
    }

    public void setPassengerLoyaltyTier(LoyaltyTier passengerLoyaltyTier) {
        this.passengerLoyaltyTier = passengerLoyaltyTier;
    }

    public enum TimeOfDay {
        STANDARD, PEAK_HOURS, NIGHT
    }

    public enum VehicleType {
        STANDARD, PREMIUM, LUXURY
    }

    public enum LoyaltyTier {
        BRONZE, SILVER, GOLD, PLATINUM
    }
}
