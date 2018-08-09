package com.match.springmvc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.match.springmvc.data.CompetitionInfo;
import com.match.springmvc.data.StuBonus;
import com.match.springmvc.data.TrWorkload;
import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.Student;
import com.match.springmvc.entities.Teacher;
import com.match.springmvc.service.IAdminService;
import com.match.springmvc.service.ICompetitionService;
import com.match.springmvc.service.IStudentService;
import com.match.springmvc.service.ITeacherService;
import com.match.springmvc.tools.ExportExcel;

@RequestMapping("/export")
@Controller
public class ExportExcelController {
	
	@Autowired
	IAdminService adminService;
	@Autowired
	ICompetitionService competitionService;
	@Autowired
	IStudentService studentService;
	@Autowired
	ITeacherService teacherService;
	
	// 跳转 导出管理页面
	@RequestMapping("/TzExportGuanLi")
	public String TzExportGuanLi(Map<String,Object> map) {
		List<Competition> allcompetitionlist = adminService.findAllCompetition();
		map.put("status", "all");
		map.put("allcompetitionlist", allcompetitionlist);
		return "ExportGuanLi";
		
	}
	
	// 跳转 导出管理页面 单个竞赛
	@RequestMapping("/TzdExportGuanLi")
	public String TzdExportGuanLi(Map<String,Object> map,@RequestParam(value="Compid") Integer Compid) {
		List<Competition> competitionlist = competitionService.findCompetitionById(Compid);
		map.put("status", "single");
		map.put("competitionlist", competitionlist);
		return "ExportGuanLi";
		
	}
	// 导出 学生奖金表 根据 竞赛编号
	@RequestMapping("/downloadbonus")
	public String downloadbonus(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="Compid") Integer Compid,@RequestParam(value="Compname") String Compname)throws IOException{
		List<StuBonus> stubonuslist = studentService.findAllStuBonusById(Compid);
		if(stubonuslist!=null) {
			// 编码 转换
			try {
				Compname = new String(Compname.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String fileName = Compname+"学生奖金表";
			List<Map<String,Object>> list=createExcelRecordBonus(stubonuslist);
			
			String columnNames[] = {"学号","姓名","系别","班级","获奖级别","认定级别","奖金数","电话","银行卡号"}; // 列名
			String keys[] = {"stuid","stuname","sdepartment","sclass","awlevel","sclevel","bonus","stutele","stucard"}; // map 中的key
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				ExportExcel.createWorkBook(list,keys,columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
			return null;
		}else {
			String filemsg = "请先导入参赛信息！";
			request.getSession().setAttribute("filemsg", filemsg);
			return "redirect:/export/TzdExportGuanLi?Compid="+Compid+"";
		}
	}
	private List<Map<String, Object>> createExcelRecordBonus(List<StuBonus> sblist) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		listmap.add(map);
		StuBonus stubonus=null;
		for (int j = 0; j < sblist.size(); j++) {
			stubonus=sblist.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("stuid", stubonus.getStuid());
			mapValue.put("stuname",stubonus.getStuname());
			mapValue.put("sdepartment", stubonus.getStudepartment());
			mapValue.put("sclass", stubonus.getStuclass());
			mapValue.put("awlevel", stubonus.getAwlevel());
			mapValue.put("sclevel", stubonus.getSclevel());
			mapValue.put("bonus", stubonus.getBonus());
			mapValue.put("stutele", stubonus.getStutele());
			mapValue.put("stucard", stubonus.getStucard());
			listmap.add(mapValue);
		}
		return listmap;
	}
	
	// 导出 教师工作量表 根据 竞赛编号
	@RequestMapping("/downloadworkload")
	public String downloadworkload(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="Compid") Integer Compid,@RequestParam(value="Compname") String Compname)throws IOException{
		List<TrWorkload> trworkloadlist = teacherService.findAllTrWorkloadById(Compid);
		if(trworkloadlist !=null) {
			// 编码 转换
			try {
				Compname = new String(Compname.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String fileName = Compname+"教师工作量表";
			List<Map<String,Object>> list=createExcelRecordWorkload(trworkloadlist);
			
			String columnNames[] = {"工号","姓名","所在部门","工作量","电话"}; // 列名
			String keys[] = {"trid","trname","trdepartment","workload","trtele"}; // map 中的key
			
			ByteArrayOutputStream os = new ByteArrayOutputStream(); // 字节数组输出流在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组缓冲区中
			try {
				ExportExcel.createWorkBook(list,keys,columnNames).write(os);// Excel 文件生成后转化为字节流
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray(); // 创建一个新分配的字节数组。数组的大小和当前输出流的大小，内容是当前输出流的拷贝
			InputStream is = new ByteArrayInputStream(content);// 创建一个大小为content字节的缓冲区 // 字节数组输入流在内存中创建一个字节数组缓冲区，从输入流读取的数据保存在该字节数组缓冲区中
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");// 指明 要下载的类型 //response.setContentType(MIME)的作用是使客户端浏览器，区分不同种类的数据，并根据不同的MIME调用浏览器内不同的程序嵌入模块来处理相应的数据
			response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1")); // 告诉浏览器 文件的名字和类型 // 高速浏览器的已下载形式
			ServletOutputStream out = response.getOutputStream(); // 提供将二进制数据发送到客户端的输出流 // out用于输出字符流数据或者二进制的字节流数据
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is); // 缓冲字节输入流
				bos = new BufferedOutputStream(out); // 缓冲字节输出流
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) { // public int read(byte[] b,int off,int len); 从此字节输入流中给定偏移量处开始将各字节读取到指定的 byte 数组中。
					bos.write(buff, 0, bytesRead); // public void write(byte[] b,int off,int len); 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此缓冲的输出流。
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
			return null;
		}else {
			String filemsg = "请先导入参赛信息！";
			request.getSession().setAttribute("filemsg", filemsg);
			return "redirect:/export/TzdExportGuanLi?Compid="+Compid+"";
		}
	}
	private List<Map<String, Object>> createExcelRecordWorkload(List<TrWorkload> twlist) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		listmap.add(map);
		TrWorkload trworkload=null;
		for (int j = 0; j < twlist.size(); j++) {
			trworkload=twlist.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("trid",trworkload.getTrid());
			mapValue.put("trname",trworkload.getTrname());
			mapValue.put("trdepartment", trworkload.getTrdepartment());
//				mapValue.put("awlevel", trworkload.getAwlevel());
//				mapValue.put("sclevel", trworkload.getSclevel());
			mapValue.put("workload", trworkload.getWorkload());
			mapValue.put("trtele", trworkload.getTrtele());
			listmap.add(mapValue);
		}
		return listmap;
	}
	
	// 跳转 导出查询
	@RequestMapping("/TzExportChaXun")
	public String TzExportChaXun() {
		return "ExportChaXun";
	}
	
	// 跳转到 导出查询
	@RequestMapping("/TzdExportChaXun")
	public String TzdExportChaXun(Map<String,Object> map,@RequestParam(value="Compid") Integer Compid,@RequestParam(value="Department")String Department) {
		
		// 开始
		CompetitionInfo compinfo = new CompetitionInfo();
		// 竞赛基本信息
		List<Competition> competitionlist = competitionService.findCompetitionById(Compid);
		// 参赛人数
		Long studentnum = competitionService.findStudentNumByCompidDepart(Compid, Department);
		System.out.println("系参赛人数："+studentnum);
		// 参赛队伍数
//			Long teamnum = competitionService.findTeamNum(competitionlist.get(0).getCompid());
//			System.out.println("参赛队伍数："+teamnum);
		// 指导教师数
		Long teachernum = competitionService.findTeacherNumByCompidDepart(Compid, Department);
		System.out.println("系指导教师数："+teachernum);
		
		if(competitionlist==null) {
			map.put("error", "请输入正确的竞赛编号！");
			return "Error";
		}else {
			compinfo.setId(competitionlist.get(0).getId());
			compinfo.setCompid(competitionlist.get(0).getCompid());
			compinfo.setCompname(competitionlist.get(0).getCompname());
			compinfo.setSponsor(competitionlist.get(0).getSponsor());
			compinfo.setStudentNum(studentnum);
//				compinfo.setTeamNum(teamnum);
			compinfo.setTeacherNum(teachernum);
			compinfo.setStarttime(competitionlist.get(0).getStarttime());
			compinfo.setEndtime(competitionlist.get(0).getEndtime());
			compinfo.setRemark(competitionlist.get(0).getRemark());
			map.put("compinfo", compinfo);

//				List<Team> teamlist = competitionService.findTeamById(competitionlist.get(0).getCompid());
//				System.out.println("测试: 所有队伍"+teamlist.get(0).getSmens());
//				map.put("citeamlist",teamlist);
			
			List<Student> stulist = competitionService.findStudentByCompIdDepart(Compid, Department);
//				System.out.println("测试: 所有学生"+stulist.get(0).getStuname());
			map.put("cistulist",stulist);
			
			List<Teacher> trlist = competitionService.findTeacherByCompIdDepart(Compid, Department);
//				System.out.println("测试: 所有教师"+trlist.get(0).getTrid());
			map.put("citrlist",trlist);
			
			// 结束
			map.put("department", Department);
			return "ExportXinXi";
		}
	}
	
	// 导出 学生奖金表 根据 竞赛编号 系别
	@RequestMapping("/downloaddepartbonus")
	public String downloaddepartbonus(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(value="Compid") Integer Compid,@RequestParam(value="Compname") String Compname,@RequestParam(value="Department") String Department)throws IOException{
		String Depart = Department;
		// 编码 转换
		try {
			Compname = new String(Compname.getBytes("ISO8859-1"),"UTF-8");
			Department = new String(Department.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<StuBonus> stubonuslist = studentService.findAllStuBonusByIdDepart(Compid, Department);
		if(stubonuslist!=null) {
			String fileName = Department+Compname+"学生奖金表";
			List<Map<String,Object>> list=createExcelRecordBonus(stubonuslist);
			
			String columnNames[] = {"学号","姓名","系别","班级","获奖级别","认定级别","奖金数","电话","银行卡号"}; // 列名
			String keys[] = {"stuid","stuname","sdepartment","sclass","awlevel","sclevel","bonus","stutele","stucard"}; // map 中的key
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				ExportExcel.createWorkBook(list,keys,columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
			return null;
		}else {
//				String filemsg = "无该系参加该竞赛指导教师信息！";
//				request.getSession().setAttribute("filemsg", filemsg);
			if((String)session.getAttribute("userdepartment")!=null) {
				return "redirect:/users/DTzExportChaXun";
			}else {
				System.out.println("系别："+Depart);
				return "ExportChaXun";
			}
		}
	}
	
	// 导出 教师工作量表 根据 竞赛编号 系别
	@RequestMapping("/downloaddepartworkload")
	public String downloaddepartworkload(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(value="Compid") Integer Compid,@RequestParam(value="Compname") String Compname,@RequestParam(value="Department") String Department)throws IOException{
		String Depart = Department;
		// 编码 转换
		try {
			Compname = new String(Compname.getBytes("ISO8859-1"),"UTF-8");
			Department = new String(Department.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<TrWorkload> trworkloadlist = teacherService.findAllTrWorkloadByIdDepart(Compid, Department);
		if(trworkloadlist !=null) {
			
			String fileName = Department+Compname+"教师工作量表";
			List<Map<String,Object>> list=createExcelRecordWorkload(trworkloadlist);
			
			String columnNames[] = {"工号","姓名","所在部门","工作量","电话"}; // 列名
			String keys[] = {"trid","trname","trdepartment","workload","trtele"}; // map 中的key
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				ExportExcel.createWorkBook(list,keys,columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
			return null;
		}else {
//				String filemsg = "无该系参加该竞赛指导教师信息！";
//				request.getSession().setAttribute("filemsg", filemsg);
			if((String)session.getAttribute("userdepartment")!=null) {
				return "redirect:/users/DTzExportChaXun";
			}else {
				System.out.println("系别："+Depart);
				return "ExportChaXun";
			}
		}
	}
	
	// 队伍奖金表（奖金 仅仅 发给 队长）
	@RequestMapping("/downloadteambonus")
	public String downloadteambonus(HttpServletRequest request,HttpServletResponse response,@RequestParam(value="Compid") Integer Compid,@RequestParam(value="Compname") String Compname)throws IOException {
		List<StuBonus> teambonuslist = studentService.findAllTeamBonusById(Compid); // 队伍奖金
		if(teambonuslist!=null) {
			// 编码 转换
			try {
				Compname = new String(Compname.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String fileName = Compname+"队伍奖金表";
			List<Map<String,Object>> list=createExcelRecordTeamBonus(teambonuslist);
			
			String columnNames[] = {"学号","姓名","系别","班级","获奖级别","认定级别","队伍奖金数","电话","银行卡号"}; // 列名
			String keys[] = {"stuid","stuname","sdepartment","sclass","awlevel","sclevel","teambonus","stutele","stucard"}; // map 中的key
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				ExportExcel.createWorkBook(list,keys,columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
			return null;
		}else {
			String filemsg = "请先导入参赛信息！";
			request.getSession().setAttribute("filemsg", filemsg);
			return "redirect:/export/TzdExportGuanLi?Compid="+Compid+"";
		}
	}
	private List<Map<String, Object>> createExcelRecordTeamBonus(List<StuBonus> sblist) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "sheet1");
		listmap.add(map);
		StuBonus stubonus=null;
		for (int j = 0; j < sblist.size(); j++) {
			stubonus=sblist.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("stuid", stubonus.getStuid());
			mapValue.put("stuname",stubonus.getStuname());
			mapValue.put("sdepartment", stubonus.getStudepartment());
			mapValue.put("sclass", stubonus.getStuclass());
			mapValue.put("awlevel", stubonus.getAwlevel());
			mapValue.put("sclevel", stubonus.getSclevel());
			mapValue.put("teambonus", stubonus.getBonus()); // 队伍奖金数
			mapValue.put("stutele", stubonus.getStutele());
			mapValue.put("stucard", stubonus.getStucard());
			listmap.add(mapValue);
		}
		return listmap;
	}
	
	// 根据 竞赛编号 系别 导出 "队伍奖金表"
	@RequestMapping("/downloaddepartteambonus")
	public String downloaddepartteambonus(HttpSession session,HttpServletRequest request,HttpServletResponse response,@RequestParam(value="Compid") Integer Compid,@RequestParam(value="Compname") String Compname,@RequestParam(value="Department") String Department)throws IOException {
		String Depart = Department;
		// 编码 转换
		try {
			Compname = new String(Compname.getBytes("ISO8859-1"),"UTF-8");
			Department = new String(Department.getBytes("ISO8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<StuBonus> teambonuslist = studentService.findAllTeamBonusByIdDepart(Compid, Department);
		if(teambonuslist!=null) {
			String fileName = Department+Compname+"队伍奖金表";
			List<Map<String,Object>> list=createExcelRecordTeamBonus(teambonuslist);
			
			String columnNames[] = {"学号","姓名","系别","班级","获奖级别","认定级别","队伍奖金数","电话","银行卡号"}; // 列名
			String keys[] = {"stuid","stuname","sdepartment","sclass","awlevel","sclevel","teambonus","stutele","stucard"}; // map 中的key
			
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				ExportExcel.createWorkBook(list,keys,columnNames).write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			// 设置response参数，可以打开下载页面
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
			ServletOutputStream out = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[2048];
				int bytesRead;
				// Simple read/write loop.
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				throw e;
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
			return null;
		}else {
//				String filemsg = "无该系参加该竞赛指导教师信息！";
//				request.getSession().setAttribute("filemsg", filemsg);
			if((String)session.getAttribute("userdepartment")!=null) {
				return "redirect:/users/DTzExportChaXun";
			}else {
				System.out.println("系别："+Depart);
				return "ExportChaXun";
			}
		}
	}
	
}
