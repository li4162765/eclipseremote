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
		//��ȡ���޸ĵ��˵�ѧ��
		int no = Integer.parseInt(request.getParameter("sno"));
		//��ȡ�޸ĺ������
		String name = request.getParameter("sname");
		int age = Integer.parseInt(request.getParameter("sage"));
		String address = request.getParameter("saddress");
		//���޸ĺ�����ݷ�װ��һ��ʵ������
		Student student  = new Student(name,age,address);
		
		StudentService service = new StudentService();
		boolean result = service.updateStudentBySno(no, student);
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		if(result) {
			//response.getWriter().println("�޸ĳɹ���");
			response.sendRedirect("QueryAllStudentsServlet");//�޸���ɺ� ���²�ѯ���е�ѧ������ʾ
		}else {
			response.getWriter().println("�޸�ʧ�ܣ�");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
