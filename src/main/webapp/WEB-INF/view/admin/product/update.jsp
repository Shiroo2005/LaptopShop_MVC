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
                    <title>Update product</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
                        rel="stylesheet">
                    <link href="/css/styles.css" rel="stylesheet" />
                    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"
                        crossorigin="anonymous"></script>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                    <script>
                        $(document).ready(() => {
                            const avatarFile = $("#avatarFile");
                            const orgImage = "${product.image}";
                            if (orgImage && !avatarFile.val()) {
                                const urlImage = "/images/product/" + orgImage;
                                $("#avatarPreview").attr("src", urlImage);
                                $("#avatarPreview").css({ "display": "block" });
                            }

                            avatarFile.change(function (e) {
                                const imgURL = URL.createObjectURL(e.target.files[0]);
                                $("#avatarPreview").attr("src", imgURL);
                                $("#avatarPreview").css({ "display": "block" });
                            });

                        });

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
                                                <li class="breadcrumb-item"><a href="/admin/product">Product</a></li>
                                                <li class="breadcrumb-item active" aria-current="page">Product ID:
                                                    ${product.id}</li>
                                            </ol>
                                        </nav>
                                        <div class="row">
                                            <div class="col-md-6 col-12 mx-auto">
                                                <h3>Update a product</h3>
                                                <hr />
                                                <form:form action="/admin/product/update" method="post"
                                                    modelAttribute="product" enctype="multipart/form-data">
                                                    <form:input hidden="true" path="id" />
                                                    <c:set var="errorName">
                                                        <form:errors path="name" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <c:set var="errorPrice">
                                                        <form:errors path="price" cssClass="invalid-feedback" />
                                                    </c:set>

                                                    <c:set var="errorShortDesc">
                                                        <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <c:set var="errorQuantity">
                                                        <form:errors path="quantity" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <c:set var="errorDetailDesc">
                                                        <form:errors path="detailDesc" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <div class="row">
                                                        <div class="mb-3 col-6">
                                                            <label class="form-label">Name:</label>
                                                            <form:input
                                                                class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                                                                path="name" />
                                                        </div>
                                                        <div class="mb-3 col-6">
                                                            <label class="form-label">Price:</label>

                                                            <form:input type="number"
                                                                class="form-control ${not empty errorPrice? 'is-invalid' : ''}"
                                                                path="price" />
                                                            ${errorPrice}
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="mb-3">

                                                            <label class="form-label">Detail description:</label>
                                                            <form:textarea type="text"
                                                                class="form-control ${not empty errorDetailDesc? 'is-invalid' : ''}"
                                                                path="detailDesc" />
                                                            ${errorDetailDesc}
                                                        </div>


                                                    </div>
                                                    <div class="row">
                                                        <div class="mb-3 col-6">
                                                            <label class="form-label">Short description:</label>
                                                            <form:input type="text"
                                                                class="form-control ${not empty errorShortDesc? 'is-invalid' : ''}"
                                                                path="shortDesc" />
                                                            ${errorShortDesc}
                                                        </div>
                                                        <div class="mb-3 col-6">

                                                            <label class="form-label">Quantity:</label>
                                                            <form:input type="number"
                                                                class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}"
                                                                path="quantity" />
                                                            ${errorQuantity }
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="mb-3 col-6">
                                                            <label class="form-label">Target:</label>
                                                            <form:select class="form-select" path="target">
                                                                <form:option value="ADMIN">ADMIN</form:option>
                                                                <form:option value="USER">USER</form:option>
                                                            </form:select>
                                                        </div>
                                                        <div class="mb-3 col-6">
                                                            <label class="form-label">Factory:</label>
                                                            <form:select class="form-select" path="factory">
                                                                <form:option value="MSI">MSI</form:option>
                                                                <form:option value="USER">USER</form:option>
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="mb-3 col-6">
                                                            <label for="formFile" class="form-label">Image:</label>
                                                            <input class="form-control" type="file" id="avatarFile"
                                                                accept=".jpg, .png, .jpeg" name="avatarFile" />
                                                        </div>


                                                        <div class="col-12 mb-3">
                                                            <img style="max-height: 250px; display: none;"
                                                                id="avatarPreview">
                                                        </div>
                                                    </div>
                                                    <a href="/admin/product"
                                                        class="btn btn-success d-inline-block">Back</a>
                                                    <button type="submit"
                                                        class="btn btn-primary d-inline-block">Create</button>
                                                </form:form>
                                            </div>

                                        </div>

                                    </div>
                                </div>
                            </main>
                        </div>
                </body>

                </html>