package com.example.calculate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.calculate.dao.FareRequest;
import com.example.calculate.dao.FareResponse;
import com.example.calculate.service.FareService;

@Service
public class FareServiceImpl implements FareService {

	public FareResponse calculateFare(FareRequest req) {
		double distanceCharge = req.getDistance() * 1.5;
		double durationCharge = req.getDuration() * 0.34;
		double baseFare = distanceCharge + durationCharge;
		double finalFare = baseFare;

		List<Map<String, Object>> appliedFactors = new ArrayList<>();
		Map<String, Double> breakdown = new HashMap<>();
		breakdown.put("distanceCharge", distanceCharge);
		breakdown.put("durationCharge", durationCharge);

		double surgeMultiplier = switch (req.getTimeOfDay()) {
		case PEAK_HOURS -> 1.5;
		case NIGHT -> 2.0;
		case STANDARD -> 1.0;
		};

		double surgeAmount = baseFare * (surgeMultiplier - 1);
		finalFare *= surgeMultiplier;
		appliedFactors.add(Map.of("type", "TIME_SURGE", "multiplier", surgeMultiplier));
		breakdown.put("surgeAmount", surgeAmount);

		double vehicleMultiplier = switch (req.getVehicleType()) {
		case PREMIUM -> 1.2;
		case LUXURY -> 1.5;
		case STANDARD -> 1.0;
		};
		finalFare *= vehicleMultiplier;
		appliedFactors.add(Map.of("type", "VEHICLE_TYPE", "multiplier", vehicleMultiplier));

		double discountPercent = switch (req.getPassengerLoyaltyTier()) {
		case SILVER -> 5;
		case GOLD -> 10;
		case PLATINUM -> 15;
		case BRONZE -> 0;
		};
		double discountAmount = finalFare * (discountPercent / 100);
		finalFare -= discountAmount;
		appliedFactors.add(Map.of("type", "LOYALTY_DISCOUNT", "percentage", discountPercent));
		breakdown.put("loyaltyDiscount", discountAmount);

		FareResponse res = new FareResponse();
		res.setRideId(req.getRideId());
		res.setBaseFare(baseFare);
		res.setFinalFare(finalFare);
		res.setAppliedFactors(appliedFactors);
		res.setBreakdown(breakdown);

		return res;
	}
}