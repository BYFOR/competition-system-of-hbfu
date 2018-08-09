package com.match.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.match.springmvc.entities.Bonusscale;
import com.match.springmvc.service.IBonusscaleService;

@RequestMapping("/bonusscale")
@Controller
public class BonusscaleController {
	
	@Autowired
	IBonusscaleService bonusscaleService;
	
	@RequestMapping("/TzBonusscaleGuanLi")
	public String TzCriteriaGuaiLi(Map<String,Object> map) {
		List<Bonusscale> bonusscalelist = bonusscaleService.findAllBonusscale();
		map.put("status", "all");
		map.put("allbonusscalelist", bonusscalelist);
		return "BonusscaleGuanLi";
	}
	
	// 跳转->修改奖金比例
	@RequestMapping("/TzXiuGaiBonusscale")
	public String TzXiuGaiBonusscale(Map<String,Object> map,@RequestParam(value="Id")Integer Id) {
		List<Bonusscale> bonusscalelist = bonusscaleService.findBonusscaleById(Id);
		map.put("updatebonusscale", bonusscalelist);
		return "XiuGaiBonusscale";
	}
	
	// 跳转到->修改奖金比例
	@RequestMapping("/TzdXiuGaiBonusscale")
	public String TzdXiuGaiBonusscale(Bonusscale bonusscale) {
		boolean flag = bonusscaleService.updateBonusscaleById(bonusscale);
		if(flag) {
			return "redirect:/bonusscale/TzBonusscaleGuanLi";
		}else {
			return "redirect:/bonusscale/TzXiuGaiBonusscale?Id="+bonusscale.getId()+"";
		}
		
	}
}
