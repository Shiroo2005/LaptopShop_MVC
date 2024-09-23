<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                    <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                    <meta name="author" content="Hỏi Dân IT" />
                    <title>Detail order</title>
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

                                    <div class="container mt-5">
                                        <br>
                                        <br>
                                        <nav aria-label="breadcrumb">
                                            <ol class="breadcrumb">
                                                <li class="breadcrumb-item"><a href="/admin">Home</a></li>
                                                <li class="breadcrumb-item"><a href="/admin/order">Order</a></li>
                                                <li class="breadcrumb-item active" aria-current="page">Order ID:
                                                    ${order.id}
                                                </li>
                                            </ol>
                                        </nav>
                                        <div class="row">
                                            <div class="col-md-12 mx-auto">
                                                <div class="content d-flex justify-content-between">
                                                    <h3>Table users</h3>
                                                </div>
                                                <hr>
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th scope="col">Sản phẩm</th>
                                                            <th scope="col">Tên</th>
                                                            <th scope="col">Giá cả</th>
                                                            <th scope="col">Số lượng</th>
                                                            <th scope="col">Thành tiền</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="orderDetail" items="${orderDetails}">
                                                            <tr>
                                                                <td>
                                                                    <img style="width: 100px; height: 100px;border-radius: 50%;"
                                                                        src="/images/product/${orderDetail.product.image}"
                                                                        class="img-fluid" alt="Sản phẩm">
                                                                </td>
                                                                <td>${orderDetail.product.name}</td>
                                                                <td>
                                                                    <fmt:formatNumber type="number"
                                                                        value="${orderDetail.product.price}" /> đ
                                                                </td>
                                                                <td>${orderDetail.quantity}</td>
                                                                <td>
                                                                    <fmt:formatNumber type="number"
                                                                        value="${orderDetail.totalPrice}" /> đ
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <div class="col-2 mt-4"><a href="/admin/order"
                                                        class="btn btn-success">Back</a></div>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </main>
                        </div>
                    </div>


                </body>


                </html>