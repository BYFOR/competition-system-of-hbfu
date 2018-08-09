package com.match.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.match.springmvc.data.TeacherInfo;
import com.match.springmvc.data.TeamInfoTr;
import com.match.springmvc.entities.Teacher;
import com.match.springmvc.service.ITeacherService;

@RequestMapping("/teacher")
@Controller
public class TeacherController {
	
	@Autowired
	ITeacherService teacherService;
	
	// 跳转 教师管理 页面
	@RequestMapping("/TzJiaoShiGuanLi")
	public String TzJiaoShiGuanLi(Map<String,Object> map) {
		List<TeacherInfo> trinfomana = teacherService.countTrTpartNum();
//		System.out.println("测试："+trinfomana.get(0).getTrname());
		map.put("status", "all");
		map.put("trinfomana", trinfomana);
		return "JiaoShiGuanLi";
	}
	
	// 跳转到 教师信息 页面
	@RequestMapping("/TzdJiaoShiXinXi")
	public String TzdJiaoShiXinXi(Map<String,Object> map,@RequestParam(value="Trid") String Trid,@RequestParam(value="Trname") String Trname) {
		// 编码 转换
		try {
			Trname = new String(Trname.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("测试:教师信息:"+Trid+" "+Trname);
		map.put("Trname", Trname);
//		List<Team> teaminfotr = teacherService.findAllTeamByTrid(Trid);
//		System.out.println("测试教师信息"+teaminfotr.get(0).getTmcname());
		List<TeamInfoTr> teaminfotr = teacherService.findAllTeamByTrid(Trid);
		map.put("teaminfotr", teaminfotr);
		return "JiaoShiXinXi";
	}
	
	// 跳转到 教师查询 页面
	@RequestMapping("/TzdJiaoShiChaXun")
	public String TzdJiaoShiChaXun() {
		return "JiaoShiChaXun";
	}
	
	// 根据 学生学号 和 姓名 查询竞赛信息(表单形式)
	@RequestMapping("/TzdJiaoShiGuanLi")
	public String TzdJiaoShiGuanLi(Map<String, Object> map, String Trid) {
		System.out.println("测试:教师工号：" + Trid);
		List<TeacherInfo> singletrinfo = teacherService.findTrById(Trid);
		map.put("status", "single");
		map.put("singletrinfo", singletrinfo);
		return "JiaoShiGuanLi";
	}
	
	// 根据 教师编号 修改 教师基本信息
	@RequestMapping("/TzXiuGaiTrInfo")
	public String TzXiuGaiTrInfo(Map<String,Object> map,@RequestParam(value="Trid") String Trid) {
		List<Teacher> teacherinfo = teacherService.findTrInfoByTrid(Trid);
		map.put("teacherinfo", teacherinfo);
		return "XiuGaiTrInfo";
	}
	
	// 跳转到 教师 基本信息 修改
	@RequestMapping("/TzdXiuGaiTrInfo")
	public String TzdXiuGaiTrInfo(Teacher teacher) {
		boolean flag = teacherService.updateTeacher(teacher);
		String Trid = teacher.getTrid();
		if(flag) {
			return "redirect:/teacher/TzdJiaoShiGuanLi?Trid="+Trid+"";
		}else {
			return "redirect:/teacher/TzXiuGaiTrInfo?Trid="+Trid+"";
		}
	}
	
	@RequestMapping("/ShanChuJiaoShi")
	public String ShanChuJiaoShi(Map<String,Object> map,@RequestParam(value="Trid")String  Trid) {
		System.out.println("删除---"+Trid);
		boolean flag = teacherService.deleteTeacherByTrid(Trid);
		if(flag) {
			List<TeacherInfo> trinfomana = teacherService.countTrTpartNum();
//		System.out.println("测试："+trinfomana.get(0).getTrname());
			map.put("status", "all");
			map.put("trinfomana", trinfomana);
			return "JiaoShiGuanLi";
		}else {
			map.put("error", "删除教师失败！");
			return "Error";
		}
	}
	
}
