package servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Student;

/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/jsontest")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Student[] studentArray= {new Student(40, "Ginger", "Baker", "Rumputie 10", "54120", "PULP"),
				new Student(10, "Jack", "Bruce", "Asematori 3", "00520", "HELSINKI")};
		
	
		Gson gson = new Gson();
		String jsonString = gson.toJson(studentArray);
	
		out.println(jsonString);

	}

}
