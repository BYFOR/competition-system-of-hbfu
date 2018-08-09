package com.match.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.match.springmvc.entities.Judgcri;
import com.match.springmvc.service.IJudgcriService;

@RequestMapping("/criteria")
@Controller
public class CriteriaController {
	
	@Autowired
	IJudgcriService judgcriService;
	
	@RequestMapping("/TzCriteriaGuaiLi")
	public String TzCriteriaGuaiLi(Map<String,Object> map) {
		List<Judgcri> judgcrilist = judgcriService.findAllJudgcri();
		map.put("status", "all");
		map.put("alljudgcrilist", judgcrilist);
		return "CriteriaGuanLi";
	}
	
	// 跳转->修改评判标准
	@RequestMapping("/TzXiuGaiCriteria")
	public String TzXiuGaiCriteria(Map<String,Object> map,@RequestParam(value="Id")Integer Id) {
		List<Judgcri> judgcrilist = judgcriService.findJudgcriById(Id);
		map.put("updatejudgcri", judgcrilist);
		return "XiuGaiCriteria";
	}
	
	// 跳转到->修改评判标准
	@RequestMapping("/TzdXiuGaiCriteria")
	public String TzdXiuGaiCriteria(Judgcri judgcri) {
		boolean flag = judgcriService.updateJudgcriById(judgcri);
		if(flag) {
			return "redirect:/criteria/TzCriteriaGuaiLi";
		}else {
			return "redirect:/criteria/TzXiuGaiCriteria?Id="+judgcri.getId()+"";
		}
		
	}
}
