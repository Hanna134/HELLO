package com.example.calculate.dao;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class FareRequest {
	
	@NotNull
    @Pattern(regexp = "^R\\d{5}$", message = "rideId must be in format 'R12345'")
    public String rideId;
    
    @Positive(message = "Distance must be positive")
    private double distance;
    
    @Positive(message = "duration must be positive")
    private int duration;
    
    private TimeOfDay timeOfDay;
    
    private VehicleType vehicleType;
    
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
	public String getTimeOfDay() {
		return timeOfDay;
	}
	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getPassengerLoyaltyTier() {
		return passengerLoyaltyTier;
	}
	public void setPassengerLoyaltyTier(String passengerLoyaltyTier) {
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
