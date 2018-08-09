package com.match.springmvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.match.springmvc.entities.Admin;
import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Users;
import com.match.springmvc.service.IAdminService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Autowired
	IAdminService adminService;
	// 管理员登录
	@RequestMapping("/login")
	public ModelAndView login(HttpSession session,Admin admin) {
		ModelAndView modelAndView=new ModelAndView();
		System.out.println("空指针："+adminService);
		List<Admin> adminlist = adminService.findAdmin(admin.getAdid(), admin.getAdpwd());
		if(adminlist==null) {
			modelAndView.setViewName("Error");
			modelAndView.addObject("error", admin.getAdid()+" 管理员登录失败！");
			System.out.println(admin.getAdid()+"管理员登录失败！");
			return modelAndView;
		}else {
			modelAndView.setViewName("Main");
			Admin admin1 = null;
			for(Admin adminli:adminlist) {
				admin1 = adminli;
			}
			session.setAttribute("admin", admin1);
			session.setAttribute("adminname", admin1.getAdid());
			System.out.println(admin.getAdid()+"管理员登录成功！");
			
			// Main.jsp 显示 所有 竞赛信息
			List<Competition> allcomp = adminService.findAllCompetition();
			modelAndView.addObject("allcomp", allcomp);
			
		}
		return modelAndView ;
	}
	
	//管理员注册
	@RequestMapping("/Tzreg")
	public String Tzreg() {
		return "Register";
	}
	@RequestMapping("/register")
	public String register(Admin admin) {
		System.out.println("注册："+admin.getAdid());
		adminService.insertUser(admin);
		return "redirect:/Login.jsp";
	}
	// 返回登录页面
	@RequestMapping("/FanHuiLogin")
	public String FanHuiLogin() {
		return "redirect:/Login.jsp";
	}
	//注销登录
	@RequestMapping("/ZhuXiaoDengLu")
	public String ZhuXiaoDengLu(HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("adminname");
		return "redirect:/Login.jsp";
	}
	
	// 跳转到仪表盘Main.jsp
	@RequestMapping("/TzMain")
	public ModelAndView TzMain() {
		ModelAndView modelAndView=new ModelAndView();
		List<Competition> allcomp = adminService.findAllCompetition();
		modelAndView.setViewName("Main");
		modelAndView.addObject("allcomp", allcomp);
		
		return modelAndView;
	}
	// 跳转竞赛管理页面
	@RequestMapping("/TzJingSaiGuanLi")
	public String TzJingSaiGuanLi(Map<String,Object> map) {
		List<Competition> allcompetitionlist = adminService.findAllCompetition();
		map.put("allcompetitionlist", allcompetitionlist);
		map.put("status", "all");
		return "JingSaiGuanLi";
	}
	// 跳转新建竞赛页面
	@RequestMapping("/TzXinjianJingSai")
	public ModelAndView TzXinjianJingSai() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("XinjianJingSai");
		return modelAndView;
	}
	// 跳转竞赛查询页面
	@RequestMapping("/TzJingSaiChaXun")
	public ModelAndView TzJingSaiChaXun() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("JingSaiChaXun");
		return modelAndView;
	}
	// 管理员 修改密码
	@RequestMapping("/TzXiuGaiPwd")
	public String TzXiuGaiPwd(Map<String,Object> map,HttpSession session) {
		Admin ad = (Admin) session.getAttribute("admin");
		List<Admin> admininfo = adminService.findAdmin(ad.getAdid(), ad.getAdpwd());
		map.put("admininfo", admininfo);
		return "XiuGaiPwd";
	}
	
	// 修改密码
	@RequestMapping("/TzdXiuGaiPwd")
	public String TzdXiuGaiPwd(Admin admin) {
		boolean flag = adminService.updateAdminPwd(admin);
		if(flag) {
			return "redirect:/Login.jsp";
		}else {
			return "redirect:/admin/TzXiuGaiPwd";
		}
	}
	
	// 返回到information 页面
	@RequestMapping("/FanHuiInfo")
	public String FanHuiInfo(Map<String,Object> map) {
		map.put("information", "您无此权限！");
		return "Information";
	}
	
	// 跳转 查看用户
	@RequestMapping("/TzChaKanUsers")
	public String TzChaKanUsers(Map<String,Object> map) {
		List<Users> ulist = adminService.findAllUsers();
		map.put("alluserlist", ulist);
		map.put("status", "all");
		return "ChaKanUsers";
	}
	
	// 重置用户密码
	@RequestMapping("/TzdResetPwd")
	public String TzdResetPwd(Map<String,Object> map,@RequestParam(value="Usid") String Usid) {
		boolean flag = adminService.updateUspwdByUsid(Usid);
		if(flag) {
			return "redirect:/admin/TzChaKanUsers";
		}else {
			map.put("error", "重置 "+Usid+" 的密码失败！");
			return "Error";
		}
		
	}
	
}
