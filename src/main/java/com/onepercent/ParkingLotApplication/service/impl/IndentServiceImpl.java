package com.onepercent.ParkingLotApplication.service.impl;

import com.onepercent.ParkingLotApplication.domain.Indent;
import com.onepercent.ParkingLotApplication.domain.PendingIndentsManager;
import com.onepercent.ParkingLotApplication.exception.NoAvailableSpaceException;
import com.onepercent.ParkingLotApplication.repository.IndentRepository;
import com.onepercent.ParkingLotApplication.repository.ParkingLotRepository;
import com.onepercent.ParkingLotApplication.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PendingIndentsManager ordersManager;

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
        return this.ordersManager.grabIndent(indentId, coordinatorId);
    }
}
