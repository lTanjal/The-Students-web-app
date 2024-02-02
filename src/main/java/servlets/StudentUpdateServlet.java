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
import model.Student;

/**
 * Servlet implementation class StudentUpdateServlet
 */
@WebServlet("/studentUpdate")
public class StudentUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		//  Retrieve the values of the request parameters
				StudentDAO studentDAO = new StudentDAO();
				
				int id= Integer.parseInt(request.getParameter("id"));
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String streetaddress = request.getParameter("streetaddress");
				String postcode = request.getParameter("postcode");
				String postoffice = request.getParameter("postoffice");
				
				Student student= new Student(id, firstname, lastname, streetaddress, postcode, postoffice);
				int studentUpdate = studentDAO.updateStudent(student);
				
		
				Gson gson = new Gson();
				String jsonString = gson.toJson(studentUpdate);

				out.println(jsonString);

	}

}
