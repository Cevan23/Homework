package data;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import business.User;
import util.CookieUtil;

public class UserIO {

    public static User getUser(String emailAddress, String path) {
        User user = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(path));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(emailAddress)) {
                    user = new User();
                    user.setEmail(emailAddress);
                    user.setFirstName(parts[1]);
                    user.setLastName(parts[2]);
                    break; // User found, exit the loop
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }

    public static void add(User user, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            // Append the user information to the text file
            String line = user.getEmail() + "," + user.getFirstName() + "," + user.getLastName();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User user, HttpServletResponse response) {
        // You can customize this method to set user information in a cookie if needed
        // For example, you can set the email in a cookie named "emailCookie"
        // and customize the expiration time accordingly.

        Cookie emailCookie = new Cookie("emailCookie", user.getEmail());
        emailCookie.setMaxAge(60 * 60 * 24 * 365 * 3); // Set age to 3 years
        emailCookie.setPath("/");
        response.addCookie(emailCookie);
    }

    public static User getUser(HttpServletRequest request, HttpServletResponse response) {
        // Try to get the User object from the session
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            // If User object doesn't exist in session, try to retrieve it from the email cookie
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");

            if (emailAddress != null && !emailAddress.isEmpty()) {
                // If the email cookie exists, create a User object
                user = new User();
                user.setEmail(emailAddress);
                // Set default values for first name and last name (you can modify this as needed)
                user.setFirstName("");
                user.setLastName("");
                // Store the User object in the session
                request.getSession().setAttribute("user", user);
            }
        }
        return user;
    }
    
    

}
