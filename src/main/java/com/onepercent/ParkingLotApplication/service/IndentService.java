package com.onepercent.ParkingLotApplication.service;

import com.onepercent.ParkingLotApplication.domain.Indent;

import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-08-02 14:44
 */
public interface IndentService {

    String generateIndent(String carNo);

    List<Indent> getIndentsWithStatus(String status);

    Indent robIndent(Long indentId, Integer coordinatorId);

    List<Indent> getAllUnfinishedIndents(Integer coordinatorId);

    Indent setParkingLotToIndent(Long indentId, Long parkingLotId, Integer coordinatorId);

    Indent changeIndentStatusByReceiptNo(String receiptNo, String status);

    Indent changeIndentStatusById(Long id, String status);
}
