package com.match.springmvc.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.match.springmvc.entities.Competition;
import com.match.springmvc.entities.StuTeam;
import com.match.springmvc.entities.Student;
import com.match.springmvc.entities.Teacher;
import com.match.springmvc.entities.Team;
import com.match.springmvc.entities.TrTeam;

public class ReadExcel {
	
	
	
	// 构造方法
	public ReadExcel() {
	
	}
	// 总行数
	private int totalRows = 0;
	// 总列数
	private int totalCells = 0;
	// 错误信息接收器
	private String errorMsg;
	
	public int getTotalRows() {
		return totalRows;
	}
	public int getTotalCells() {
		return totalCells;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * 验证是否为Excel 文件
	 * @param filePath
	 * @return
	 */
	public boolean validateExcel(String filePath) {
		if (filePath == null||!(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))){
			errorMsg = "文件名不是Excel格式！";
			return false;
		}
		return true;
	}

	// 第一张 工作簿
	public List<Team> getTeamInfo(String fileName,MultipartFile Mfile){
		// 把Spring 文件上传的MultipartFile 转换成CommonsMultipartFile 类型
		CommonsMultipartFile cmfile= (CommonsMultipartFile)Mfile; // 获取本地存储路径
		File file = new File("D:\\FileUpload"); // 创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		if (!file.exists())
			file.mkdirs();
		//新建一个文件
		File file1 = new File("D:\\FileUpload\\FileUpload" + fileName);
		// 将上传的文件写入新建的文件中
		try {
			cmfile.getFileItem().write(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		List<Team> teamlist = new ArrayList<Team>();
		InputStream is = null;
		try {
			if(!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;
			if(WDWUtil.isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			is = new FileInputStream(file1); // 根据新建的文件实例化输入流
			teamlist = getTeamList(is,isExcel2003);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				}catch(IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return teamlist;
	}
	/**
	 * 根据Excel里面的内容读取客户信息
	 * @param is
	 * @param isExcel2003 Excel是2003还是2007版本
	 * @return
	 */
	public List<Team> getTeamList(InputStream is,boolean isExcel2003){
		List<Team> teamlist = null;
		try {
			// 根据版本选择创建Workbook的方式
			Workbook wb = null;
			if(isExcel2003) { // 当excel是2003时
				wb = new HSSFWorkbook(is);
			}else { // 当excel是2007时
				wb = new XSSFWorkbook(is);
			}
			teamlist = readTeamValue(wb);
		}catch(IOException e){
			e.printStackTrace();
		}
		return teamlist;
	}
	
	private List<Team> readTeamValue(Workbook wb){
		
		Sheet sheet=wb.getSheetAt(0); // 得到第一个sheet
		this.totalRows=sheet.getPhysicalNumberOfRows(); // 得到Excel的行数
		System.out.println("测试(行数)："+this.totalRows);
		// 得到Excel的列数(前提是有行数)
		if(totalRows>=1 && sheet.getRow(0) != null){
			this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<Team> teamlist=new ArrayList<Team>();
		Team team;
		// 循环Excel 行数，从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			Row row = sheet.getRow(r);
			if(row ==null)
				continue;
			team = new Team();
			// 循环Excel 的列
			for(int c=0;c<this.totalCells;c++) {
				row.getCell(c).setCellType(Cell.CELL_TYPE_STRING);
				Cell cell = row.getCell(c);
				if(null!=cell) {
					if(c==0) {
						team.setTmcname(cell.getStringCellValue());// 队伍参与的竞赛名称
					}else if(c==1) {
						Competition competition =new Competition();
			//			Integer Compid =Integer.valueOf((int) cell.getStringCellValue());
						Integer Compid =Integer.valueOf( cell.getStringCellValue());
						System.out.println("ReadExcel---竞赛编号:"+Compid);
						competition.setCompid(Compid);
						team.setCompetition(competition);
						System.out.println("测试列-ReadExcel-team.setCompetition："+team.getCompetition().getCompid());
					}else if(c==2) {
						team.setTeamid(cell.getStringCellValue());
					}else if(c==3) {
						team.setSmens(cell.getStringCellValue());
					}else if(c==4) {
						team.setTmens(cell.getStringCellValue());
					}else if(c==5) {
						team.setAwlevel(cell.getStringCellValue());
					}
//	    			else if(c==6){
//	    				team.setSclevel(cell.getStringCellValue());
//	    			}
				}
			}
			teamlist.add(team);
		}
		return teamlist;
	}
	// 第二张工作簿（学生信息）
	public List<StuTeam> getStuTeamInfo(String fileName,MultipartFile Mfile){
		// 把Spring 文件上传的MultipartFile 转换成CommonsMultipartFile 类型
		CommonsMultipartFile cmfile= (CommonsMultipartFile)Mfile; // 获取本地存储路径
		File file = new File("D:\\FileUpload"); // 创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		if (!file.exists())
			file.mkdirs();
		//新建一个文件
		File file1 = new File("D:\\FileUpload\\FileUpload" + fileName);
		// 将上传的文件写入新建的文件中
		try {
			cmfile.getFileItem().write(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<StuTeam> stuteamlist = new ArrayList<StuTeam>();
		InputStream is = null;
		try {
			if(!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;
			if(WDWUtil.isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			is = new FileInputStream(file1); // 根据新建的文件实例化输入流
			stuteamlist = getStuTeamList(is,isExcel2003);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				}catch(IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return stuteamlist;
	}
	/**
	 * 根据Excel里面的内容读取客户信息
	 * @param is
	 * @param isExcel2003 Excel是2003还是2007版本
	 * @return
	 */
	public List<StuTeam> getStuTeamList(InputStream is,boolean isExcel2003){
		List<StuTeam> stuteamlist = null;
		try {
			// 根据版本选择创建Workbook的方式
			Workbook wb = null;
			if(isExcel2003) { // 当excel是2003时
				wb = new HSSFWorkbook(is);
			}else { // 当excel是2007时
				wb = new XSSFWorkbook(is);
			}
			stuteamlist = readStuTeamValue(wb);
		}catch(IOException e){
			e.printStackTrace();
		}
		return stuteamlist;
	}
	
	private List<StuTeam> readStuTeamValue(Workbook wb){
		
		Sheet sheet=wb.getSheetAt(1); // 得到第二个sheet
		this.totalRows=sheet.getPhysicalNumberOfRows(); // 得到Excel的行数
		System.out.println("学生队伍---测试(行数)："+this.totalRows);
		// 得到Excel的列数(前提是有行数)
		if(totalRows>=1 && sheet.getRow(0) != null){
			this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<StuTeam> stuteamlist=new ArrayList<StuTeam>();
		StuTeam stuteam;
		
		// 循环Excel 行数，从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			Row row = sheet.getRow(r);
			if(row ==null)
				continue;
			stuteam = new StuTeam();
			// 循环Excel 的列
			for(int c=0;c<this.totalCells;c++) {
				row.getCell(c).setCellType(Cell.CELL_TYPE_STRING);
				Cell cell = row.getCell(c);
				if(null!=cell) {
					if(c==0) {
						stuteam.setCname(cell.getStringCellValue());
					}else if(c==1) {
						Competition competition =new Competition();
					//	Integer Compid =Integer.valueOf((int) cell.getNumericCellValue());
						Integer Compid = Integer.valueOf(cell.getStringCellValue());
						System.out.println("ReadExcel1----学生队伍测试竞赛编号:"+Compid);
						competition.setCompid(Compid);
						stuteam.setCompetition(competition);
						System.out.println("测试-ReadExcel1-stuteam.setCompetition："+stuteam.getCompetition().getCompid());
					}else if(c==2) {
						stuteam.setSname(cell.getStringCellValue());
					}else if(c==3) {
						Student student = new Student();
						String Stuid = cell.getStringCellValue();
						student.setStuid(Stuid);
						stuteam.setStudent(student);
					}else if(c==4) {
						Team team =new Team();
						String Teamid = cell.getStringCellValue();
						team.setTeamid(Teamid);
						stuteam.setTeam(team);
					}else if(c==5) {
						stuteam.setSdepartment(cell.getStringCellValue());
					}else if(c==6) {
						stuteam.setSclass(cell.getStringCellValue());
					}
//	    			else if(c==6) {
//	    				stuteam.setCredit(Float.valueOf((float) cell.getNumericCellValue()));
//	    			}
//	    			else if(c==6) {
//	    				stuteam.setBonus(BigDecimal.valueOf(cell.getNumericCellValue()));
//	    			}
					else if(c==7) {
						stuteam.setHeader(cell.getStringCellValue()); // 获取 是否 是队长
					}
					
				}
			}
			stuteamlist.add(stuteam);
		}
		return stuteamlist;
	}
	
	// 第三张 工作簿
	public List<TrTeam> getTrTeamInfo(String fileName,MultipartFile Mfile){
		// 把Spring 文件上传的MultipartFile 转换成CommonsMultipartFile 类型
		CommonsMultipartFile cmfile= (CommonsMultipartFile)Mfile; // 获取本地存储路径
		File file = new File("D:\\FileUpload"); // 创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
		if (!file.exists())
			file.mkdirs();
		//新建一个文件
		File file1 = new File("D:\\FileUpload\\FileUpload" + fileName);
		// 将上传的文件写入新建的文件中
		try {
			cmfile.getFileItem().write(file1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<TrTeam> trteamlist = new ArrayList<TrTeam>();
		InputStream is = null;
		try {
			if(!validateExcel(fileName)) {// 验证文件名是否合格
				return null;
			}
			boolean isExcel2003 = true;
			if(WDWUtil.isExcel2007(fileName)) {
				isExcel2003 = false;
			}
			is = new FileInputStream(file1); // 根据新建的文件实例化输入流
			trteamlist = getTrTeamList(is,isExcel2003);
			is.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(is!=null) {
				try {
					is.close();
				}catch(IOException e) {
					is = null;
					e.printStackTrace();
				}
			}
		}
		return trteamlist;
	}
	/**
	 * 根据Excel里面的内容读取客户信息
	 * @param is
	 * @param isExcel2003 Excel是2003还是2007版本
	 * @return
	 */
	public List<TrTeam> getTrTeamList(InputStream is,boolean isExcel2003){
		List<TrTeam> trteamlist = null;
		try {
			// 根据版本选择创建Workbook的方式
			Workbook wb = null;
			if(isExcel2003) { // 当excel是2003时
				wb = new HSSFWorkbook(is);
			}else { // 当excel是2007时
				wb = new XSSFWorkbook(is);
			}
			trteamlist = readTrTeamValue(wb);
		}catch(IOException e){
			e.printStackTrace();
		}
		return trteamlist;
	}
	
	private List<TrTeam> readTrTeamValue(Workbook wb){
		
		Sheet sheet=wb.getSheetAt(2); // 得到第三个sheet
		this.totalRows=sheet.getPhysicalNumberOfRows(); // 得到Excel的行数
		System.out.println("教师队伍---测试(行数)："+this.totalRows);
		// 得到Excel的列数(前提是有行数)
		if(totalRows>=1 && sheet.getRow(0) != null){
			this.totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
		}
		List<TrTeam> trteamlist=new ArrayList<TrTeam>();
		TrTeam trteam;
		
		// 循环Excel 行数，从第二行开始。标题不入库
		for(int r=1;r<totalRows;r++) {
			Row row = sheet.getRow(r);
			if(row ==null)
				continue;
			trteam = new TrTeam();
			// 循环Excel 的列
			for(int c=0;c<this.totalCells;c++) {
				row.getCell(c).setCellType(Cell.CELL_TYPE_STRING);
				Cell cell = row.getCell(c);
				if(null!=cell) {
					if(c==0) {
						trteam.setConame(cell.getStringCellValue());
					}else if(c==1) {
						Competition competition =new Competition();
				//		Integer Compid =Integer.valueOf((int) cell.getNumericCellValue());
						Integer Compid = Integer.valueOf(cell.getStringCellValue());
						System.out.println("ReadExcel2---教师队伍测试竞赛编号:"+Compid);
						competition.setCompid(Compid);
						trteam.setCompetition(competition);
						System.out.println("测试-ReadExcel2-trteam.setCompetition："+trteam.getCompetition().getCompid());
					}else if(c==2) {
						trteam.setTname(cell.getStringCellValue());
					}else if(c==3) {
						Teacher teacher = new Teacher();
						String Trid = cell.getStringCellValue();
						teacher.setTrid(Trid);
						trteam.setTeacher(teacher);
					}else if(c==4) {
						Team team =new Team();
						String Teamid = cell.getStringCellValue();
						team.setTeamid(Teamid);
						trteam.setTeam(team);
					}else if(c==5) {
						trteam.setTdepartment(cell.getStringCellValue());
					}
//	    			else if(c==6) {
//	    				trteam.setWorkload(Integer.valueOf((int) cell.getNumericCellValue()));
//	    			}
				}
			}
			trteamlist.add(trteam);
		}
		return trteamlist;
	}
} // END
