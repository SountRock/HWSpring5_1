package com.example.HWSpring5.services;

import com.example.HWSpring5.domain.Status;
import com.example.HWSpring5.domain.Task;
import com.example.HWSpring5.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskDataService {
    @Autowired
    private TaskRepository repo;

    public void addTask(Task t){
        repo.save(t);
    }

    public List<Task> getAllTasks(){
        return (List<Task>) repo.findAll();
    }

    public List<Task> getTasksByStatus(Status status){
        return repo.findByStatus(status);
    }

    public void deleteTaskById(Long id){
        repo.deleteById(id);
    }

    public TaskRepository getRepo() {
        return repo;
    }
}
