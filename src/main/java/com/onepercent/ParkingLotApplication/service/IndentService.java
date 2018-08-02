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

}
