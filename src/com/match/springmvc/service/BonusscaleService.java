package com.match.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.springmvc.dao.IBonusscaleDAO;
import com.match.springmvc.entities.Bonusscale;

@Service
public class BonusscaleService implements IBonusscaleService {
	
	@Autowired
	private IBonusscaleDAO bonusscaleDAO;
	
	@Override
	public List<Bonusscale> findAllBonusscale() {
		// TODO Auto-generated method stub
		return bonusscaleDAO.findAllBonusscale();
	}
	
	@Override
	public List<Bonusscale> findBonusscaleById(Integer Id) {
		// TODO Auto-generated method stub
		return bonusscaleDAO.findBonusscaleById(Id);
	}
	
	@Override
	public boolean updateBonusscaleById(Bonusscale bonusscale) {
		// TODO Auto-generated method stub
		return bonusscaleDAO.updateBonusscaleById(bonusscale);
	}
	
}
