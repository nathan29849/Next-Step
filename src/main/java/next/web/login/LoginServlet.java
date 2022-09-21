package next.web.login;

import core.db.DataBase;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
	private static final int HOUR = 3600;
	private static final String HOME = "/";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/user/login.html");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		log.debug("USER ID = {}, PASSWORD = {}", userId, password);

		User user = DataBase.findUserById(userId);
		if (user == null || !user.login(userId, password)){
			log.debug("LOGIN FAILED ! : USER ID 또는 PASSWORD 불일치");
			resp.sendRedirect("/user/login_failed");
			return;
		}

		log.debug("LOGIN COMPLETE !");
		resp.addCookie(createCookie("login", "true"));
		resp.sendRedirect(HOME);
	}

	private Cookie createCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(HOUR);
		cookie.setPath(HOME);
		return cookie;
	}

}
