package com.match.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.springmvc.dao.IJudgcriDAO;
import com.match.springmvc.entities.Judgcri;

@Service
public class JudgcriService implements IJudgcriService {
	
	@Autowired
	private IJudgcriDAO judgcriDAO;
	
	@Override
	public List<Judgcri> findAllJudgcri() {
		// TODO Auto-generated method stub
		return judgcriDAO.findAllJudgcri();
	}
	
	@Override
	public List<Judgcri> findJudgcriById(Integer Id) {
		// TODO Auto-generated method stub
		return judgcriDAO.findJudgcriById(Id);
	}
	
	@Override
	public boolean updateJudgcriById(Judgcri judgcri) {
		// TODO Auto-generated method stub
		return judgcriDAO.updateJudgcriById(judgcri);
	}
	
}
