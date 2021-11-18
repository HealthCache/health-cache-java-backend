package com.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
		when(sDao.getById(subject.getSubjectId())).thenReturn(subject);
		assertThat(sServ.getSubjectById(anyInt())).isEqualTo(subject);
	}
	
	@Test
	void getSubjectsByUser() {
		Username username = new Username();
		List<Subject> list = new ArrayList<>();
		when(sDao.findByUserId(username.getUsernameId())).thenReturn(list);
		assertThat(sServ.getSubjectsByUser(username)).isEqualTo(list);
	}
	
	@Test
	void createSubject() {
		Subject subject = new Subject();
		when(sDao.save(subject)).thenReturn(subject);
		assertThat(sServ.createSubject(subject)).isEqualTo(subject);
	}
	
	@Test
	void deleteSubjectTest() {
		Subject subject = new Subject();
		doNothing().when(sDao).delete(subject);
		assertThat(sServ.deleteSubject(subject)).isEqualTo(true);
	}
	
	@Test
	void updateSubjectTest() {
		Subject subject = new Subject();
		when(sDao.save(subject)).thenReturn(subject);
		assertThat(sServ.updateSubject(subject)).isEqualTo(subject);
	}
	
}
