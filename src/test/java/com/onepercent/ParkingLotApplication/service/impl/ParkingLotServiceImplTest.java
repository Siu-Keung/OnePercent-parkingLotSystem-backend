package com.onepercent.ParkingLotApplication.service.impl;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.domain.User;
import com.onepercent.ParkingLotApplication.exception.OperationNotAllowedException;
import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;
import com.onepercent.ParkingLotApplication.repository.ParkingLotRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author Dylan Wei
 * @date 2018-08-01 13:18
 */
public class ParkingLotServiceImplTest {
    private ParkingLotServiceImpl parkingLotService;

    private ParkingLotRepository repository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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

    @Test
    public void should_throw_exception_given_id_not_exists() {
        Optional optional = Optional.empty();

        when(this.repository.findById(anyLong())).thenReturn(optional);

        expectedException.expect(ResourceNotFoundException.class);
        this.parkingLotService.getParkingLotById(1234L);
    }

    @Test
    public void should_get_parkinglots_paging(){
        List<ParkingLot> list = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot1.setId(1L);
        parkingLot2.setId(2L);
        list.add(parkingLot1);
        list.add(parkingLot2);
        Page page = mock(Page.class);

        when(this.repository.findAll(any(Pageable.class))).thenReturn(page);
        when(page.getTotalElements()).thenReturn(2L);
        when(page.getContent()).thenReturn(list);

        List<ParkingLot> resultList = this.parkingLotService.getParkingLotsPaging(PageRequest.of(1, 2));
        assertThat(resultList.size(), is(2));
        assertThat(resultList.get(0).getId(), is(1L));
        assertThat(resultList.get(1).getId(), is(2L));
    }

    @Test
    public void should_throw_exception_given_wrong_page_range(){
        Page page = mock(Page.class);

        when(this.repository.findAll(any(Pageable.class))).thenReturn(page);
        when(page.getNumber()).thenReturn(0);

        expectedException.expect(ResourceNotFoundException.class);
        List<ParkingLot> resultList = this.parkingLotService.getParkingLotsPaging(PageRequest.of(1, 2));
    }

    @Test
    public void should_update_parking_lot_successfully(){
        Optional<ParkingLot> optional = Optional.of(mock(ParkingLot.class));
        when(repository.findById(anyLong())).thenReturn(optional);
        this.parkingLotService.updateParkingLot(mock(ParkingLot.class));

        verify(this.repository).save(any(ParkingLot.class));
    }

    @Test
    public void should_throw_NOT_FOUND_exception_given_id_not_exists(){
        when(this.repository.existsById(anyLong())).thenReturn(false);

        expectedException.expect(ResourceNotFoundException.class);
        this.parkingLotService.updateParkingLot(mock(ParkingLot.class));
    }

    @Test
    public void should_throw_NOT_ALLOWED_exception_given_parking_lot_have_car_or_coordinator(){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setId(1L);
        parkingLot.setCoordinator(new User());
        parkingLot.setTotalSize(100);
        parkingLot.setSpareSize(99);
        Optional<ParkingLot> optional = Optional.of(parkingLot);

        when(this.repository.findById(anyLong())).thenReturn(optional);

        expectedException.expect(OperationNotAllowedException.class);
        this.parkingLotService.updateParkingLot(mock(ParkingLot.class));

    }

    @Test
    public void should_add_parkinglot_successfully(){
        when(this.repository.save(any())).thenReturn(mock(ParkingLot.class));

        this.parkingLotService.addParkingLot(mock(ParkingLot.class));

        verify(this.repository).save(any());
    }

    @Test
    public void should_add_parkinglot_failed(){
        expectedException.expect(OperationNotAllowedException.class);
        this.parkingLotService.addParkingLot(mock(ParkingLot.class));
    }


}