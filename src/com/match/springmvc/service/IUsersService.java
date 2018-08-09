package com.match.springmvc.service;

import java.util.List;

import com.match.springmvc.entities.Users;

public interface IUsersService {
	// 查找 用户
	public List<Users> findUser(String Usid, String Uspwd);
	// 添加 用户
	public void insertUser(Users users);
	// 用户 修改密码
	public boolean updateUserPwd(Users users);
	// 检查 某系是否 已经有人注册过
	public boolean findUserByDepart(Users users);
	
}
