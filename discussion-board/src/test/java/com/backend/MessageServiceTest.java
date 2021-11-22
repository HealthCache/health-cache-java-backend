package com.backend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.backend.model.Message;
import com.backend.model.Subject;
import com.backend.model.Username;
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
	void getAllMessagesTest() {
		ArrayList<Message> messages = new ArrayList<Message>();
		Message message = new Message(); 
		
		messages.add(message);
		
		when(mDao.findAll()).thenReturn(messages);
		
		ArrayList<Message> response = (ArrayList<Message>) mServ.getAllMessages();
		
		assertThat(response.size()).isGreaterThan(0);
		
	}
	
	@Test
	void getMessageByIdTest() {
		Message message = new Message();
		when(mDao.findById(1)).thenReturn(Optional.of(message));
		assertThat(mServ.getMessageById(1)).isEqualTo(message);
	}
	
	@Test
	void getMessageByUserTest() {
		List<Message> list = new ArrayList<>();
		when(mDao.findByUsernameId(1)).thenReturn(list);
		assertThat(mServ.getMessagesByUser(new Username())).isEqualTo(list);
	}
	
	@Test
	void getMessagesBySubjectTest() {
		List<Message> list = new ArrayList<>();
		when(mDao.findBySubjectId(1)).thenReturn(list);
		assertThat(mServ.getMessagesBySubject(new Subject())).isEqualTo(list);
	}
	
	@Test
	void createMessageTest() {
		Message message = new Message();
		when(mDao.save(any(Message.class))).thenReturn(message);
		assertThat(mServ.createMessage(message)).isEqualTo(message);
	}
	
	@Test
	void deleteMessageTest() {
		Message message = new Message();
		Optional<Message> op = Optional.of(message);
//		doNothing().when(mDao).delete(any(Message.class));
		when(mDao.findById(anyInt())).thenReturn(op);
		assertThat(mServ.deleteMessage(message)).isEqualTo(true);
	}
	
	@Test
	void updateMessageTest() {
		Message message = new Message();
		Optional<Message> op = Optional.of(message);
		when(mDao.findById(anyInt())).thenReturn(op);
		when(mDao.save(any(Message.class))).thenReturn(message);
		assertThat(mServ.updateMessage(message)).isEqualTo(message);
	}
}
