package com.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.model.Subject;
import com.backend.repository.SubjectRepo;
import com.backend.service.SubjectService;

public class SubjectServiceTest {
	
	private SubjectRepo sDao;
	private SubjectService sServ;
	
	@BeforeEach
	void setup() {
		sDao = mock(SubjectRepo.class);
		sServ = new SubjectService(sDao);
	}
	
	@Test
	void getAllSubjectsTest() {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		Subject subject = new Subject();
		
		subjects.add(subject);
		
		when(sDao.findAll()).thenReturn(subjects);
		
		ArrayList<Subject> response = (ArrayList<Subject>) sServ.getAllSubjects();
		
		assertThat(response.size()).isGreaterThan(0);
		
	}
	
}
