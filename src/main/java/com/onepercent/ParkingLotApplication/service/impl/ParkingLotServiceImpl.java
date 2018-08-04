package com.onepercent.ParkingLotApplication.service.impl;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.dto.Condition;
import com.onepercent.ParkingLotApplication.dto.Pagination;
import com.onepercent.ParkingLotApplication.exception.OperationNotAllowedException;
import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;
import com.onepercent.ParkingLotApplication.repository.ParkingLotRepository;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void updateParkingLot(ParkingLot parkingLot, Boolean setCoordinatorNull) throws ResourceNotFoundException, OperationNotAllowedException {
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
        if (parkingLot.getName() == null)
            parkingLot.setName(actualParkingLot.getName());
        if (parkingLot.getAvailable() == null)
            parkingLot.setAvailable(actualParkingLot.getAvailable());
        if (parkingLot.getTotalSize() == null)
            parkingLot.setTotalSize(actualParkingLot.getTotalSize());
        if (parkingLot.getSpareSize() == null)
            parkingLot.setSpareSize(actualParkingLot.getSpareSize());
        if((setCoordinatorNull == null || setCoordinatorNull == false))
            parkingLot.setCoordinator(actualParkingLot.getCoordinator());
        this.parkingLotRepository.save(parkingLot);
    }

    @Override
    public void addParkingLot(ParkingLot parkingLot) throws OperationNotAllowedException {
        ParkingLot result = this.parkingLotRepository.save(parkingLot);
        if (result == null)
            throw new OperationNotAllowedException();
    }

    private List<ParkingLot> getCommonParkingLot(List<List<ParkingLot>> lists) {
        if (lists.size() == 1)
            return lists.get(0);
        List<ParkingLot> list = lists.get(0).stream().filter(item -> lists.get(1).contains(item)).collect(Collectors.toList());
        List<ParkingLot> resultList = lists.get(0);
        for (int i = 2; i < lists.size(); i++) {
            List<ParkingLot> currentList = lists.get(i);
            list = list.stream().filter(item -> currentList.contains(item)).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public List<ParkingLot> getParkingLotsByCondition(Condition condition, Pagination pagination) {
        List<List<ParkingLot>> resultLists = new ArrayList<>();
        if (condition.getPhoneNumber() != null) {
            resultLists.add(this.parkingLotRepository.findByCoordinatorPhoneNumber(condition.getPhoneNumber()));
        }
        if (condition.getName() != null) {
            ParkingLot probe = new ParkingLot();
            probe.setName(condition.getName());
            resultLists.add(this.parkingLotRepository.findAll(Example.of(probe)));
        }
        if (condition.getGreaterThanEqual() != null) {
            resultLists.add(this.parkingLotRepository.findByTotalSizeGreaterThanEqual(condition.getGreaterThanEqual()));
        }
        if (condition.getLessThanEqual() != null) {
            resultLists.add(this.parkingLotRepository.findByTotalSizeLessThanEqual(condition.getLessThanEqual()));
        }
        if(condition.getCoordinatorId() != null){
            resultLists.add(this.parkingLotRepository.findByCoordinatorId(condition.getCoordinatorId()));
        }
        List<ParkingLot> list = getCommonParkingLot(resultLists);
        int startIndex = (pagination.getPage() - 1) * pagination.getSize();
        int end = startIndex + pagination.getSize();
        if (end >= list.size())
            end = list.size();
        try {
            return list.subList(startIndex, end);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public List<ParkingLot> getAllParkingLots() {
        return this.parkingLotRepository.findAll();
    }
}
