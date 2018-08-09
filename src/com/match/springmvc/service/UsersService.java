package com.match.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.springmvc.dao.IUsersDAO;
import com.match.springmvc.entities.Users;

@Service
public class UsersService implements IUsersService {
	
	@Autowired
	private IUsersDAO usersDAO;
	
	@Override
	public List<Users> findUser(String Usid, String Uspwd) {
		// TODO Auto-generated method stub
		return usersDAO.findUser(Usid, Uspwd);
	}
	
	@Override
	public void insertUser(Users users) {
		// TODO Auto-generated method stub
		usersDAO.insertUser(users);
	}
	
	@Override
	public boolean updateUserPwd(Users users) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean findUserByDepart(Users users) {
		// TODO Auto-generated method stub
		return usersDAO.findUserByDepart(users);
	}
	
}
