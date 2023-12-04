package com.marvin.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080/api/tasks") // Replace with the actual URL of your frontend
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Get all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTask();
    }

    // Get a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        Optional<Task> existingTask = taskService.getTaskById(id);
        
        if (existingTask.isPresent()) {
            Task savedTask = taskService.updateTask(id, updatedTask);
            return ResponseEntity.ok(savedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        boolean deletedSuccessfully = taskService.deleteTask(id);
    
        if (deletedSuccessfully) {
            // If the task is deleted successfully, return no content
            return ResponseEntity.noContent().build();
        } else {
            // If the task is not found, return not found response
            return ResponseEntity.notFound().build();
        }
    }
    

    @PutMapping("/{id}/toggle")
public ResponseEntity<Task> toggleTaskComplete(@PathVariable String id){
    Task toggledTask = taskService.toggleTaskComplete(id);

    if (toggledTask != null && toggledTask.isCompleted()) {
        // If the task is marked as complete, delete it
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    } else if (toggledTask != null) {
        // If the task is not marked as complete, return the updated task
        return ResponseEntity.ok(toggledTask);
    } else {
        // If the task is not found, return not found response
        return ResponseEntity.notFound().build();
    }
}

}
