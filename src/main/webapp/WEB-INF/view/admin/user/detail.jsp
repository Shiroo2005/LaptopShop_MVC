<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Detail user</title>
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <div id="layoutSidenav_nav">
                        <jsp:include page="../layout/sidebar.jsp" />
                    </div>
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <br>
                                <br>
                                <div class="container mt-5">
                                    <nav aria-label="breadcrumb">
                                        <ol class="breadcrumb">
                                            <li class="breadcrumb-item"><a href="/admin">Home</a></li>
                                            <li class="breadcrumb-item"><a href="/admin/user">User</a></li>
                                            <li class="breadcrumb-item active" aria-current="page">User ID: ${user.id}
                                            </li>
                                        </ol>
                                    </nav>
                                    <div class="row">
                                        <div class="col-md-12 mx-auto" modelAttribute=${user}>
                                            <h3>Detail user</h3>
                                            <hr />
                                            <div class="card col-8">
                                                <div class="card-header">
                                                    User information
                                                </div>
                                                <ul class="list-group list-group-flush">
                                                    <li class="list-group-item">ID: ${user.id}</li>
                                                    <li class="list-group-item">Email: ${user.email}</li>
                                                    <li class="list-group-item">Full Name: ${user.fullName}</li>
                                                    <li class="list-group-item">Address: ${user.address}</li>
                                                </ul>
                                            </div>
                                            <div class="col-2 mt-2"><a href="/admin/user"
                                                    class="btn btn-success">Back</a></div>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </main>
                    </div>
            </body>
            </div>

            </html>