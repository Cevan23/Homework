
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Murach's Java Servlets and JSP</title> <link rel="stylesheet" href="styles/Albums.css"  type="text/css" />
    </head>
    <body>
        <h1>Downloads</h1>
        <!--        <h2>Joe Rut Genuine Wood Grained Finish</h2>-->
        <h2>${product.description}</h2>
        
        <table class="downloads-table">
            <tr>
                <th>Song title</th>
                <th>Audio Format</th>
            </tr>
            <tr>
                <td>Filter</td>
                <td>
                    <a href="/musicstore/sound/${productCode}/star.mp3" class="audio-link">MP3</a>                    
                </td>
                <td>
                    <a href="https://www.youtube.com/watch?v=sLV3U8nxpjk&ab_channel=86theBand-Topic" target="_blank" class="video-link">MP4</a>
                </td>
            </tr>
            <tr>
               <td>So Long Lazy Ray</td>
                <td>
                    <a href="/musicstore/sound/${productCode}/no_difference.mp3" class="audio-link">MP3</a>
                </td>
                <td>
                    <a href="https://www.youtube.com/watch?v=sLV3U8nxpjk&ab_channel=86theBand-Topic" target="_blank" class="video-link">MP4</a>
                </td>
            </tr>
        </table>

        <a href="/Download_Music/ViewCookiesServlet" class="view-cookies-link">View All Cookies</a>

        <a href="download?action=delete" class="delete-cookies-link">Delete All Persistent Cookies</a>
    </body>
</html>