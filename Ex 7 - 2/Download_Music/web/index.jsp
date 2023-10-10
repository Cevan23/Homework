<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Murach's Java Servlets and JSP</title>
        <link rel="stylesheet" href="styles/index.css" type="text/css"/>
    </head>
    <body>
        <h1>List of Albums</h1>

        <div class="container">
            <p>User Email: ${cookie.emailCookie.value}</p>

            <p>
                <a href="download?action=checkUser&amp;productCode=8601">86 (the band) - True Life Songs and Pictures</a><br>
                <a href="download?action=checkUser&amp;productCode=pf01">Paddlefoot - The First CD</a><br>
                <a href="download?action=checkUser&amp;productCode=pf02">Paddlefoot The Second CD</a><br>
                <a href="download?action=checkUser&amp;productCode=jr01">Joe Rut Genuine Wood Grained Finish</a>
            </p>

            <a href="download?action=delete" class="delete-link">Delete All Persistent Cookies</a>
        </div>

    </body>
</html>