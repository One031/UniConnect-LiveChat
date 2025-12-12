<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-dark d-flex align-items-center justify-content-center vh-100">
        <div class="card shaow-lg p-4" style="max-width: 400px; width: 100%">
            <h2 class="text-center mb-4">Login</h2>
            
             <!-- ERROR MESSAGE -->
        <%
            String error = (String) request.getAttribute("errorMessage");
            if (error != null) {
        %>
            <div class="alert alert-danger text-center"><%= error %></div>
        <%
            }
        %>

            
            <form action="LoginServlet" method="post">
                <div class="mb-3">
                    <label for="username" class="form-lavel fw-bold">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                
                <div class="mb-3">
                    <label for="password" class="form-lavel fw-bold">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                
                <div class="d-grid">
                    <button type="submit" class="btn btn-dark fw-bold">Login</button>
                </div>
            </form>
            
            <p class="text-center mt-3 mb-0">
                Don't have an account? <a href='registration.jsp'>Register</a>
            </p>
        </div>
    </body>
</html>
