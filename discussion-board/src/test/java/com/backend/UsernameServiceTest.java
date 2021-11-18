package com.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.model.Username;
import com.backend.repository.UserNamesRepo;
import com.backend.service.UsernamesService;

public class UsernameServiceTest {
	
	private UserNamesRepo uDao;
	private UsernamesService uServ;
	
	@BeforeEach
	void setup() {
		uDao = mock(UserNamesRepo.class);
		uServ = new UsernamesService(uDao);
	}

	@Test
	void getAllUsernamesTest() {
		ArrayList<Username> usernames = new ArrayList<Username>();
		Username username = new Username();
		
		usernames.add(username);
		
		when(uDao.findAll()).thenReturn(usernames);
		
		ArrayList<Username> response = (ArrayList<Username>) uServ.getAllUsernames();
		
		assertThat(response.size()).isGreaterThan(0);
		
	}
	
	@Test
	void getUsernameByIdTest() {
		Username username = new Username();
		
		when(uDao.getById(username.getId())).thenReturn(username);
		
		
		assertThat(uServ.getUsernameById(anyInt())).isEqualTo(username);
	}
	
	@Test
	void createUsernameTest() {
		Username username = new Username();
		when(uDao.save(username)).thenReturn(username);
		assertThat(uServ.createUsername(username)).isEqualTo(username);
	}
	
	@Test
	void updateUsernameTest() {
		Username username = new Username();		
		doReturn(Optional.of(username)).when(uDao).findById(username.getId());
		when(uDao.save(username)).thenReturn(username);
		assertThat(uServ.updateUsername(username)).isEqualTo(username);
	}
}
