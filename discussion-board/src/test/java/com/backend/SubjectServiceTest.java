package com.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.model.Subject;
import com.backend.model.Username;
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
	
	@Test
	void getSubjectByIdTest() {
		Subject subject = new Subject();
		when(sDao.getById(1)).thenReturn(subject);
		assertThat(sServ.getSubjectById(1)).isEqualTo(subject);
	}

	
	@Test
	void createSubject() {
		Subject subject = new Subject();
		when(sDao.save(any())).thenReturn(subject);
		assertThat(sServ.createSubject(subject)).isEqualTo(subject);
	}
	
	@Test
	void deleteSubjectTest() {
		Subject subject = new Subject();
		Optional<Subject> op = Optional.of(subject);
//		doNothing().when(sDao).delete(any());
		when(sDao.findById(anyInt())).thenReturn(op);
		assertThat(sServ.deleteSubject(subject)).isEqualTo(true);
	}
	
	@Test
	void updateSubjectTest() {
		Subject subject = new Subject();
		doReturn(Optional.of(subject)).when(sDao).findById(subject.getId());
		when(sDao.save(subject)).thenReturn(subject);
		assertThat(sServ.updateSubject(subject)).isEqualTo(subject);
	}
	
	
	@Test
	void getSubjectsByUser() {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		Subject subject = new Subject();
		Username username = new Username();
		
		subjects.add(subject);
		
		when(sDao.findByUsernameId(anyInt())).thenReturn(subjects);
		
		ArrayList<Subject> response = (ArrayList<Subject>) sServ.getSubjectsByUser(username);
		
		assertThat(response.size()).isGreaterThan(0);
	}
	
}
