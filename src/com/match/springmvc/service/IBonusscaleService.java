package com.match.springmvc.service;

import java.util.List;

import com.match.springmvc.entities.Bonusscale;

public interface IBonusscaleService {
	// 所有 奖金比例
	public List<Bonusscale> findAllBonusscale();
	// 查找 某个 奖金比例
	public List<Bonusscale> findBonusscaleById(Integer Id);
	// 根据 Id 更新 奖金比例
	public boolean updateBonusscaleById(Bonusscale bonusscale);
}
