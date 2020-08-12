package org.student.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.student.entity.Student;

public class StudentDao {
	//ͨ��ѧ�Ų�ѯѧ���Ƿ����
	public boolean isExist(int sno) {
		return queryStudentByno(sno) == null?false:true;
	}
	
	//����ѧ����Ϣ
	public boolean addStudent(Student student) {
		//JDBC����
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			//1��ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2����ȡ����
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3����ȡԤ��������ݿ��������
			String sql = 
					"insert into student(sno,sname,sage,saddress) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			//��ռλ��?��ֵ����1��?�±���1����2��?�±���2.JDBC�������±궼�Ǵ�1��ʼ
			ps.setInt(1, student.getSno());
			ps.setString(2,student.getSname());
			ps.setInt(3, student.getSage());
			ps.setString(4,student.getSaddress());
			//4��ִ��sql
			int count = ps.executeUpdate();
			//5����������
			if(count>0) {
				return true;
			}else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//6���ͷ���Դ
			if(ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	//����ѧ���޸�ѧ��������sno֪�����޸ĵ���,������˸ĳ� student
	public boolean updateStuentBySno(int sno,Student student) {
		//JDBC����
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			//1��ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2����ȡ����
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3����ȡԤ��������ݿ��������
			String sql = 
					"update student set sname =?,sage =?,saddress =? where sno=?";
			ps = conn.prepareStatement(sql);
			//��ռλ��?��ֵ����1��?�±���1����2��?�±���2.JDBC�������±궼�Ǵ�1��ʼ
			//�޸ĺ������
			ps.setString(1,student.getSname());
			ps.setInt(2, student.getSage());
			ps.setString(3,student.getSaddress());
			//�޸ĵ��Ǹ���
			ps.setInt(4, sno);
			//4��ִ��sql
			int count = ps.executeUpdate();
			//5����������
			if(count>0) {
				return true;
			}else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//6���ͷ���Դ
			if(ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	//����ѧ��ɾ��ѧ��
	public boolean deleteStudentByno(int sno) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			//1��ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2����ȡ����
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3����ȡԤ��������ݿ��������
			String sql = 
					"delete from student where sno =?";
			ps = conn.prepareStatement(sql);
			//��ռλ��?��ֵ����1��?�±���1����2��?�±���2.JDBC�������±궼�Ǵ�1��ʼ
			ps.setInt(1, sno);
			//4��ִ��sql
			int count = ps.executeUpdate();
			//5����������
			if(count>0) {
				return true;
			}else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//6���ͷ���Դ
			if(ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//��ѯȫ��ѧ��
	public List<Student> queryStudent(){
		List<Student> students = new ArrayList<>();
		Student student = null;	
		//JDBC����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		try {
			//1��ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2����ȡ����
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3����ȡԤ��������ݿ��������
			String sql = 
					"select * from student ";
			ps = conn.prepareStatement(sql);
			//��ռλ��?��ֵ����1��?�±���1����2��?�±���2.JDBC�������±궼�Ǵ�1��ʼ
			//4��ִ��sql
			rs = ps.executeQuery();
			//5����������
			while(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no,name,age,address);
				students.add(student);
			}
			
			return students;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//6���ͷ���Դ
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	//����ѧ�Ų�ѯѧ��
	public Student queryStudentByno(int sno) {
		Student student = null;	
		//JDBC����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		try {
			//1��ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2����ȡ����
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3����ȡԤ��������ݿ��������
			String sql = 
					"select * from student where sno=?";
			ps = conn.prepareStatement(sql);
			//��ռλ��?��ֵ����1��?�±���1����2��?�±���2.JDBC�������±궼�Ǵ�1��ʼ
			ps.setInt(1, sno);
			//4��ִ��sql
			rs = ps.executeQuery();
			//5����������
			if(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("sname");
				int age = rs.getInt("sage");
				String address = rs.getString("saddress");
				student = new Student(no,name,age,address);
			}
			
			return student;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//6���ͷ���Դ
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
			
}
