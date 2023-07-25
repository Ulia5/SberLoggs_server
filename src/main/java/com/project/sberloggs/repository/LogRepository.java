package com.project.sberloggs.repository;

import com.project.sberloggs.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByLevel(String level);
    List<Log> findByDateIsContaining(String date);
}
