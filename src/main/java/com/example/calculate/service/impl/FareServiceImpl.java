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

        double surgeMultiplier = 1.0;
        switch (req.getTimeOfDay()) {
            case PEAK_HOURS:
                surgeMultiplier = 1.5;
                break;
            case NIGHT:
                surgeMultiplier = 2.0;
                break;
            case STANDARD:
                surgeMultiplier = 1.0;
                break;
        }

        double surgeAmount = baseFare * (surgeMultiplier - 1);
        finalFare *= surgeMultiplier;

        Map<String, Object> timeSurgeFactor = new HashMap<>();
        timeSurgeFactor.put("type", "TIME_SURGE");
        timeSurgeFactor.put("multiplier", surgeMultiplier);
        appliedFactors.add(timeSurgeFactor);

        breakdown.put("surgeAmount", surgeAmount);

        double vehicleMultiplier = 1.0;
        switch (req.getVehicleType()) {
            case PREMIUM:
                vehicleMultiplier = 1.2;
                break;
            case LUXURY:
                vehicleMultiplier = 1.5;
                break;
            case STANDARD:
                vehicleMultiplier = 1.0;
                break;
        }
        finalFare *= vehicleMultiplier;

        Map<String, Object> vehicleFactor = new HashMap<>();
        vehicleFactor.put("type", "VEHICLE_TYPE");
        vehicleFactor.put("multiplier", vehicleMultiplier);
        appliedFactors.add(vehicleFactor);

        double discountPercent = 0;
        switch (req.getPassengerLoyaltyTier()) {
            case SILVER:
                discountPercent = 5;
                break;
            case GOLD:
                discountPercent = 10;
                break;
            case PLATINUM:
                discountPercent = 15;
                break;
            case BRONZE:
                discountPercent = 0;
                break;
        }
        double discountAmount = finalFare * (discountPercent / 100);
        finalFare -= discountAmount;

        Map<String, Object> loyaltyFactor = new HashMap<>();
        loyaltyFactor.put("type", "LOYALTY_DISCOUNT");
        loyaltyFactor.put("percentage", discountPercent);
        appliedFactors.add(loyaltyFactor);

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
