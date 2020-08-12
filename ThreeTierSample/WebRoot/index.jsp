<%@page import="org.student.entity.Student"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生信息列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  		<%
		//error:adderror 失败
		//否则：1 确实执行了增加    2直接访问查询全部页面
		String error = (String)request.getAttribute("error") ;//addError
		if(error!=null){
			if(error.equals("addError")){
				out.print("增加失败！");
			}else if(error.equals("noaddError")){
				out.print("增加成功！");
			}//根本没有执行增加
		}	
		
		%>
    	<table border="1px">
    		<tr>
    			<th>学号</th>
    			<th>姓名</th>
    			<th>年龄</th>
    			<th>操作</th>
   			</tr> 
   			
   			<%
   				//获取request域中的数据
   				List<Student> students = (List<Student>)request.getAttribute("students");
   				for(Student student:students){
 			%>
 					<tr>
 						<td><a href="QueryStudentBySnoServlet?sno=<%=student.getSno()%>"><%=student.getSno() %></a></td>
 						
 						<td><%=student.getSname() %></td>
 						<td><%=student.getSage() %></td>
 						<td><a href="DeleteStudentServlet?sno=<%=student.getSno() %>">删除</a></td>
					</tr>
 			<%	
   				}
   			 %>
		</table>
			<a href="add.jsp">新增</a>
</html>
