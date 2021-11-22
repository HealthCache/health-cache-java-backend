package com.discussionboard.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.discussionboard.model.Username;

public interface UserNamesRepo extends JpaRepository<Username, Integer> {
	public List<Username> findLast10ByOrderByIdDesc();
}
