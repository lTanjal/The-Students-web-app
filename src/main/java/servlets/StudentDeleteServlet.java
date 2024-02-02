package servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data_access_db.StudentDAO;



/**
 * Servlet implementation class StudentDeleteServlet
 */
@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		//  Retrieve the values of the request parameters
		StudentDAO studentDAO= new StudentDAO();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int studentDelete = studentDAO.deleteStudent(id);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(studentDelete);

		out.println(jsonString);

		
	}

}
