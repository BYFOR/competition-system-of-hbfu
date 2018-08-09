package com.match.springmvc.dao;

import java.util.List;

import com.match.springmvc.entities.Judgcri;

public interface IJudgcriDAO {
	
	// 所有 评判标准
	public List<Judgcri> findAllJudgcri();
	// 查找 某个 评判标准
	public List<Judgcri> findJudgcriById(Integer Id);
	// 根据 Id 更新 评判标准
	public boolean updateJudgcriById(Judgcri judgcri);
}
