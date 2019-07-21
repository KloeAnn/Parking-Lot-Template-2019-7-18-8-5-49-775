package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping("/parking-lots")
    public ResponseEntity addParkingLot(@RequestBody ParkingLot parkingLot) {
        return ResponseEntity.ok(parkingLotService.addParkingLot(parkingLot));
    }
}