package com.onepercent.ParkingLotApplication.controller;

import com.onepercent.ParkingLotApplication.domain.Leaving;
import com.onepercent.ParkingLotApplication.service.LeavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-08-06 2:51
 */
@RestController
@RequestMapping("/leavings")
public class LeavingController {
    @Autowired
    private LeavingService leavingService;

    @GetMapping
    public List<Leaving> getAllLeavings(Leaving leaving){
        List<Leaving> leavings = this.leavingService.getAllLeavings(leaving);
        return leavings;
    }

    @PostMapping
    public Leaving createLeaving(
            @RequestBody Leaving leaving){
        return this.leavingService.addLeaving(leaving);
    }

    @GetMapping("/{leavingId}")
    public Leaving getLeavingById(@PathVariable Long leavingId){
        return this.leavingService.getLeavingById(leavingId);
    }

    @PatchMapping("/{leavingId}")
    public Leaving updateLeaingStatus(@PathVariable Long leavingId,
                                      @RequestBody Leaving leaving){
        leaving.setId(leavingId);
        return this.leavingService.setLeavingRequestStatus(leaving);
    }


}
