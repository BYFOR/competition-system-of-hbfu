package com.match.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.match.springmvc.data.StudentInfo;
import com.match.springmvc.data.TeamInfoStu;
import com.match.springmvc.entities.Student;
import com.match.springmvc.service.IStudentService;

@RequestMapping("/student")
@Controller
public class StudentController {
	
	@Autowired
	IStudentService studentService;

	// 跳转 学生管理页面
	@RequestMapping("/TzXueShengGuanLi")
	public String TzdXueShengGuanLi(Map<String,Object> map) {
		List<StudentInfo> stuinfomana = studentService.countStuTpartNum();
//		System.out.println("测试：学生管理:"+stuinfo.get(2).getStuname());
		map.put("status", "all");
		map.put("stuinfomana", stuinfomana);
		return "XueShengGuanLi";		
	}
	
	// 跳转到 学生查询页面
	@RequestMapping("/TzdXueShengChaXun")
	public String TzdXueShengChaXun() {
		return "XueShengChaXun";
	}
	
	// 跳转 学生信息 页面 显示 学生 参加的 所有 队伍信息 + 个人所获学分、奖金信息
	@RequestMapping("/TzdXueShengXinXi")
	public String TzdXueShenXinXi(Map<String,Object> map,@RequestParam(value="Stuid") String Stuid,@RequestParam(value="Stuname") String Stuname) {
		// 编码 转换
		try {
			Stuname = new String(Stuname.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("测试:学生信息:"+Stuid+" "+Stuname);
		map.put("Stuname", Stuname);
//		List<Team> teaminfostu = studentService.findAllTeamByStuid(Stuid);
//		System.out.println("测试学生信息"+teaminfostu.get(1).getTmcname());
		List<TeamInfoStu> teaminfostu = studentService.findAllTeamByStuid(Stuid);
		map.put("teaminfostu", teaminfostu);
		return "XueShengXinXi";		
	}
	
	// 根据 学生学号   查询竞赛信息(表单形式)
	@RequestMapping("/TzdXueShengGuanLi")
	public String TzdXueShengGuanLi(Map<String,Object> map,String Stuid) {
		System.out.println("测试 学生学号："+Stuid);
		List<StudentInfo> singlestuinfo = studentService.findStuById(Stuid);
		map.put("status", "single");
		map.put("singlestuinfo", singlestuinfo);
		return "XueShengGuanLi";
	}
	
	// 跳转 学生信息 修改页
	@RequestMapping("/TzXiuGaiStuInfo")
	public String XiuGaiStuInfo(Map<String,Object> map,@RequestParam(value="Stuid")String Stuid) {
		List<Student> studentinfo = studentService.findStuInfoByStuid(Stuid);
		map.put("studentinfo", studentinfo);
		return "XiuGaiStuInfo";
		
	}
	
	// 跳转到 学生信息 修改页面
	@RequestMapping("/TzdXiuGaiStuInfo")
	public String TzdXiuGaiStuInfo(Student student) {
		boolean flag = studentService.updateStudent(student);
		String Stuid = student.getStuid();
		if(flag) {			
			return "redirect:/student/TzdXueShengGuanLi?Stuid="+Stuid+"";
		}else {
			return "redirect:/student/TzXiuGaiStuInfo?Stuid="+Stuid+"";
		}
				
	}
	
	// 根据学号 删除学生
	@RequestMapping("/ShanChuXueSheng")
	public String ShanChuXueSheng(Map<String,Object> map,@RequestParam(value="Stuid")String  Stuid) {
		System.out.println("删除---"+Stuid);
		boolean flag = studentService.deleteStudentByStuid(Stuid);
		if(flag) {
			List<StudentInfo> stuinfomana = studentService.countStuTpartNum();
			map.put("status", "all");
			map.put("stuinfomana", stuinfomana);
			return "XueShengGuanLi";
		}else {
			map.put("error", "删除学生失败！");
			return "Error";
		}
	}
}
