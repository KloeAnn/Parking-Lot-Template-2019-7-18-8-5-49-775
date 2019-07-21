package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ParkingLotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ParkingLotService parkingLotService;

    private List<ParkingLot> parkingLotList;

    @Before
    public void setUp() {
        parkingLotList = new ArrayList<ParkingLot>(){{
            add(new ParkingLot("name", 10, "Beijing"));
            add(new ParkingLot("name2", 15, "Beijing"));
            add(new ParkingLot("name3", 30, "Beijing"));
        }};
    }

    @Test
    public void should_return_parking_lot_with_id_when_request_to_add() throws Exception {
        ParkingLot parkingLot = new ParkingLot("name", 10, "Beijing");
        Mockito.when(parkingLotService.addParkingLot(Mockito.any()))
                .thenReturn(parkingLot);
    }

    @Test
    @Transactional
    public void should_return_remain_parking_lots_when_delete_one() throws Exception {
        doNothing().when(parkingLotService).removeParkingLot(Mockito.anyString());
        Mockito.when(parkingLotService.findAll())
                .thenReturn(parkingLotList);

        mockMvc.perform(delete("/parking-lots/{parkingLotName}", "name")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }


}