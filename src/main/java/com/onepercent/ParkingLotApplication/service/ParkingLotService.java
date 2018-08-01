package com.onepercent.ParkingLotApplication.service;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;

/**
 * @author Dylan Wei
 * @date 2018-08-01 13:16
 */
public interface ParkingLotService {

    ParkingLot getParkingLotById(Long id) throws ResourceNotFoundException;



}
