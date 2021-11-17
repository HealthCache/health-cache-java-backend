package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Message;

public interface MessageRepo extends JpaRepository<Message, Integer> {

}
