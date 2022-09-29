package next.web.login;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(LogoutServlet.class);
	private static final String HOME = "/";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		if (req.getCookies() == null){
			throw new IllegalStateException("로그인되지 않은 유저입니다.");
		}

		Arrays.stream(req.getCookies())
			.filter(c -> c.getName().equals("login"))
			.findAny()
			.orElseThrow(() -> new IllegalStateException("로그인되지 않은 유저입니다."));

		resp.addCookie(createCookie("login", "false"));
		req.getSession().invalidate();
		resp.sendRedirect(HOME);
	}

	private Cookie createCookie(String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(HOME);
		return cookie;
	}
}
