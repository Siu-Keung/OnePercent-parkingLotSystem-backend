package com.onepercent.ParkingLotApplication.repository;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dylan Wei
 * @date 2018-08-01 12:32
 */
@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {


}
