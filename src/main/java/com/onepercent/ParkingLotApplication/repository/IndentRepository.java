package com.onepercent.ParkingLotApplication.repository;

import com.onepercent.ParkingLotApplication.domain.Indent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-08-02 14:46
 */
public interface IndentRepository extends JpaRepository<Indent, Long> {

    List<Indent> findByStatusEquals(String status);


}
