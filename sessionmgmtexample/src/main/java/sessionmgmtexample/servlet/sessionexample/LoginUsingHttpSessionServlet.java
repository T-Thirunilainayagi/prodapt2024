package sessionmgmtexample.servlet.sessionexample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/loginusingsession")
public class LoginUsingHttpSessionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final String userID = "admin";
	private final String password = "password";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		if(userID.equals(user) && password.equals(pwd)){
			HttpSession session = request.getSession();
			session.setAttribute("user", "Ashwin");
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(2*60);
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(2*60);
			response.addCookie(userName);
			response.sendRedirect("loginsessionsuccess.jsp");
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginsession.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}

	}

}
	 	