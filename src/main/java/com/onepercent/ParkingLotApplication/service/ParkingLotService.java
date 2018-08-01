package com.onepercent.ParkingLotApplication.service;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-08-01 13:16
 */
public interface ParkingLotService {

    ParkingLot getParkingLotById(Long id) throws ResourceNotFoundException;

    List<ParkingLot> getParkingLotsPaging(PageRequest pageRequest) throws ResourceNotFoundException;

}
