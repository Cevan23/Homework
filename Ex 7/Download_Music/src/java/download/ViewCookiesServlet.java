package download;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.IOException;

@WebServlet(name = "ViewCookiesServlet", urlPatterns = {"/ViewCookiesServlet"})
public class ViewCookiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        
        // HTML content to display the cookies
        StringBuilder htmlContent = new StringBuilder("<html>");
        htmlContent.append("<head>");
        htmlContent.append("<meta charset=\"utf-8\">");
        htmlContent.append("<title>Cookie Viewer</title>");
        htmlContent.append("<link rel=\"stylesheet\" href=\"styles/Cookies.css\" type=\"text/css\">");
        htmlContent.append("</head>");
        htmlContent.append("<body>");
        htmlContent.append("<h1>Cookies</h1>");
        htmlContent.append("<ul class=\"cookie-list\">");
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                htmlContent.append("<li>").append(cookie.getName()).append(": ").append(cookie.getValue()).append("</li>");
            }
        }
        
        htmlContent.append("</ul>");
        htmlContent.append("<a href=\"/Download_Music/\" class=\"view-albums-link\">View Albums</a>");
        htmlContent.append("</body></html>");
        
        response.setContentType("text/html");
        response.getWriter().write(htmlContent.toString());
    }
}
