package com.match.springmvc.entities;

import org.springframework.stereotype.Component;

/**
 * 评判标准实体类
 * @author lenovo
 *
 */
@Component
public class Judgcri {
	
	private Integer Id; // 自增主键
	
	private String Award; // 获奖级别
	private String Affirmation; // 认定级别
	private Double Cricredit; // 对应学分
	private Double Cribonus; // 对应奖金
	private Double Criworkload; // 对应教师工作量
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getAward() {
		return Award;
	}
	public void setAward(String award) {
		Award = award;
	}
	public String getAffirmation() {
		return Affirmation;
	}
	public void setAffirmation(String affirmation) {
		Affirmation = affirmation;
	}
	public Double getCricredit() {
		return Cricredit;
	}
	public void setCricredit(Double cricredit) {
		Cricredit = cricredit;
	}
	public Double getCribonus() {
		return Cribonus;
	}
	public void setCribonus(Double cribonus) {
		Cribonus = cribonus;
	}
	public Double getCriworkload() {
		return Criworkload;
	}
	public void setCriworkload(Double criworkload) {
		Criworkload = criworkload;
	}
	
}
