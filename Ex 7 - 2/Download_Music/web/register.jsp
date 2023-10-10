<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Murach's Java Servlets and JSP</title>
        <link rel="stylesheet" href="styles/register.css" type="text/css"/>
        <script>
            function validateForm() {
                var email = document.forms["registrationForm"]["email"].value;
                var firstName = document.forms["registrationForm"]["firstName"].value;
                var lastName = document.forms["registrationForm"]["lastName"].value;

                // Regular expression to validate email format
                var emailPattern = /^[a-zA-Z0-9._-]+@gmail\.com$/;

                if (!emailPattern.test(email)) {
                    alert("Email must be in the format XXXXX@gmail.com");
                    return false;
                }
                if (email === "" || firstName === "" || lastName === "") {
                    alert("All fields must be filled out");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <h1>Download Registration</h1>
        <div class="container">
            <p>To register for our downloads, enter your name and email address below. Then, click on the Submit button.</p>
            <form name="registrationForm" action="download" method="post" onsubmit="return validateForm()">
                <input type="hidden" name="action" value="registerUser">

                <label for="email">Email:</label>
                <input type="email" name="email" id="email" value="${user.email}">

                <label for="firstName">First Name:</label>
                <input type="text" name="firstName" id="firstName" value="${user.firstName}">

                <label for="lastName">Last Name:</label>
                <input type="text" name="lastName" id="lastName" value="${user.lastName}">

                <input type="submit" value="Register">
            </form>
            <div class="error" id="error"></div>
        </div>
    </body>
</html>
