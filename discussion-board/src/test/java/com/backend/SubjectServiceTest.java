package com.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.model.Subject;
import com.backend.model.Username;
import com.backend.repository.SubjectRepo;
import com.backend.repository.UserNamesRepo;
import com.backend.service.SubjectService;


public class SubjectServiceTest {
	
	private SubjectRepo sDao;
	private UserNamesRepo uDao;
	private SubjectService sServ;
	
	@BeforeEach
	void setup() {
		sDao = mock(SubjectRepo.class);
		uDao = mock(UserNamesRepo.class);
		sServ = new SubjectService(sDao, uDao);
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
		when(sDao.findById(1)).thenReturn(Optional.of(subject));
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
	void getSubjectsByUserIdTest() {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		Subject subject = new Subject();
		Username username = new Username();
		
		subjects.add(subject);
		
		when(sDao.findByUsernameId(anyInt())).thenReturn(subjects);
		
		ArrayList<Subject> response = (ArrayList<Subject>) sServ.getSubjectsByUserId(1);
		
		assertThat(response.size()).isGreaterThan(0);
	}
	
	@Test
	void getSubjectsByUserTest() {
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		Subject subject = new Subject();
		Username username = new Username();
		
		subjects.add(subject);
		
		when(sDao.findByUsernameId(anyInt())).thenReturn(subjects);
		
		ArrayList<Subject> response = (ArrayList<Subject>) sServ.getSubjectsByUser(username);
		
		assertThat(response.size()).isGreaterThan(0);
	}
	
	@Test
	void voteSubject() {
		Subject subject = new Subject();
		Username user = new Username();
		when(uDao.findById(anyInt())).thenReturn(Optional.of(user));
		when(sDao.findById(anyInt())).thenReturn(Optional.of(subject));
		when(uDao.save(user)).thenReturn(user);
		when(sDao.save(subject)).thenReturn(subject);
		assertThat(sServ.voteSubject(1, 1)).isEqualTo(subject);
	}
	
	@Test
	void getLastTenOrderByIdTest() {
		Subject subject = new Subject();
		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject);
		when(sDao.findLast10ByOrderByIdDesc()).thenReturn(subjects);
		assertThat(sServ.getLastTenOrderById()).isNotEmpty();
	}

}
