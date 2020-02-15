package com.VasyaPupkin;

 

import java.io.*;

import javax.servlet.*;

import javax.servlet.http.*;

 

public class MyServlet extends HttpServlet {

 

protected void processRequest(HttpServletRequest request, HttpServletResponse response)

throws ServletException, IOException {

response.setContentType("text/html;charset=UTF-8");

PrintWriter out = response.getWriter();

try {

out.println("<html>");

out.println("<head>");

out.println("<title>First Servlet</title>");

out.println("</head>");

out.println("<body>");

out.println("<h1>Hello World!</h1>");

out.println("</body>");

out.println("</html>");

 

} finally {

out.close();

}

}

 

protected void doGet(HttpServletRequest request, HttpServletResponse response)

throws ServletException, IOException {

processRequest(request, response);

}

 

protected void doPost(HttpServletRequest request, HttpServletResponse response)

throws ServletException, IOException {

processRequest(request, response);

}

}