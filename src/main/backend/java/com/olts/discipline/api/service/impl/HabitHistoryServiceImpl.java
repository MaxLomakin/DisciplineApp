package com.olts.discipline.api.service.impl;

import com.olts.discipline.api.repository.HabitHistoryRepository;
import com.olts.discipline.api.service.HabitHistoryService;
import com.olts.discipline.model.Habit;
import com.olts.discipline.model.HabitHistory;

import javax.annotation.Resource;

/**
 * OLTS on 20.08.2017.
 */
public class HabitHistoryServiceImpl implements HabitHistoryService {
    @Resource
    private HabitHistoryRepository repository;

    @Override
    public HabitHistory create(Habit originalHabit) {
        HabitHistory history = new HabitHistory();
        propagateHistory(originalHabit, history);
        return repository.save(history);
    }

    private void propagateHistory(Habit originalHabit, HabitHistory history) {
        history.setName(originalHabit.getName());
        history.setCount(originalHabit.getCount());
        history.setDifficulty(originalHabit.getDifficulty());
        history.setDescription(originalHabit.getDescription());
        history.setOriginalHabit(originalHabit);
    }
}