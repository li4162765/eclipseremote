package org.student.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Student;
import org.student.service.StudentService;

public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddStudentServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int no =  Integer.parseInt(request.getParameter("sno"));
		String name = request.getParameter("sname");
		int age = Integer.parseInt(request.getParameter("sage"));
		String address = request.getParameter("saddress");
		Student student = new Student(no,name,age,address);
		
		StudentService studentService = new StudentService();
		boolean result = studentService.addStudent(student);
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		/*if(result) {
			
			response.sendRedirect("QueryAllStudentsServlet");//重新查询 所有学生
		}else {
			out.println("增加失败！");
		}
		*/
		if(!result) {//如果增加失败，给request放入一条数据error
			request.setAttribute("error", "addError");  
		}else {//增加成功
			request.setAttribute("error", "noaddError");  
		}
//		response.sendRedirect("QueryAllStudentsServlet");
		request.getRequestDispatcher("QueryAllStudentsServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
