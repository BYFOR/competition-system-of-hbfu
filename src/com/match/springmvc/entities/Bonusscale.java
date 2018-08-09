package com.match.springmvc.entities;

import org.springframework.stereotype.Component;

/**
 * 奖金比例实体类
 * @author lenovo
 *
 */
@Component
public class Bonusscale {
	
	private Integer Id; // 自增主键
	
	private Integer Maxnumber; // 最大人数
	private Integer Minnumber; // 最低人数
	private Double Scale; // 奖金比例
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getMaxnumber() {
		return Maxnumber;
	}
	public void setMaxnumber(Integer maxnumber) {
		Maxnumber = maxnumber;
	}
	public Integer getMinnumber() {
		return Minnumber;
	}
	public void setMinnumber(Integer minnumber) {
		Minnumber = minnumber;
	}
	public Double getScale() {
		return Scale;
	}
	public void setScale(Double scale) {
		Scale = scale;
	}
}
