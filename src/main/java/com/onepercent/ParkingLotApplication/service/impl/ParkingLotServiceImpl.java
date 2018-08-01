package com.onepercent.ParkingLotApplication.service.impl;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Dylan Wei
 * @date 2018-08-01 13:18
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    @Override
    public ParkingLot getParkingLotById(Long id) throws ResourceNotFoundException {
        throw new NotImplementedException();
    }
}
