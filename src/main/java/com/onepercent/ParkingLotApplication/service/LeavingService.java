package com.onepercent.ParkingLotApplication.service;

import com.onepercent.ParkingLotApplication.domain.Leaving;
import com.onepercent.ParkingLotApplication.repository.LeavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Dylan Wei
 * @date 2018-08-06 2:51
 */
@Service
public class LeavingService {
    @Autowired
    private LeavingRepository leavingRepository;

    public List<Leaving> getAllLeavings(Leaving leaving){
        return this.leavingRepository.findAll(Example.of(leaving));
    }

    public Leaving addLeaving(Leaving leaving){
        leaving.setStatus("pending");
        leaving.setComment(null);
        leaving.setCreateDate(new Date());
        return this.leavingRepository.save(leaving);
    }

    public Leaving getLeavingById(Long id){
        return this.leavingRepository.findById(id).get();
    }

    public Leaving setLeavingRequestStatus(Leaving leaving){
        Leaving realLeaving = this.leavingRepository.findById(leaving.getId()).get();
        if(realLeaving.getTerminated() != null && realLeaving.getTerminated() == 1 )
            return realLeaving;
        realLeaving.setStatus(leaving.getStatus());
        realLeaving.setTerminated(1);
        return this.leavingRepository.save(realLeaving);
    }

}
