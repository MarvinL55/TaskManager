package com.marvin.taskmanager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;



@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TaskService(TaskRepository taskRepository, MongoTemplate mongoTemplate) {
        this.taskRepository = taskRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id){
        return taskRepository.findById(id);
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(String id, Task updatedTask){
        updatedTask.setId(id);
        return taskRepository.save(updatedTask);
    }

    public boolean deleteTask(String id) {
        try {
            taskRepository.deleteById(id);
            return true; // Deletion was successful
        } catch (Exception e) {
            return false; // Deletion failed
        }
    }
    
    public Task toggleTaskComplete(String id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setCompleted(!task.isCompleted());
            return taskRepository.save(task);
        }
        return null;
    }
}
