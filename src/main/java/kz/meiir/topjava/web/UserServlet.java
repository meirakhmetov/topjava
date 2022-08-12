package kz.meiir.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Meiir Akhmetov on 02.08.2022
 */
public class UserServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int userId=Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to users");
        request.getRequestDispatcher("/users.jsp").forward(request,response);

    }
}
