package com.example.HWSpring5.controllers;

import com.example.HWSpring5.services.TaskDataService;
import com.example.HWSpring5.services.TaskStatusService;
import com.example.HWSpring5.domain.Status;
import com.example.HWSpring5.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    @Autowired
    TaskStatusService statusService;
    @Autowired
    TaskDataService taskService;

    @PostMapping("/add")
    public Task addTask(@RequestBody Task task){
        taskService.addTask(task);
        update();

        return statusService.getRepo().findById(task.getId()).get();
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable Status status){
        return taskService.getTasksByStatus(status);
    }

    @PutMapping("/{id}")
    public Task updateTaskStatus(@PathVariable Long id, @RequestBody Task task){
        Task temp = statusService.updateStatusById(id, task.getStatus());
        update();
        return temp;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTaskById(id);
        update();
    }

    private void update(){
        statusService.checkStatus();
    }
}
