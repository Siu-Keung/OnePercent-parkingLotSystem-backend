package com.onepercent.ParkingLotApplication.service.impl;

import com.onepercent.ParkingLotApplication.domain.Indent;
import com.onepercent.ParkingLotApplication.domain.IndentStatus;
import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.domain.IndentsDistributor;
import com.onepercent.ParkingLotApplication.exception.NoAvailableSpaceException;
import com.onepercent.ParkingLotApplication.repository.IndentRepository;
import com.onepercent.ParkingLotApplication.repository.ParkingLotRepository;
import com.onepercent.ParkingLotApplication.service.IndentService;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-08-02 14:45
 */
@Service
public class IndentServiceImpl implements IndentService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private IndentRepository indentRepository;
    @Autowired
    private IndentsDistributor ordersManager;
    @Autowired
    private ParkingLotService parkingLotService;

    // TODO: 2018-08-02 此处会产生线程安全问题 
    @Override
    public String generateIndent(String carNo) {
        if(this.parkingLotRepository.getSpareParkingLotCount() <= 0)
            throw new NoAvailableSpaceException();
        Indent indent = new Indent(carNo);
        this.indentRepository.save(indent);
        this.ordersManager.addIndent(indent.getId());
        return indent.getReceiptNo();
    }

    @Override
    public List<Indent> getIndentsWithStatus(String status) {
        return indentRepository.findByStatusEquals(status);
    }

    @Override
    public Indent robIndent(Long indentId, Integer coordinatorId) {
        return this.ordersManager.assignIndent(indentId, coordinatorId);
    }

    @Override
    public List<Indent> getAllUnfinishedIndents(Integer coordinatorId) {
        return this.indentRepository.findAllUnfinishedIndents(coordinatorId);
    }

    @Override
    public Indent setParkingLotToIndent(Long indentId, Long parkingLotId, Integer coordinatorId) {
        ParkingLot parkingLot = this.parkingLotService.getParkingLotById(parkingLotId);
        if(parkingLot.getSpareSize() <= 0)
            throw new NoAvailableSpaceException("停车场已满！无法停车！");
        parkingLot.setSpareSize(parkingLot.getSpareSize() - 1);
        parkingLot = this.parkingLotRepository.saveAndFlush(parkingLot);
        Indent indent = this.indentRepository.findById(indentId).get();
        indent.setParkingLotId(parkingLotId);
        indent.setStatus(IndentStatus.PARKED);
        indent.setCoordinatorId(coordinatorId);
        return this.indentRepository.saveAndFlush(indent);
    }

    @Override
    public Indent changeIndentStatusByReceiptNo(String receiptNo, String status) {
        Indent indent = this.indentRepository.findByReceiptNo(receiptNo);
        indent.setStatus(status);
        return this.indentRepository.saveAndFlush(indent);
    }

    @Override
    public Indent changeIndentStatusById(Long id, String status) {
       Indent indent = this.indentRepository.findById(id).get();
       indent.setStatus(status);
       return this.indentRepository.saveAndFlush(indent);
    }

    @Override
    public List<Indent> getIndents(Indent example) {
        return this.indentRepository.findAll(Example.of(example));
    }
}
