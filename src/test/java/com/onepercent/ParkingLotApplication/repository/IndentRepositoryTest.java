package com.onepercent.ParkingLotApplication.repository;

import com.onepercent.ParkingLotApplication.domain.Indent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Dylan Wei
 * @date 2018-08-02 20:10
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class IndentRepositoryTest {
    @Autowired
    private TestEntityManager manager;
    @Autowired
    private IndentRepository repository;



}