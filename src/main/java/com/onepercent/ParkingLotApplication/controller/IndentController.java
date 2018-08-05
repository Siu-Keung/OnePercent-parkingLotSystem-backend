package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.Indent;
import com.onepercent.ParkingLotApplication.domain.IndentStatus;
import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.dto.UpdateIndentParams;
import com.onepercent.ParkingLotApplication.exception.IllegalCommandException;
import com.onepercent.ParkingLotApplication.repository.IndentRepository;
import com.onepercent.ParkingLotApplication.service.IndentService;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dylan Wei
 * @date 2018-08-02 15:05
 */
@RestController
@RequestMapping("/orders")
public class IndentController {
    @Autowired
    private IndentService indentService;
    @Autowired
    private ParkingLotService parkingLotService;


    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage', 'ParkingBoy')")
    @GetMapping
    public Object getOrders(
            @RequestParam(required = false) String carNo,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Boolean withParkingLot) {
        Indent indent = new Indent();
        indent.setCarNo(carNo);
        indent.setId(id);
        indent.setReceiptNo(null);
        indent.setCreateDate(null);
        indent.setStatus(null);
        List<Indent> result = this.indentService.getIndents(indent);
        if(withParkingLot == null || withParkingLot == false)
            return result;
        ParkingLot parkingLot = null;
        try {
            parkingLot = this.parkingLotService.getParkingLotById(result.get(0).getParkingLotId());
        }catch (Exception e){ }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("order", result.get(0));
        resultMap.put("parkingLot", parkingLot);
        return resultMap;
    }

    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage', 'ParkingBoy')")
    @PostMapping
    public String generateOrder(String carNo) {
        return this.indentService.generateIndent(carNo);
    }

    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage', 'ParkingBoy')")
    @GetMapping("/{status}")
    public List<Indent> getOrders(
            @PathVariable String status) {
        return this.indentService.getIndentsWithStatus(status);
    }

    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage', 'ParkingBoy')")
    @PutMapping("/{receiptNo}")
    public Indent unpark(@PathVariable String receiptNo) {
        return this.indentService.changeIndentStatusByReceiptNo(
                receiptNo, IndentStatus.WAITING_TO_RETRIEVE);
    }

    @PreAuthorize(" hasAnyAuthority('Admin', 'Manage', 'ParkingBoy')")
    @PatchMapping("/{indentId}")
    public Object updateIndent(
            @PathVariable Long indentId,
            UpdateIndentParams params) {
        switch (params.getOperation()) {
            case "robOrder":
                return this.indentService.robIndent(indentId, params.getCoordinatorId());
            case "setParkingLotId":
                return this.indentService.setParkingLotToIndent(indentId,
                        params.getParkingLotId(), params.getCoordinatorId());
            case "updateStatus":
                return this.indentService.changeIndentStatusById(indentId, params.getStatus());
            default:
                throw new IllegalCommandException();
        }
    }


}
