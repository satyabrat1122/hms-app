package com.hms.implementation;

import com.hms.entity.State;
import com.hms.payloads.StateDto;
import com.hms.repository.StateRepository;
import com.hms.services.StateService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
   private StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public String addState(StateDto stateDto) {
        Optional<State> state = stateRepository.findStateByName(stateDto.getName());
        if(state.isPresent()){
            return "State already exists";

        }
        State state1 = new State();
        state1.setName(stateDto.getName());
        stateRepository.save(state1);
        return "State added successfully";


    }
}
