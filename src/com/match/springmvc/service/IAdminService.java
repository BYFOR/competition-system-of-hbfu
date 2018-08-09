package com.match.springmvc.service;

import java.util.List;

import com.match.springmvc.entities.Admin;
import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Users;

public interface IAdminService {
	// 查找管理员
	public List<Admin> findAdmin(String Adid, String Adpwd);
	// 添加管理员
	public void insertUser(Admin admin);
	// 查找所有竞赛
	public List<Competition> findAllCompetition();
	// 管理员 修改密码
	public boolean updateAdminPwd(Admin admin);
	// 查看所有用户
	public List<Users> findAllUsers();
	// 重置某用户密码
	public boolean updateUspwdByUsid(String Usid);
}
