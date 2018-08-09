package com.match.springmvc.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.match.springmvc.entities.Admin;
import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Users;

@Repository
public class AdminDAO implements IAdminDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	// 获取当前Session
	private Session getSession() {
		Session session;
		try {
			System.out.println("sessionFactory:"+sessionFactory);
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
			System.out.println("openSession:");
		}
		return session;
	}
	
	
	@Override
	public List<Admin> findAdmin(String Adid, String Adpwd) {
		// TODO Auto-generated method stub
		String hql = "from Admin a where a.Adid=? and a.Adpwd=?";
		Query query = this.getSession().createQuery(hql);
//		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0,Adid);
		query.setString(1,Adpwd);
		List<Admin> list = query.list();
//		this.getSession().close();
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public void insertAdmin(Admin admin) {
		// TODO Auto-generated method stub
		System.out.println("DAO：添加管理员(事务)"+sessionFactory.getCurrentSession());
		sessionFactory.getCurrentSession().save(admin);
	}
	
	
	@Override
	public List<Competition> findAllCompetition() {
		// TODO Auto-generated method stub
		List<Competition> list=null;
		String hql="from Competition c";
		System.out.println("DAO：查询所有竞赛(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		list=query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	
	@Override
	public boolean updateAdminPwd(Admin admin) {
		// TODO Auto-generated method stub
		String hql="update Admin set Adpwd=? where Adid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, admin.getAdpwd());
		query.setString(1, admin.getAdid());
		if(query.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public List<Users> findAllUsers() {
		// TODO Auto-generated method stub
		List<Users> list=null;
		String hql="from Users u";
//		System.out.println("DAO：查询所有竞赛(事务)"+sessionFactory.getCurrentSession());
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		list=query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	
	@Override
	public boolean updateUspwdByUsid(String Usid) {
		// TODO Auto-generated method stub
		String hql="update Users u set u.Uspwd=? where u.Usid=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, "111111");
		query.setString(1, Usid);
		if(query.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
	
}
