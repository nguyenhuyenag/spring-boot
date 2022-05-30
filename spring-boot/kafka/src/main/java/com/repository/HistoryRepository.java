package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
	
	boolean existsByMaThongDiep(String mathongdiep);
	
}
