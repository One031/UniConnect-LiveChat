<%@ page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String username = (String) session.getAttribute("user");
    if (username == null) {
        response.sendRedirect("login.html"); // Redirect if not logged in
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UniConnect - Camera</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr" crossorigin="anonymous">
    </head>
    <body class="bg-dark text-white">
        
        <h1 class="text-center mb-5 mt-3">Camera</h1>
        
        <div class="container d-flex justify-content-center">
            <div class="card bg-light shadow p-3">
                <video id="cameraStream" autoplay playsinline class="w-100 rounded"></video>
            </div>
        </div>
        
        <div class="text-center mt-4">
            <a href="HomeServlet" class="btn btn-outline-light">Back to Home</a>
        </div>
        
        <!-- JavaScript to access the camera -->
        <script>
            // Get reference to the video element
            const video = document.getElementById("cameraStream");
            
            // Request access to the user's camera, no audio
            navigator.mediaDevices.getUserMedia({video: true, audio: false }).then(
                    stream => {
                        // If successful, attach the camera stream to the video element
                        video.srcObject = stream;
                    })
                            .catch(error => {
                                // If an error occurs show an alert 
                                alert("Error accessing camera: " + error.message);
                    });
        </script>
    </body>
</html>
