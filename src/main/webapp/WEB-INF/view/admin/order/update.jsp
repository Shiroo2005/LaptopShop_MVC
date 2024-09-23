<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                    <meta name="author" content="Hỏi Dân IT" />
                    <title>Update order</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
                        rel="stylesheet">
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
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
                                                <li class="breadcrumb-item"><a href="/admin/product">Product</a></li>
                                                <li class="breadcrumb-item active" aria-current="page">Product ID:
                                                    ${order.id}</li>
                                            </ol>
                                        </nav>
                                        <div class="row">
                                            <div class="col-md-6 col-12 mx-auto mt-5">
                                                <h3>Update a order</h3>
                                                <hr />
                                                <form:form action="/admin/order/update" method="post"
                                                    modelAttribute="order">
                                                    <input type="hidden" name="${_csrf.parameterName}"
                                                        value="${_csrf.token}">

                                                    <form:input path="id" hidden="true" />
                                                    <div class="row mb-3">
                                                        <div class="col-6">
                                                            Order id = ${order.id}
                                                        </div>
                                                        <div class="col-6">
                                                            Price:
                                                            <fmt:formatNumber type="number"
                                                                value="${order.totalPrice}" /> đ
                                                            <input type="hidden" value="${order.totalPrice}"
                                                                name="totalPrice">
                                                        </div>
                                                    </div>
                                                    <div class="row mb-5">
                                                        <div class="col-6">
                                                            <label class="mb-1">User:</label>
                                                            <input class="form-control" value="Admin full" disabled
                                                                readonly>
                                                        </div>

                                                        <div class="col-6">
                                                            <label class="mb-1">Status:</label>
                                                            <form:select path="status" class="form-select">
                                                                <form:option value="PENDING">PENDING</form:option>
                                                                <form:option value="SHIPPING">SHIPPING</form:option>
                                                                <form:option value="COMPLETE">COMPLETE</form:option>
                                                                <form:option value="CANCEL">CANCEL</form:option>
                                                            </form:select>
                                                        </div>


                                                    </div>
                                                    <a href="/admin/order" class="btn btn-success">Back</a>
                                                    <button type="submit" class="btn btn-primary">Update</button>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                        </div>
                        </main>
                    </div>
                </body>
                </div>

                </html>