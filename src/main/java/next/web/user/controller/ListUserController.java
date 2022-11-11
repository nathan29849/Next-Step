package next.web.user.controller;

import core.db.DataBase;
import java.util.Arrays;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import next.web.Controller;

public class ListUserController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Object value = session.getAttribute("user");
		if (request.getCookies() == null || value == null){
			return "redirect:/user/loginForm";
		}

		Optional<Cookie> cookie = Arrays.stream(request.getCookies())
			.filter(c -> c.getName().equals("login"))
			.findAny();

		if (cookie.isPresent()) {
			request.setAttribute("users", DataBase.findAll());
			return "/user/list.jsp";
		}

		return "redirect:/user/loginForm";
	}
}
