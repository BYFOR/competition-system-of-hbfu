package com.match.springmvc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.match.springmvc.entities.Admin;
import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Users;

@Repository
public interface IAdminDAO {
	
	// 通过工号、密码查询管理员
	public List<Admin> findAdmin(String Adid,String password);
	
	// 添加管理员
	public void insertAdmin(Admin admin);
	
	// 未经竞赛查询 直接显示所有竞赛的信息
	public List<Competition> findAllCompetition();
	
	// 管理员 修改密码
	public boolean updateAdminPwd(Admin admin);
	
	// 查看所有用户
	public List<Users> findAllUsers();
	
	// 重置某用户密码
	public boolean updateUspwdByUsid(String Usid);
}
