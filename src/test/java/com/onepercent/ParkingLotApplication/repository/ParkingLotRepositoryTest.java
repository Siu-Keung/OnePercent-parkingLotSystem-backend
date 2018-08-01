package com.onepercent.ParkingLotApplication.repository;

import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Dylan Wei
 * @date 2018-08-01 12:33
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingLotRepositoryTest {
    @Autowired
    private TestEntityManager manager;
    @Autowired
    private ParkingLotRepository repository;

    @Test
    public void should_get_parkinglot_with_coordinator(){
        User user = manager.persistAndFlush(new User());
        manager.persistAndFlush(new User());

        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setCoordinator(user);
        parkingLot = manager.persistAndFlush(parkingLot);

        Optional<ParkingLot> optional = this.repository.findById(parkingLot.getId());

        assertThat(optional.isPresent(), is(true));
        assertThat(optional.get().getCoordinator().getId(), is(user.getId()));
    }

}