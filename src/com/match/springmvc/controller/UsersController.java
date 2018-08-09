package com.match.springmvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.match.springmvc.entities.Users;
import com.match.springmvc.service.IUsersService;

@RequestMapping("/users")
@Controller
public class UsersController {
	
	@Autowired
	IUsersService usersService;
	
	// 跳转 用户登录页
	@RequestMapping("/Tzuserlogin")
	public String Tzuserlogin() {
		return "Userlogin";
		
	}
	
	// 用户登录
	@RequestMapping("/Userlogin")
	public ModelAndView Userlogin(HttpSession session,Users users) {
		
		ModelAndView modelAndView=new ModelAndView();
		
		List<Users> userlist = usersService.findUser(users.getUsid(), users.getUspwd());
		if(userlist==null) {
			modelAndView.setViewName("Error");
			modelAndView.addObject("error", users.getUsid()+" 用户登录失败！");
			return modelAndView;
		}else {
			Users user1 = userlist.get(0);
			
			session.setAttribute("user", user1);
			session.setAttribute("username", user1.getUsid());
			session.setAttribute("userdepartment", user1.getUsdepartment());
			
			System.out.println(user1.getUsid()+"用户登录成功！"+" "+user1.getUsdepartment());
			modelAndView.setViewName("TeamInfoImport");
			
		}
		return modelAndView ;
	}
	// 跳转 用户注册
	@RequestMapping("/Tzreg")
	public String Tzreg() {
		return "Userregister";
	}
	// 跳转到 用户注册
	@RequestMapping("/register")
	public String register(Map<String,Object> map,Users users) {
		boolean flag = usersService.findUserByDepart(users);
		if(flag) {
			map.put("error", "该系已有人注册！不能再被注册！");
			return "Error";
		}else {
			usersService.insertUser(users);
			return "Userlogin";
		}
	}
	
	// 跳转 管理员登录
	@RequestMapping("/Tzadminlogin")
	public String Tzadminlogin() {
		return "redirect:/Login.jsp";
	}
	
	// 用户 注销 登录
	@RequestMapping("/UZhuXiaoDengLu")
	public String UZhuXiaoDengLu(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("username");
		session.removeAttribute("userdepartment");
		return "Userlogin";
	}
	
	// 跳转 导出查询（系）
	@RequestMapping("/DTzExportChaXun")
	public String DTzExportChaXun() {
		return "DExportChaXun";
	}
}
