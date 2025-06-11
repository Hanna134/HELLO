package com.example.calculate.dao;

import java.util.List;
import java.util.Map;

public class FareResponse {

	    private String rideId;
	    private double baseFare;
	    private double finalFare;
	    private List<Map<String, Object>> appliedFactors;
	    private Map<String, Double> breakdown;
		public String getRideId() {
			return rideId;
		}
		public void setRideId(String rideId) {
			this.rideId = rideId;
		}
		public double getBaseFare() {
			return baseFare;
		}
		public void setBaseFare(double baseFare) {
			this.baseFare = baseFare;
		}
		public double getFinalFare() {
			return finalFare;
		}
		public void setFinalFare(double finalFare) {
			this.finalFare = finalFare;
		}
		public List<Map<String, Object>> getAppliedFactors() {
			return appliedFactors;
		}
		public void setAppliedFactors(List<Map<String, Object>> appliedFactors) {
			this.appliedFactors = appliedFactors;
		}
		public Map<String, Double> getBreakdown() {
			return breakdown;
		}
		public void setBreakdown(Map<String, Double> breakdown) {
			this.breakdown = breakdown;
		}
	    
	    
	    
	}

