package com.match.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.match.springmvc.entities.Bonusscale;

@Repository
public class BonusscaleDAO implements IBonusscaleDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Bonusscale> findAllBonusscale() {
		// TODO Auto-generated method stub
		String hql = "from Bonusscale";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		List<Bonusscale> list = query.list();
		
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public List<Bonusscale> findBonusscaleById(Integer Id) {
		// TODO Auto-generated method stub
		String hql = "from Bonusscale b where b.Id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0,Id);
		
		List<Bonusscale> list = query.list();
		if(list.size()!=0) {
			return list;
		}
		return null;
	}
	
	@Override
	public boolean updateBonusscaleById(Bonusscale bonusscale) {
		// TODO Auto-generated method stub
		String hql="update Bonusscale set Minnumber=?,Maxnumber=?,Scale=? where Id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		query.setInteger(0, bonusscale.getMinnumber());
		query.setInteger(1,bonusscale.getMaxnumber());
		query.setDouble(2,bonusscale.getScale());
		query.setInteger(3,bonusscale.getId());
		
		if(query.executeUpdate()>0) {
			return true;
		}
		return false;
	}
	
}
