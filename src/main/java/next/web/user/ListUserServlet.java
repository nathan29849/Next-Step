package next.web.user;

import core.db.DataBase;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/list")
public class ListUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCookies() == null){
            throw new IllegalStateException("로그인되지 않은 유저입니다.");
        }

        Arrays.stream(req.getCookies())
            .filter(c -> c.getName().equals("login"))
            .findAny()
            .orElseThrow(() -> new IllegalStateException("로그인되지 않은 유저입니다."));

        req.setAttribute("users", DataBase.findAll());
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
