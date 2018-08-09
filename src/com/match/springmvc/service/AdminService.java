package com.match.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.springmvc.dao.IAdminDAO;
import com.match.springmvc.entities.Admin;
import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Users;

@Service
/*@Transactional*/
public class AdminService implements IAdminService {
	
	@Autowired
	private IAdminDAO adminDAO;
	
	@Override
	public List<Admin> findAdmin(String Adid, String Adpwd) {
		// TODO Auto-generated method stub
		System.out.println("AdminService:" + Adid);
		return adminDAO.findAdmin(Adid, Adpwd);
	}
	/**
	 * 添加管理员
	 */
	@Override
	public void insertUser(Admin admin) {
		// TODO Auto-generated method stub
		System.out.println("添加管理员:" + admin.getAdid());
		adminDAO.insertAdmin(admin);
	}
	// 查找所有竞赛
	@Override
	public List<Competition> findAllCompetition() {
		// TODO Auto-generated method stub
		System.out.println("查找所有竞赛");
		return adminDAO.findAllCompetition();
	}
	@Override
	public boolean updateAdminPwd(Admin admin) {
		// TODO Auto-generated method stub
		return adminDAO.updateAdminPwd(admin);
	}
	@Override
	public List<Users> findAllUsers() {
		// TODO Auto-generated method stub
		return adminDAO.findAllUsers();
	}
	@Override
	public boolean updateUspwdByUsid(String Usid) {
		// TODO Auto-generated method stub
		return adminDAO.updateUspwdByUsid(Usid);
	}
	
	
}
