<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>UniConnect - Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-dark text-light d-flex flex-column min-vh-100">

        <nav class="navbar navbar-dark bg-dark px-4">
            <span class="navbar-text fs-5">Welcome, <span id="username">${username}!</span></span>
            <a href="index.html" class="btn btn-outline-light">Logout</a>
        </nav>

        <div class="flex-fill d-flex justify-content-center align-items-center text-center">
            <div>
                <h1 class="display-3 fw-bold">UniConnect</h1>
                <p class="fs-5 mb-4">Start chatting or capture moments with other students and staff in real time.</p>
                <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                    <a href="ConversationsServlet" class="btn btn-outline-light btn-lg px-4 me-sm-3 fw-bold">Start Chat</a>
                    <a href="CameraServlet" class="btn btn-warning btn-lg px-4 fw-bold">Camera</a>
                </div>
            </div>
        </div>
    </body>
</html>
