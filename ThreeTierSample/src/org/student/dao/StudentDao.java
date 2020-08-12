package org.student.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.student.entity.Student;

public class StudentDao {
	//通过学号查询学生是否存在 很重要
	public boolean isExist(int sno) {
		return queryStudentByno(sno) == null?false:true;
	}
	
	//增加学生信息
	public boolean addStudent(Student student) {
		//JDBC代码
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			//1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2、获取连接
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3、获取预编译的数据库操作对象
			String sql = 
					"insert into student(sno,sname,sage,saddress) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			//给占位符?传值（第1个?下标是1，第2个?下标是2.JDBC中所有下标都是从1开始
			ps.setInt(1, student.getSno());
			ps.setString(2,student.getSname());
			ps.setInt(3, student.getSage());
			ps.setString(4,student.getSaddress());
			//4、执行sql
			int count = ps.executeUpdate();
			//5、处理结果集
			if(count>0) {
				return true;
			}else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//6、释放资源
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
	
	//根据学号修改学生：根据sno知道待修改的人,把这个人改成 student
	public boolean updateStuentBySno(int sno,Student student) {
		//JDBC代码
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			//1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2、获取连接
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3、获取预编译的数据库操作对象
			String sql = 
					"update student set sname =?,sage =?,saddress =? where sno=?";
			ps = conn.prepareStatement(sql);
			//给占位符?传值（第1个?下标是1，第2个?下标是2.JDBC中所有下标都是从1开始
			//修改后的内容
			ps.setString(1,student.getSname());
			ps.setInt(2, student.getSage());
			ps.setString(3,student.getSaddress());
			//修改的那个人
			ps.setInt(4, sno);
			//4、执行sql
			int count = ps.executeUpdate();
			//5、处理结果集
			if(count>0) {
				return true;
			}else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//6、释放资源
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
	
	
	
	//根据学号删除学生
	public boolean deleteStudentByno(int sno) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			//1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2、获取连接
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3、获取预编译的数据库操作对象
			String sql = 
					"delete from student where sno =?";
			ps = conn.prepareStatement(sql);
			//给占位符?传值（第1个?下标是1，第2个?下标是2.JDBC中所有下标都是从1开始
			ps.setInt(1, sno);
			//4、执行sql
			int count = ps.executeUpdate();
			//5、处理结果集
			if(count>0) {
				return true;
			}else {
				return false;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			//6、释放资源
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
	
	//查询全部学生
	public List<Student> queryStudent(){
		List<Student> students = new ArrayList<>();
		Student student = null;	
		//JDBC代码
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		try {
			//1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2、获取连接
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3、获取预编译的数据库操作对象
			String sql = 
					"select * from student ";
			ps = conn.prepareStatement(sql);
			//给占位符?传值（第1个?下标是1，第2个?下标是2.JDBC中所有下标都是从1开始
			//4、执行sql
			rs = ps.executeQuery();
			//5、处理结果集
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
			//6、释放资源
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
	
	//根据学号查询学生
	public Student queryStudentByno(int sno) {
		Student student = null;	
		//JDBC代码
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		
		try {
			//1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2、获取连接
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bjpowernode","root","4162765");
			//3、获取预编译的数据库操作对象
			String sql = 
					"select * from student where sno=?";
			ps = conn.prepareStatement(sql);
			//给占位符?传值（第1个?下标是1，第2个?下标是2.JDBC中所有下标都是从1开始
			ps.setInt(1, sno);
			//4、执行sql
			rs = ps.executeQuery();
			//5、处理结果集
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
			//6、释放资源
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
