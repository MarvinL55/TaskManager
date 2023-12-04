package com.marvin.taskmanager;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    void deleteById(String id);

    List<Task> findAll();

    Optional<Task> findById(String id);
}
