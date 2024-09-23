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
                    <title>Product table</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
                        rel="stylesheet">
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                    <script>
                        function toggleCheckboxes(source) {
                            const checkboxes = document.querySelectorAll('input[name="selectDel"]:not([value="0"])');
                            checkboxes.forEach(checkbox => {
                                checkbox.checked = source.checked;
                            });
                        }
                    </script>
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
                                                <li class="breadcrumb-item active" aria-current="page">Product</li>
                                            </ol>
                                        </nav>
                                        <div class="row">
                                            <form action="/admin/product/delete" method="post">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                                <div class="col-md-12 mx-auto">
                                                    <div class="content d-flex justify-content-between">
                                                        <h3>Table products</h3>
                                                        <div class="method">
                                                            <a href="/admin/product/create"
                                                                class="btn btn-primary">Create a
                                                                product</a>
                                                            <button class="btn btn-danger">Delete</button>
                                                        </div>
                                                    </div>
                                                    <hr>
                                                    <table class="table table-bordered">
                                                        <input type="hidden" name="selectDel" value="-1">
                                                        <thead>
                                                            <tr>
                                                                <th><input type="checkbox" name="selectDel" value="0"
                                                                        onclick="toggleCheckboxes(this)">
                                                                </th>
                                                                <th scope="col">ID</th>
                                                                <th scope="col">Name</th>
                                                                <th scope="col">Price</th>
                                                                <th scope="col">Factory</th>
                                                                <th scope="col">Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach var="product" items="${products}">
                                                                <tr>
                                                                    <td>
                                                                        <input type="checkbox" name="selectDel"
                                                                            value="${product.id}">
                                                                    </td>
                                                                    <td scope="col">${product.id}</td>
                                                                    <td>${product.name}</td>
                                                                    <td>
                                                                        <fmt:formatNumber type="number"
                                                                            value="${product.price}" /> đ
                                                                    </td>
                                                                    <td>${product.factory}</td>
                                                                    <td>
                                                                        <a href="/admin/product/${product.id}"
                                                                            class="btn btn-success">View</a>
                                                                        <a href="/admin/product/update/${product.id}"
                                                                            class="btn btn-warning">Update</a>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>

                                                    <nav aria-label="Page navigation example">
                                                        <ul class="pagination justify-content-center">
                                                            <li
                                                                class="${currentPage - 1 == 0 ? 'disabled page-item' : 'page-item'}">
                                                                <a class="page-link"
                                                                    href="/admin/product?page=${currentPage - 1}"
                                                                    aria-label="Previous">
                                                                    <span aria-hidden="true">&laquo;</span>
                                                                </a>
                                                            </li>
                                                            <li
                                                                class="${currentPage - 1 == 0 ? 'd-none' : 'page-item'}">
                                                                <a class="page-link"
                                                                    href="/admin/product?page=${currentPage - 1}">
                                                                    ${currentPage - 1}</a>
                                                            </li>
                                                            <li class="page-item">
                                                                <a class="page-link"
                                                                    href="/admin/product?page=${currentPage}">
                                                                    ${currentPage}
                                                                </a>
                                                            </li>
                                                            <li
                                                                class="${currentPage + 1 > totalPages ? 'd-none' : 'page-item'}">
                                                                <a class="page-link"
                                                                    href="/admin/product?page=${currentPage + 1}">
                                                                    ${currentPage + 1}</a>
                                                            </li>
                                                            <li
                                                                class="${currentPage + 1 > totalPages ? 'disabled page-item' : 'page-item'}">
                                                                <a class="page-link"
                                                                    href="/admin/product?page=${currentPage + 1}"
                                                                    aria-label="Next">
                                                                    <span aria-hidden="true">&raquo;</span>
                                                                </a>
                                                            </li>
                                                        </ul>
                                                    </nav>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </main>
                        </div>
                </body>
                </div>