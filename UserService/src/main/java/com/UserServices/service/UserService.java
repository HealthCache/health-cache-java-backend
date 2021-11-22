package com.UserServices.service;

import org.springframework.stereotype.Service;

import com.UserServices.model.User;
import com.UserServices.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

	private UserRepo uDao;
	
	public boolean registerUser(User u)
	{
		//System.out.println(u);
		try {
			uDao.save(u);
			return true;
		} catch (Exception e) {
			System.out.println(u);
			return false;
		}
	}
	
	public User loginUser(String username, String password) {
		User u = uDao.findByUsername(username);
		if(u == null) {
			return null;
		} else {
			if(!u.getPassword().equals(password)) {
				return null;
			}
			else {
				return u;
			}
		}
	}



//	User Profile Services Starts Here
public User save(User user) {
	return uDao.save(user);

}

	public Optional<User> findById(Long id) {
		return uDao.findById(id);
	}

	public List<User> findAll() {
		return (List<User>) uDao.findAll();
	}


	public User update(User user) {
		return uDao.save(user);
	}

	public void deleteById(Long id) {
		uDao.deleteById(id);
	}

	public void delete(User user) {
		uDao.delete(user);
	}

	public User findByEmail(String email) {
		return uDao.findByEmail(email);
	}

	public User findByUsername(String username) {
		return uDao.findByUsername(username);
	}

	public User getUser(Long id) {
		User user = findAll().stream()
				.filter(t -> id.equals(t.getUser_id()))
				.findFirst()
				.orElse(null);
		return user;
	}
}
