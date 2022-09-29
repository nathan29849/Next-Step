package next.web.user;

import core.db.DataBase;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/user/update")
public class UpdateUserServlet extends HttpServlet {

	private final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		HttpSession session = req.getSession();
		Object value = session.getAttribute("user");
		if (req.getCookies() == null || value == null){
			throw new IllegalStateException("로그인되지 않은 유저입니다.");
		}

		User user = (User) value;
		String userId = user.getUserId();
		log.debug("[doGET] userId = {}", userId);
		User savedUser = DataBase.findUserById(userId);
		req.setAttribute("user", savedUser);
		RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		HttpSession session = req.getSession();
		Object value = session.getAttribute("user");
		if (req.getCookies() == null || value == null){
			throw new IllegalStateException("로그인되지 않은 유저입니다.");
		}

		User user = (User) value;
//		String userId = req.getParameter("userId");
//		User savedUser = DataBase.findUserById(userId);
		User savedUser = DataBase.findUserById(user.getUserId());
		savedUser.update(
			req.getParameter("password"),
			req.getParameter("name"),
			req.getParameter("email")
		);

		log.debug("USER UPDATE COMPLETE: userId={}, password={}, name={}, email={}",
			savedUser.getUserId(), savedUser.getPassword(), savedUser.getName(), savedUser.getEmail());

		req.setAttribute("users", DataBase.findAll());
		resp.sendRedirect("/user/list");
	}
}
