package com.match.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.match.springmvc.dao.IStudentDAO;
import com.match.springmvc.data.StuBonus;
import com.match.springmvc.data.StudentInfo;
import com.match.springmvc.data.TeamInfoStu;
import com.match.springmvc.entities.Student;

@Service
public class StudentService implements IStudentService {
	
	@Autowired
	private IStudentDAO studentDAO;
	
	@Override
	public List<StudentInfo> countStuTpartNum() {
		// TODO Auto-generated method stub
		return studentDAO.countStuTpartNum();
	}
	
	@Override
	public List<TeamInfoStu> findAllTeamByStuid(String Stuid) {
		// TODO Auto-generated method stub
		return studentDAO.findAllTeamByStuid(Stuid);
	}
	
	@Override
	public List<StudentInfo> findStuById(String Stuid) {
		// TODO Auto-generated method stub
		return studentDAO.findStuById(Stuid);
	}
	
	@Override
	public List<Student> findStuInfoByStuid(String Stuid) {
		// TODO Auto-generated method stub
		return studentDAO.findStuInfoByStuid(Stuid);
	}
	
	@Override
	public boolean updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentDAO.updateStudent(student);
	}
	
	@Override
	public List<StuBonus> findAllStuBonusById(Integer Compid) {
		// TODO Auto-generated method stub
		return studentDAO.findAllStuBonusById(Compid);
	}
	
	@Override
	public List<StuBonus> findAllStuBonusByIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		return studentDAO.findAllStuBonusByIdDepart(Compid, Department);
	}
	
	@Override
	public List<StuBonus> findAllTeamBonusById(Integer Compid) {
		// TODO Auto-generated method stub
		return studentDAO.findAllTeamBonusById(Compid);
	}
	
	@Override
	public List<StuBonus> findAllTeamBonusByIdDepart(Integer Compid, String Department) {
		// TODO Auto-generated method stub
		return studentDAO.findAllTeamBonusByIdDepart(Compid, Department);
	}
	
	@Override
	public boolean deleteStudentByStuid(String  Stuid) {
		// TODO Auto-generated method stub
		return studentDAO.deleteStudentByStuid(Stuid);
	}
	
}
