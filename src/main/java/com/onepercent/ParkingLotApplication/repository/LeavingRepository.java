package com.onepercent.ParkingLotApplication.repository;

import com.onepercent.ParkingLotApplication.domain.Leaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dylan Wei
 * @date 2018-08-06 2:52
 */
@Repository
public interface LeavingRepository extends JpaRepository<Leaving, Long> {

}
