package next.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/login_failed")
public class LoginFailedServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(LoginFailedServlet.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.html");
		rd.forward(req, resp);
	}
}
