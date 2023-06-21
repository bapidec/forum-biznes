package com.example.forumbiznes;

import java.io.*;

import com.example.forumbiznes.Dao.UserDao;
import com.example.forumbiznes.Model.Topic;
import com.example.forumbiznes.Model.User;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";

        UserDao dao = new UserDao();
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