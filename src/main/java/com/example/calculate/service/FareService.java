package com.example.calculate.service;

import com.example.calculate.dao.FareRequest;
import com.example.calculate.dao.FareResponse;

public  interface FareService{
	
	FareResponse calculateFare(FareRequest req);
	
}