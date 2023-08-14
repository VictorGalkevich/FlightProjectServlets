package model.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.utility.UrlPath;

import java.io.IOException;

import static model.utility.UrlPath.*;

@WebServlet(LOCALE)
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var language = req.getParameter("lang");
        req.getSession().setAttribute("lang", language);

        var referer = req.getHeader("referer");
        var page = referer != null ? referer : LOGIN;
        resp.sendRedirect(page + "?lang=" + language);
    }
}
