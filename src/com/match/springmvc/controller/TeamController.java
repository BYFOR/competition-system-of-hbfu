package com.match.springmvc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Team;
import com.match.springmvc.service.ICompetitionService;
import com.match.springmvc.service.ITeamService;

@RequestMapping("/team")
@Controller
public class TeamController {
	
	@Autowired
	ITeamService teamService;
	@Autowired
	ICompetitionService competitionService;
	
	// 跳转 队伍信息 导入 页面
	@RequestMapping("/TzdTeamInfoImport")
	public String TzdTeamInfoImport() {
		return "TeamInfoImport";
	}
	
	// 响应 Excel上传 调用方法 解析Excel
	@RequestMapping("/TeamInfoImportHandle")
	public String TeamInfoImportHandle(@RequestParam(value="filename") MultipartFile file,
	                                   HttpServletRequest request,HttpServletResponse response) {
		// 判断文件是否为空
		if(file == null)
			return null;
		// 获取文件名
		String filename = file.getOriginalFilename();
		// 进一步判断文件是否为空（即判断其大小是否为0或名称是否为null）
		long filesize = file.getSize();
		if(filename==null||("").equals(filename)&&filesize==0)
			return null;
		
		
		// 批量导入。参数：文件名，文件。
		boolean flag = teamService.insertBatchTeamInfo(filename, file);
		
		if(flag) {
			String filemsg = "批量导入Excel成功！";
			request.getSession().setAttribute("filemsg", filemsg);
			
		}else {
			String filemsg = "批量导入Excel失败！（请检查该张表参赛信息是否已导入或表中参赛信息有误！）";
			request.getSession().setAttribute("filemsg", filemsg);
		}
		
		return "TeamInfoImport";
	}
	
	// 跳转 修改队伍 页面
	@RequestMapping("/TzXiuGaiTeam")
	public String TzXiuGaiTeam(Map<String,Object> map,@RequestParam(value="Teamid") String Teamid) {
		System.out.println("修改队伍信息："+Teamid);
		List<Team> teaminfo = teamService.findTeamByTeamid(Teamid);
		map.put("updateteaminfo", teaminfo);
		return "XiuGaiTeam";
	}
	
	// 跳转到 修改队伍 页面
	@RequestMapping("/TzdXiuGaiTeam")
	public String TzdXiuGaiTeam(Team team,@RequestParam(value="Compid") Integer Compid) {
		boolean flag =false;
		List<Team> teamlist = teamService.findTeamByTeamid(team.getTeamid());
		System.out.println("flag---队伍编号----:"+team.getTeamid()+"--获奖等级--"+team.getAwlevel()+"--队伍竞赛编号--"+Compid);
		Team updateteam = teamlist.get(0);
		updateteam.setAwlevel(team.getAwlevel());
		
		List<Competition> complist = competitionService.findCompetitionById(Compid);
		updateteam.setCompetition(complist.get(0));
		
		System.out.println("获奖级别："+team.getAwlevel()+" 队伍竞赛："+updateteam.getCompetition());
		
		flag = teamService.updateTeamByTeamid(updateteam);
		System.out.println("-flag-:"+flag);
		
		if(flag) {
			return "redirect:/team/TzdTeamGuanLi?Teamid="+team.getTeamid()+"";
		}else {
			return "redirect:/team/TzXiuGaiTeam?Teamid="+team.getTeamid()+"";
		}
	}
	
	// 新建队伍
//	@RequestMapping("/XinjianTeam")
//	public String XinjianTeam(Team team) {
//		System.out.println("队伍Team 的competition 的Compid："+team.getCompetition().getCompid());
//		List<Competition> singleteamcomp = competitionService.findCompetitionById(team.getCompetition().getCompid());
//		team.setCompetition(singleteamcomp.get(0));
//		teamService.insertTeam(team);
//		return "TeamChaXun"; // 新建队伍成功->竞赛查询页面
//	}
	
	// 删除队伍
	@RequestMapping("/TzdDeleteTeam")
	public String TzdDeleteTeam(Map<String,Object> map,@RequestParam(value="Teamid") String Teamid,@RequestParam(value="Id")Integer Id) {
		boolean flag = teamService.deleteTeamByTeamid(Teamid);
		System.out.println("重定向(跳转到另一个Controller)："+Id);
		if(flag) {
			return "redirect:/competition/TzdBiSaiXinXi?Id="+Id+""; //重定向：problem：Required Integer parameter 'Id' is not present
		}else {
			return "redirect:/competition/TzdDeleteTeam?Teamid="+Teamid+"&Id="+Id+"";
		}
	}
	
	// 跳转 队伍查询 页面
	@RequestMapping("/TzTeamChaXun")
	public String TzTeamChaXun() {
		return "TeamChaXun";
	}
	
	// 跳转到 队伍查询页面
	@RequestMapping("/TzdTeamGuanLi")
	public String TzdTeamGuanLi(Map<String,Object> map,@RequestParam(value="Teamid") String Teamid) {
		System.out.println("队伍查询-Teamid："+Teamid);
		List<Team> singleteam = teamService.findTeamByTeamid(Teamid);
		map.put("singleteam", singleteam);
		return "TeamGuanLi";
	}
	
}
