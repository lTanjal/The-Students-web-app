package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// 2. Get the values of the request parameters
		String paramX = request.getParameter("x");
		String paramY = request.getParameter("y");
		String oper = request.getParameter("operation");
		int x = Integer.parseInt(paramX);
		int y = Integer.parseInt(paramY);
		int z = 0;
		if (oper.equals("add")) {
			z = x + y;
			out.println(paramX + "+" + paramY + "=" + z);
		}
		else if (oper.equals("multiply")) {
			z = x * y;
			out.println(paramX + "*" + paramY + "=" + z);
		}

		
	

	}
}


//http://localhost:8080/WebAppExercises/calculator?x=5&y=2&operation=add