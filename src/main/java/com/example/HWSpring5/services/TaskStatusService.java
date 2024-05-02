package com.example.HWSpring5.services;

import com.example.HWSpring5.domain.Status;
import com.example.HWSpring5.domain.Task;
import com.example.HWSpring5.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskStatusService {
    private ChronoUnit between = ChronoUnit.MINUTES;
    @Autowired
    private TaskRepository repo;

    public void checkStatus() {
        List<Task> temp = new ArrayList<>(repo.findAll());
        for (int i = 0; i < temp.size(); i++) {
            if(temp.get(i).getStatus() != Status.FINISH &&
                    between.between(temp.get(i).getTaskTime(), LocalDateTime.now()) > 0){
                temp.get(i).setStatus(Status.FAILED);
            }
        }

        //repo.clear();
        repo.saveAllAndFlush(temp);
    }

    public Task updateStatusById(Long id, Status status){
        repo.updateStatusById(status.toString(), id);

        return repo.findById(id).get();
    }

    public TaskRepository getRepo() {
        return repo;
    }
}
