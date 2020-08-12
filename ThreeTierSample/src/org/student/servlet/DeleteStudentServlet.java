package org.student.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.student.service.StudentService;

public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteStudentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ɾ��
		request.setCharacterEncoding("utf-8");
		//����ǰ�δ�����ѧ��
		int no = Integer.parseInt(request.getParameter("sno"));
		
		StudentService service = new StudentService();
		boolean result = service.deleteStudentBySno(no);
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		if(result) {
			response.sendRedirect("QueryAllStudentsServlet");//���²�ѯ ����ѧ��
		}else {
			response.getWriter().println("ɾ��ʧ�ܣ�");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
