package com.example.forumbiznes;

import com.example.forumbiznes.Dao.UserDao;
import com.example.forumbiznes.Model.User;
import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    @EJB
    private UserDao dao;

    public void init() {
        message = "Hello World!";

        String login = "BURAK",
                password = "BURAK",
        email = "BURAK@BURAK.BURAK";

        if(!dao.isLoginOccupied(login)){
            dao.save(new User(login, password, email, 2));
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}