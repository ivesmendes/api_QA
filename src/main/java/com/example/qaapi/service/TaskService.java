package com.example.qaapi.service;

import com.example.qaapi.model.Task;
import com.example.qaapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> findAll() {
        return repo.findAll();
    }

    public Optional<Task> findById(Long id){
        return repo.findById(id);
    }

    public Task create(Task task) {
        return repo.save(task);
    }

    public  Optional<Task> update(Long id, Task task){
        return repo.findById(id).map(existing -> {
            existing.setTitle(task.getTitle());
            existing.setDone(task.isDone());
            return repo.save(existing);
        });
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
