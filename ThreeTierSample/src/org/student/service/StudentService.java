package org.student.service;

import java.util.List;

import org.student.dao.StudentDao;
import org.student.entity.Student;

//ҵ���߼��㣺�߼��Ե���ɾ�Ĳ飨������+��������dao����е���װ
public class StudentService {
	StudentDao studentDao = new StudentDao();
	
	//����ѧ�Ų�ѯѧ��
	public Student querySstudentBySno(int sno) {
		return studentDao.queryStudentByno(sno);
	}
	
	//��ѯ����ѧ��
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
			System.out.println("�����Ѵ��ڣ�");
			return false;
		}
	}

}
