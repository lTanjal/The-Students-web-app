package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import data_access_db.StudentDAO;
import model.Student;

/**
 * Servlet implementation class StudentListServlet
 */
@WebServlet("/students")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		// Set the response data type to JSON and the character encoding to UTF-8
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		StudentDAO studentDAO = new StudentDAO();
		List<Student> studentList = studentDAO.getAllStudents();

		Gson gson = new Gson();
		String jsonString = gson.toJson(studentList);

		out.println(jsonString);

	}
}
// End