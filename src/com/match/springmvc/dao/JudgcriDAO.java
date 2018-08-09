package com.match.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.match.springmvc.entities.Judgcri;

@Repository
public class JudgcriDAO implements IJudgcriDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Judgcri> findAllJudgcri() {
		// TODO Auto-generated method stub
		String hql = "from Judgcri";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Judgcri> list = query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<Judgcri> findJudgcriById(Integer Id) {
		// TODO Auto-generated method stub
		String hql = "from Judgcri j where j.Id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0,Id);
		
		List<Judgcri> list = query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public boolean updateJudgcriById(Judgcri judgcri) {
		// TODO Auto-generated method stub
		String hql="update Judgcri set Award=?,Affirmation=?,Cricredit=?,Cribonus=?,Criworkload=? where Id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		query.setString(0, judgcri.getAward());
		query.setString(1,judgcri.getAffirmation());
		query.setDouble(2,judgcri.getCricredit());
		query.setDouble(3,judgcri.getCribonus());
		query.setDouble(4,judgcri.getCriworkload());
		query.setInteger(5,judgcri.getId());
		
		if(query.executeUpdate()>0) {
			return true;
		}
		return false;
	}
}
