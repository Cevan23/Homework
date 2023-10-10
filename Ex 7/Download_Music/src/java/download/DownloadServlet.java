package download;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.ServletContext;
import java.io.IOException;

import business.User;
import data.UserIO;
import util.CookieUtil;

@WebServlet(name = "DownloadServlet", urlPatterns = {"/DownloadServlet"})
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "viewAlbums"; // default action
        }
        // perform action and set URL to appropriate page 
        String url = "/index.jsp";
        if (action.equals("viewAlbums")) {
            url = "/index.jsp";
        }
        if (action.equals("delete")) {
           
            deletePersistentCookies(request, response);
            url = "/view_cookies.jsp" ;
            HttpSession session = request.getSession();
            session.invalidate();
            
           
        } else if (action.equals("checkUser")) {
            url = checkUser(request, response);
        }
    
    
        // forward to the view 
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        // perform action and set URL to appropriate page 
        String url = "/index.jsp";
        if (action.equals("registerUser")) {
            url = registerUser(request, response);
        }
        // forward to the view 
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String checkUser(HttpServletRequest request, HttpServletResponse response) {

        String productCode = request.getParameter("productCode");
        HttpSession session = request.getSession();
        session.setAttribute("productCode", productCode);
        User user = (User) session.getAttribute("user");
        String url;
        // if User object doesn't exist, check email cookie
        if (user == null) {
            
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");
            // if cookie doesn't exist, go to Registration page 
            if (emailAddress == null || emailAddress.equals("")) {
                url = "/register.jsp";
            } // if cookie exists,
            // create User object and go to Downloads page
            else {
                ServletContext sc = getServletContext();
                String path = sc.getRealPath("/WEB-INF/EmailList.txt");
                user = UserIO.getUser(emailAddress, path);
                session.setAttribute("user", user);
                url = "/jspAlbums/" + productCode + "download.jsp";
            }
        } // if User object exists, go to Downloads page 
        else {
            url = "/jspAlbums/" + productCode + "download.jsp";
        }
        return url;
    }

    private String registerUser(HttpServletRequest request, HttpServletResponse response) {
        // get the user data
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        // store the data in a User object
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        // write the User object to a file
        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/WEB-INF/EmailList.txt");
        UserIO.add(user, path);
        // store the User object as a session attribute 
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        // add a cookie that stores the user's email to browser 
        Cookie c = new Cookie("emailCookie", email);
        //c.setMaxAge(60 * 60 * 24 * 365 * 2); // set age to 2 years 
        c.setMaxAge(60 * 60 * 24 * 365 * 3); // set age to 3 years 
        // c.setMaxAge(10); // set age to 10 seconds
        c.setPath("/");
        // allow entire app to access it
        response.addCookie(c);
        // create an return a URL for the appropriate Download page 
        String productCode = (String) session.getAttribute("productCode");
        String url = "/jspAlbums/" + productCode + "download.jsp";
        return url;
    }

    // Add this method to delete persistent cookies
    private void deletePersistentCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

         if (cookies != null) {
            for (Cookie cookie : cookies) {               
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
      
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
