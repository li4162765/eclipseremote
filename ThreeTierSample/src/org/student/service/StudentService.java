package org.student.service;

import java.util.List;

import org.student.dao.StudentDao;
import org.student.entity.Student;

//业务逻辑层：逻辑性的增删改查（增：查+增），对dao层进行的组装
public class StudentService {
	StudentDao studentDao = new StudentDao();
	
	//根据学号查询学生
	public Student querySstudentBySno(int sno) {
		return studentDao.queryStudentByno(sno);
	}
	
	//查询所有学生
	public List<Student> queryAllStudents(){
		return studentDao.queryStudent();
	}
	
	
	public boolean updateStudentBySno(int sno,Student student) {
		if(studentDao.isExist(sno)) {
			return studentDao.updateStuentBySno(sno, student);
		}else {
			return false;
		}
	}
	
	public boolean deleteStudentBySno(int sno) {
		if(studentDao.isExist(sno)) {
			return studentDao.deleteStudentByno(sno);
		}else {
			return false;
		}
		
	}
	
	public boolean addStudent(Student student) {
		if(!studentDao.isExist(student.getSno())) {
			return studentDao.addStudent(student);
		}else{
			System.out.println("此人已存在！");
			return false;
		}
	}

}
