package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Username;

public interface UserNamesRepo extends JpaRepository<Username, Integer> {

}
