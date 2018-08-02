package com.onepercent.ParkingLotApplication.repository;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-08-01 12:32
 */
@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {

    @Query(value = "select *  from parking_lot, user where parking_lot.coordinator_id = user.id and user.phone = ?1", nativeQuery = true)
    List<ParkingLot> findByCoordinatorPhoneNumber(String phoneNumber);

    List<ParkingLot> findByTotalSizeGreaterThanEqual(Integer num);

    List<ParkingLot> findByTotalSizeLessThanEqual(Integer num);

    List<ParkingLot> findByCoordinatorId(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "update parking_lot set coordinator_id = ?2 where id = ?1")
    int updateCoordinatorId(Long id, Long coordinatorId);

    @Query(nativeQuery = true, value = "select (sum(spare_size) - (select count(*) from indent where status = '待接单') ) from parking_lot")
    int getSpareParkingLotCount();

}
