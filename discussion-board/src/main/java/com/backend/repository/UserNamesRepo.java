package com.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Username;

public interface UserNamesRepo extends JpaRepository<Username, Integer> {
	public List<Username> findLast10ByOrderByIdDesc();
}
