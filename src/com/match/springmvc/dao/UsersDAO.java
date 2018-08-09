package com.match.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.match.springmvc.entities.Users;

@Repository
public class UsersDAO implements IUsersDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Users> findUser(String Usid, String Uspwd) {
		// TODO Auto-generated method stub
		String hql = "from Users u where u.Usid=? and u.Uspwd=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,Usid);
		query.setString(1,Uspwd);
		List<Users> list = query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public void insertUser(Users users) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(users);
	}
	
	@Override
	public boolean updateUserPwd(Users users) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean findUserByDepart(Users users) {
		// TODO Auto-generated method stub
		String hql = "from Users u where u.Usdepartment=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,users.getUsdepartment());
		List<Users> list = query.list();
		
		if(list.size()!=0) {
			return true; // 该系已有人注册过！
		}
		return false;
	}
	
}
