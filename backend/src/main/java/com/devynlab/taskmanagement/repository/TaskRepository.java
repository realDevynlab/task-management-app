package com.devynlab.taskmanagement.repository;

import com.devynlab.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.deleted = false")
    List<Task> findAllNotDeleted();

    @Query("SELECT t FROM Task t WHERE t.id = :id AND t.deleted = false")
    Task findByIdNotDeleted(Long id);

}
