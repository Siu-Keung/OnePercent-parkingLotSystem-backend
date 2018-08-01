package com.onepercent.ParkingLotApplication.service.impl;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.repository.ParkingLotRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Dylan Wei
 * @date 2018-08-01 13:18
 */
public class ParkingLotServiceImplTest {
    private ParkingLotServiceImpl parkingLotService;

    private ParkingLotRepository repository;

    @Before
    public void beforeEach(){
        this.repository = mock(ParkingLotRepository.class);
        this.parkingLotService = new ParkingLotServiceImpl(this.repository);
    }

    @Test
    public void should_get_specefic_parkinglot_given_valid_id(){
        ParkingLot temp = new ParkingLot();
        Optional optional = Optional.of(temp);
        when(this.repository.findById(any())).thenReturn(optional);

        ParkingLot parkingLot = this.parkingLotService.getParkingLotById(123L);

        assertThat(parkingLot, notNullValue());
    }


}