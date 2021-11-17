package com.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.model.Message;
import com.backend.repository.MessageRepo;
import com.backend.service.MessageService;

public class MessageServiceTest {

	private MessageRepo mDao;
	private MessageService mServ;
	
			
	@BeforeEach
	void setup() {
		mDao = mock(MessageRepo.class);
		mServ = new MessageService(mDao);
	}
	
	@Test
	void getAllSubjectsTest() {
		ArrayList<Message> messages = new ArrayList<Message>();
		Message message = new Message();
		
		messages.add(message);
		
		when(mDao.findAll()).thenReturn(messages);
		
		ArrayList<Message> response = (ArrayList<Message>) mServ.getAllMessages();
		
		assertThat(response.size()).isGreaterThan(0);
		
	}
}
