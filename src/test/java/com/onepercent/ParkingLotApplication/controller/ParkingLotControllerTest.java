package com.onepercent.ParkingLotApplication.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onepercent.ParkingLotApplication.config.WebSecurityConfig;
import com.onepercent.ParkingLotApplication.domain.ParkingLot;
import com.onepercent.ParkingLotApplication.exception.OperationNotAllowedException;
import com.onepercent.ParkingLotApplication.exception.ResourceNotFoundException;
import com.onepercent.ParkingLotApplication.service.ParkingLotService;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Dylan Wei
 * @date 2018-08-01 16:14
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ParkingLotController.class)
@AutoConfigureMockMvc(secure=false)
public class ParkingLotControllerTest {
    @MockBean
    private ParkingLotService parkingLotService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private static final String prefix = "/parkinglots/";

    @Test
    public void should_get_204_when_add_parking_lot_successfully() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName("停车场1");
        parkingLot.setTotalSize(100);
        mockMvc.perform(
                post(prefix).content(mapper.writeValueAsString(parkingLot))
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().is(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void should_get_403_when_add_OperationNotAllowedExcepton_thrown() throws Exception {
        OperationNotAllowedException exception =
                new OperationNotAllowedException();
        doThrow(exception).when(this.parkingLotService).addParkingLot(any(ParkingLot.class));

        mockMvc.perform(
                post(prefix).content(mapper.writeValueAsString(
                        new ParkingLot())).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(HttpStatus.SC_FORBIDDEN))
                .andExpect(content().string(exception.getMessage()));
    }

    @Test
    public void should_get_specefic_parkinglot_given_valid_id() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        when(this.parkingLotService.getParkingLotById(anyLong())).thenReturn(parkingLot);

        mockMvc.perform(get(prefix + anyLong())
        .content(mapper.writeValueAsString(parkingLot))
        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(parkingLot)));
    }

    @Test
    public void should_get_404_given_id_not_exists() throws Exception {
        ResourceNotFoundException exception =
                new ResourceNotFoundException();
        doThrow(exception).when(this.parkingLotService).getParkingLotById(anyLong());

        mockMvc.perform(get(prefix + anyLong())
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(mapper.writeValueAsString(new ParkingLot())))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_get_parkinglots_paging() throws Exception {
        List<ParkingLot> list = new ArrayList<>();
        when(this.parkingLotService.getParkingLotsPaging(any())).thenReturn(list);

        mockMvc.perform(
                get(prefix + "?page=" + 1 + "&size=" + 9)
        ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(list)));
    }

    @Test
    public void should_get_404_when_page_out_of_range() throws Exception {
        doThrow(ResourceNotFoundException.class).when(this.parkingLotService).getParkingLotsPaging(any());

        mockMvc.perform(
                get(prefix + "?page=" + 1 + "&size=" + 2)
        ).andExpect(status().isNotFound());

    }



}