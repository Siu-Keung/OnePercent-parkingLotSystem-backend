package com.onepercent.ParkingLotApplication.service.impl;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;
import com.onepercent.ParkingLotApplication.repository.ParkingLotRepository;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Optional;

/**
 * @author Dylan Wei
 * @date 2018-08-01 13:18
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotServiceImpl(ParkingLotRepository repository){
        this.parkingLotRepository = repository;
    }

    @Override
    public ParkingLot getParkingLotById(Long id) throws ResourceNotFoundException {
        Optional<ParkingLot> optional = this.parkingLotRepository.findById(id);
        if(!optional.isPresent())
            throw new ResourceNotFoundException();
        return optional.get();
    }
}
