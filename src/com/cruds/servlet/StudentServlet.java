package com.cruds.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cruds.db.StudentDAO;
import com.cruds.entity.Student;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StudentDAO dao = new StudentDAO();
		List<Student> list = dao.getAllStudent();
		
		request.setAttribute("STUD_LIST", list);
		RequestDispatcher rd = request.getRequestDispatcher("student.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("ACTION");
		RequestDispatcher rd = request.getRequestDispatcher("student.jsp");
		StudentDAO dao = new StudentDAO();
		
		if("CREATE".equals(action))
		{
			String name = request.getParameter("studentname");
			String rollno = request.getParameter("rollno");
			
			if(dao.create(new Student(name, Integer.parseInt(rollno))))
			{
				request.setAttribute("MESSAGE", "Record inserted successfully");
			}
			else
			{
				request.setAttribute("MESSAGE", "Problem in creating record");
			}
		}
		else if("DELETE".equals(action))
		{
			String rollno = request.getParameter("hdnRollNo");
			//System.out.println(rollno);
			dao.delete(Integer.parseInt(rollno));
		}
		
		List<Student> list = dao.getAllStudent();
		request.setAttribute("STUD_LIST", list);
		
		rd.forward(request, response);
	}

}
