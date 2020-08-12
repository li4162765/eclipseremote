package org.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.entity.Student;
import org.student.service.StudentService;

public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取待修改的人的学号
		int no = Integer.parseInt(request.getParameter("sno"));
		//获取修改后的内容
		String name = request.getParameter("sname");
		int age = Integer.parseInt(request.getParameter("sage"));
		String address = request.getParameter("saddress");
		//将修改后的内容封装到一个实体类中
		Student student  = new Student(name,age,address);
		
		StudentService service = new StudentService();
		boolean result = service.updateStudentBySno(no, student);
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		if(result) {
			//response.getWriter().println("修改成功！");
			response.sendRedirect("QueryAllStudentsServlet");//修改完成后 重新查询所有的学生并显示
		}else {
			response.getWriter().println("修改失败！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
