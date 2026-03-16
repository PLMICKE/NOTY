package com.noty.service;

import com.noty.model.Action;
import com.noty.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActionService {

    @Autowired
    private ActionRepository actionRepository;

    public List<Action> findAll() {
        return actionRepository.findAll();
    }

    public Optional<Action> findById(int id) {
        return actionRepository.findById(id);
    }

    public Action save(Action action) {
        return actionRepository.save(action);
    }

    public void deleteById(int id) {
        actionRepository.deleteById(id);
    }
}
