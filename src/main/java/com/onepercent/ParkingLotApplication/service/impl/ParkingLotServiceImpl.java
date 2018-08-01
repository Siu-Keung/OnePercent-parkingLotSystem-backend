package com.onepercent.ParkingLotApplication.service.impl;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.exception.OperationNotAllowedException;
import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;
import com.onepercent.ParkingLotApplication.repository.ParkingLotRepository;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

/**
 * @author Dylan Wei
 * @date 2018-08-01 13:18
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotServiceImpl(ParkingLotRepository repository) {
        this.parkingLotRepository = repository;
    }

    @Override
    public ParkingLot getParkingLotById(Long id) throws ResourceNotFoundException {
        Optional<ParkingLot> optional = this.parkingLotRepository.findById(id);
        if (!optional.isPresent())
            throw new ResourceNotFoundException();
        return optional.get();
    }

    @Override
    public List<ParkingLot> getParkingLotsPaging(PageRequest pageRequest) throws ResourceNotFoundException {
        Page<ParkingLot> parkingLots = this.parkingLotRepository.findAll(pageRequest);
        if (parkingLots.getTotalElements() == 0)
            throw new ResourceNotFoundException("所选页码超出范围！");
        return parkingLots.getContent();
    }

    private boolean satisfyEmpty(ParkingLot parkingLot) {
        ParkingLot actualParkingLot = this.parkingLotRepository.findById(parkingLot.getId()).get();
        if (actualParkingLot.getSpareSize() == actualParkingLot.getTotalSize())
            return true;
        return false;
    }

    private boolean satisfyWithNoCoordinator(ParkingLot parkingLot) {
        ParkingLot actualParkingLot = this.parkingLotRepository.findById(parkingLot.getId()).get();
        if (actualParkingLot.getCoordinator() == null)
            return true;
        return false;
    }

    @Override
    public void updateParkingLot(ParkingLot parkingLot) throws ResourceNotFoundException, OperationNotAllowedException {
        Optional<ParkingLot> optional = this.parkingLotRepository.findById(parkingLot.getId());
        if (!optional.isPresent())
            throw new ResourceNotFoundException("停车场不存在！");
        if (parkingLot.getAvailable() != null) {
            if (!satisfyWithNoCoordinator(parkingLot))
                throw new OperationNotAllowedException("请先移除停车员再注销该停车场！");
            if (!satisfyEmpty(parkingLot))
                throw new OperationNotAllowedException("该停车场不是空的，禁止注销！");
        }
        ParkingLot actualParkingLot = optional.get();
        if(parkingLot.getName() == null)
            parkingLot.setName(actualParkingLot.getName());
        if(parkingLot.getAvailable() == null)
            parkingLot.setAvailable(actualParkingLot.getAvailable());
        if(parkingLot.getTotalSize() == null)
            parkingLot.setTotalSize(actualParkingLot.getTotalSize());
        if(parkingLot.getSpareSize() == null)
            parkingLot.setSpareSize(actualParkingLot.getSpareSize());
        this.parkingLotRepository.save(parkingLot);
    }

    @Override
    public void addParkingLot(ParkingLot parkingLot) throws OperationNotAllowedException{
        ParkingLot result = this.parkingLotRepository.save(parkingLot);
        if(result == null)
            throw new OperationNotAllowedException();
    }
}
