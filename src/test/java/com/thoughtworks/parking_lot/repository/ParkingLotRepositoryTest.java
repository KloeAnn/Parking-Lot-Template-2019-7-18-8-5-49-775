package com.thoughtworks.parking_lot.repository;

import com.thoughtworks.parking_lot.model.ParkingLot;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParkingLotRepositoryTest {
    @Autowired
    private ParkingLotRepository parkingLotRepository;

    List<ParkingLot> parkingLots;

    @Before
    public void setUp() {
        parkingLots = new ArrayList<ParkingLot>(){
            {
                add(new ParkingLot("name", 10, "Beijing"));
                add(new ParkingLot("name2", 15, "Beijing"));
                add(new ParkingLot("name3", 30, "Beijing"));
            }};
        parkingLotRepository.saveAll(parkingLots);
    }

    @Test
    @DirtiesContext
    public void should_return_the_parking_lots_when_find_it_by_name() {
        ParkingLot parkingLot = parkingLotRepository.findByName("name");
        assertEquals(parkingLot.getCapacity(), 10);
    }
}