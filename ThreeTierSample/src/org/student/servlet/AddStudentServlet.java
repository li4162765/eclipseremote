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
			
			response.sendRedirect("QueryAllStudentsServlet");//���²�ѯ ����ѧ��
		}else {
			out.println("����ʧ�ܣ�");
		}
		*/
		if(!result) {//�������ʧ�ܣ���request����һ������error
			request.setAttribute("error", "addError");  
		}else {//���ӳɹ�
			request.setAttribute("error", "noaddError");  
		}
//		response.sendRedirect("QueryAllStudentsServlet");
		request.getRequestDispatcher("QueryAllStudentsServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
