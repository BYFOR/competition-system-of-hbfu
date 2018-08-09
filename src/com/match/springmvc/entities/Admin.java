package com.match.springmvc.entities;

import org.springframework.stereotype.Component;

/**
 * 管理员实体类
 * @author lenovo
 *
 */
@Component
public class Admin {
	
	private String Adid;
	private String Adpwd;
	
	public String getAdid() {
		return Adid;
	}
	public void setAdid(String adid) {
		Adid = adid;
	}
	public String getAdpwd() {
		return Adpwd;
	}
	public void setAdpwd(String adpwd) {
		Adpwd = adpwd;
	}
	
	
}
