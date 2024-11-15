package src.java;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        String agree = request.getParameter("agree");

        out.println("<h1>Form Data</h1>");
        out.println("<p>Username: " + username + "</p>");
        out.println("<p>Gender: " + gender + "</p>");
        out.println("<p>Agreed to terms: " + (agree != null ? "Yes" : "No") + "</p>");

        double real = Double.parseDouble(request.getParameter("real"));
        double imaginary = Double.parseDouble(request.getParameter("imaginary"));

        double absValue = Math.sqrt(real * real + imaginary * imaginary);
        double arg = Math.atan2(imaginary, real);
        String conjugate = real + " - " + imaginary + "i";

        out.println("<h2>Complex Number Calculations</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Calculation</th><th>Result</th></tr>");
        out.println("<tr><td>Absolute Value</td><td>" + absValue + "</td></tr>");
        out.println("<tr><td>Argument</td><td>" + arg + "</td></tr>");
        out.println("<tr><td>Conjugate</td><td>" + conjugate + "</td></tr>");
        out.println("</table>");
    }
}