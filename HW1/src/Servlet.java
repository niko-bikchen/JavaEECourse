import java.io.*;

import javax.servlet.*;

import javax.servlet.http.*;


public class Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("title", "Welcome");
        request.setAttribute("welcomeTitle", "Welcome to The Form !");
        request.setAttribute("welcomeBody", "Please fill out The Form");

        request.getRequestDispatcher("welcome.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line = "";

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String data = builder.toString();

        String[] userData = data.split("&");
        String[] fieldData = null;
        String userName = "";
        String userPassword = "";

        for (String field : userData) {
            fieldData = field.split("=");

            switch (fieldData[0]) {
                case "userName":
                    if (fieldData.length == 2) {
                        userName = fieldData[1];
                    }
                    break;
                case "userPassword":
                    if (fieldData.length == 2) {
                        userPassword = fieldData[1];
                    }
                    break;
            }
        }

        if (userName.equals("Nick") && userPassword.equals("admin")) {
            request.getRequestDispatcher("success.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("failure.jsp").forward(request, response);
        }
    }
}