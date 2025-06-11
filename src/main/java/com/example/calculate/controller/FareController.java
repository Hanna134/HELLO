package com.example.calculate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.calculate.dao.FareRequest;
import com.example.calculate.dao.FareResponse;
import com.example.calculate.service.FareService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/rides")
public class FareController {

    @Autowired
    FareService fareService;

    @PostMapping("/fare-calculation")
    public FareResponse calculate(@Valid@RequestBody FareRequest request) {
        return fareService.calculateFare(request);
    }
}