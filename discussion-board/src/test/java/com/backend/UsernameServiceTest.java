package com.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

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
}
