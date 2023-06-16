
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		try {
			boolean UserExists = userDAO.ifUserExists(Username);
			if (UserExists) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("UserAlreadyExists.html");
				dispatcher.forward(request, response);
			} else {
				boolean Insertuser = userDAO.insertUser(Username, Password);
				if (Insertuser) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("Success.html");
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("Error.html");
					dispatcher.forward(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
