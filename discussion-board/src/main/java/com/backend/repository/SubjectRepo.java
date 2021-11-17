package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Subject;

public interface SubjectRepo extends JpaRepository<Subject, Integer> {

}
