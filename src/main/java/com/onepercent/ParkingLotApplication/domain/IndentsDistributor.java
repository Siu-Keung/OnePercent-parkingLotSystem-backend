package com.onepercent.ParkingLotApplication.domain;

import com.onepercent.ParkingLotApplication.exception.OperationNotAllowedException;
import com.onepercent.ParkingLotApplication.repository.IndentRepository;
import com.onepercent.ParkingLotApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dylan Wei
 * @date 2018-08-02 16:02
 */
@Component
public class IndentsDistributor {
    private final ConcurrentHashMap<Long, Indent> indentsMap = new ConcurrentHashMap<>();

    @Autowired
    private IndentRepository indentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void initializeIndentsMap(){
        List<Indent> indentList = this.indentRepository.findByStatusEquals(IndentStatus.PENDING);
        indentList.stream().forEach(item -> this.indentsMap.put(item.getId(), item));
    }

    public synchronized void addIndent(Long indentId){
        Indent indent = this.indentRepository.findById(indentId).get();
        this.indentsMap.put(indentId, indent);
    }

    public synchronized Indent assignIndent(Long indentId, Integer coordinatorId){
//        1.判断指定订单是否存在，不存在则抛异常
        if(!this.indentsMap.containsKey(indentId))
            throw new OperationNotAllowedException("抢单失败！");
//        2.判断停车员是否存在，不存在则抛异常
        Optional<User> optional = this.userRepository.findById(coordinatorId);
        if(!optional.isPresent())
            throw new OperationNotAllowedException("停车员不存在！");

        Indent indent = this.indentsMap.get(indentId);
        indent.setCoordinatorId(coordinatorId);
        indent.setStatus(IndentStatus.ACCEPTED);
        Indent resultIndent =  this.indentRepository.saveAndFlush(indent);
        this.indentsMap.remove(indentId);
        return resultIndent;
    }



}
