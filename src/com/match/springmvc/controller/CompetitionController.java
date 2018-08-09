package com.match.springmvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.match.springmvc.data.CompetitionInfo;
import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Student;
import com.match.springmvc.entities.Teacher;
import com.match.springmvc.entities.Team;
import com.match.springmvc.service.IAdminService;
import com.match.springmvc.service.ICompetitionService;

@RequestMapping("/competition")
@Controller
public class CompetitionController {
	
	@Autowired
	IAdminService adminService;
	@Autowired
	ICompetitionService competitionService;
	
	
	// 新建竞赛
	@RequestMapping("/XinjianJingSai")
	public String XinjianJingSai(Map<String,Object> map,Competition competition) {
		List<Competition> complist = competitionService.findCompetitionById(competition.getCompid());
		if(complist!=null) {
			map.put("error", "该竞赛编号已存在！请重新输入竞赛编号！");
			return "Error";
		}else {
			competitionService.insertCompetition(competition);
			return "redirect:/admin/TzMain";
		}
	}
	
	// 根据竞赛编号 删除竞赛
	@RequestMapping("/ShanChuJingSai")
	public String ShanChuJingSai(Map<String,Object> map,@RequestParam(value="Compid") Integer Compid) {
		boolean flag = competitionService.deleteCompetitionByCompId(Compid);
		if(flag) {
			List<Competition> allcomp = adminService.findAllCompetition();
			map.put("allcomp", allcomp);
			return "redirect:/admin/TzMain";
		}else {
			map.put("error", "删除竞赛失败！");
			return "Error";
		}
	}
	
	@RequestMapping("/ShanChuJS")
	public String ShanChuJS(Map<String,Object> map,@RequestParam(value="Compid") Integer Compid) {
		boolean flag = competitionService.deleteCompetitionByCompId(Compid);
		if(flag) {
			List<Competition> allcomp = adminService.findAllCompetition();
			map.put("allcomp", allcomp);
			return "redirect:/competition/TzdJingSaiGuanLi";
		}else {
			map.put("error", "删除竞赛失败！");
			return "Error";
		}
	}
	
	
	// 根据 竞赛名称 查询竞赛信息(表单形式)
	@RequestMapping("/TzdJingSaiGuanLi")
	public String TzdJingSaiGuanLi(Map<String,Object> map,String Compname) {
		System.out.println("测试 竞赛名称："+Compname);
		List<Competition> competitionlist = competitionService.findCompetitionByName(Compname);
		map.put("competitionlist", competitionlist);
		map.put("status", "single");
		return "JingSaiGuanLi";
	}
	
	// 跳转到比赛信息页面
	@RequestMapping("/TzdBiSaiXinXi")
	public String TzdBiSaiXinXi(Map<String,Object> map,@RequestParam(value="Id") Integer Id) {
		System.out.println("测试 比赛信息："+Id);
		CompetitionInfo compinfo = new CompetitionInfo();
		// 竞赛基本信息
		List<Competition> competitionlist = competitionService.findCompetitionByHouTianId(Id);
		// 参赛人数
		Long studentnum = competitionService.findStudentNumById(competitionlist.get(0).getCompid());
		System.out.println("参赛人数："+studentnum);
		// 参赛队伍数
		Long teamnum = competitionService.findTeamNum(competitionlist.get(0).getCompid());
		System.out.println("参赛队伍数："+teamnum);
		// 指导教师数
		Long teachernum = competitionService.findTeacherNum(competitionlist.get(0).getCompid());
		System.out.println("指导教师数："+teachernum);
		
		compinfo.setId(competitionlist.get(0).getId());
		compinfo.setCompid(competitionlist.get(0).getCompid());
		compinfo.setCompname(competitionlist.get(0).getCompname());
		compinfo.setSponsor(competitionlist.get(0).getSponsor());
		compinfo.setStudentNum(studentnum);
		compinfo.setTeamNum(teamnum);
		compinfo.setTeacherNum(teachernum);
		compinfo.setStarttime(competitionlist.get(0).getStarttime());
		compinfo.setEndtime(competitionlist.get(0).getEndtime());
		compinfo.setRemark(competitionlist.get(0).getRemark());
		map.put("compinfo", compinfo);
		
		List<Team> teamlist = competitionService.findTeamById(competitionlist.get(0).getCompid());
//		System.out.println("测试: 所有队伍"+teamlist.get(0).getSmens());
		map.put("citeamlist",teamlist);
		
		List<Student> stulist = competitionService.findStudentByCompId(competitionlist.get(0).getCompid());
//		System.out.println("测试: 所有学生"+stulist.get(0).getStuname());
		map.put("cistulist",stulist);
		
		List<Teacher> trlist = competitionService.findTeacherByCompId(competitionlist.get(0).getCompid());
//		System.out.println("测试: 所有教师"+trlist.get(0).getTrid());
		map.put("citrlist",trlist);
		
		return "BiSaiXinXi";
	}
	
	// 跳转到 修改 竞赛信息 页面
	@RequestMapping("/TzdXiuGaiJingSai")
	public String TzdXiuGaiJingSai(Map<String,Object> map,@RequestParam(value="Id")Integer Id) {
		System.out.println("修改的 竞赛信息 编号："+Id);
		// ①.查找出 对应 竞赛基本信息
		List<Competition> competitioninfo = competitionService.findCompetitionByHouTianId(Id);
		map.put("competitioninfo", competitioninfo);
		return "XiuGaiJingSai";
		
	}
	// 修改比赛信息
	@RequestMapping("/XiuGaiJingSai")
	public String XiuGaiJingSai(Map<String,Object> map,Competition competition) {
		System.out.println("修改 比赛信息 编号(Controller):"+competition.getId());
//		ModelAndView modelAndView=new ModelAndView();
		List<Competition> clist = competitionService.findCompetitionById(competition.getCompid());
		if(clist != null) {
			map.put("error", "该竞赛编号已存在，请重新输入要修改竞赛的竞赛编号！");
			return "Error";
		}else {
			boolean flag = competitionService.updateCompetition(competition);
			if(flag) {
//				modelAndView.setViewName("JingSaiChaXun");
//				return modelAndView;
				return "redirect:/competition/TzdBiSaiXinXi?Id="+competition.getId()+"";
			}else {
//				modelAndView.setViewName("XiuGaiJingSai");
//				modelAndView.addObject("competitioninfo", competition);
//				return modelAndView;
				map.put("competitioninfo", competition);
				return "XiuGaiJingSai";
			}
		}
	}
	
}
