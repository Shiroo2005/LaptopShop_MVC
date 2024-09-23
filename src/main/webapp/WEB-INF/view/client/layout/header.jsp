<!-- Navbar start -->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                <div class="container-fluid fixed-top">
                    <div class="container px-5">
                        <nav class="navbar navbar-light bg-white navbar-expand-xl">
                            <a href="/" class="navbar-brand">
                                <h1 class="text-primary display-6">LaptopShop</h1>
                            </a>
                            <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarCollapse">
                                <span class="fa fa-bars text-primary"></span>
                            </button>
                            <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                                <div class="navbar-nav mx-auto">
                                    <a href="/" class="nav-item nav-link active">Trang chủ</a>
                                    <a href="/product" class="nav-item nav-link">Sản phẩm</a>
                                </div>
                                <div class="d-flex m-3 me-0">
                                    <c:if test="${not empty pageContext.request.userPrincipal}">
                                        <a href="/cart" class="position-relative me-4 my-auto">
                                            <i class="fa fa-shopping-bag fa-2x"></i>
                                            <c:if test="${sessionScope.sum != 0}">
                                                <span
                                                    class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                                    style="top: -5px; left: 15px; height: 20px; min-width: 20px;">${sessionScope.sum}
                                                </span>
                                            </c:if>
                                        </a>

                                        <div class="dropdown my-auto">
                                            <a href="#" class="dropdown" role="button" id="dropdownMenuLink"
                                                data-bs-toggle="dropdown" aria-expanded="false"
                                                data-bs-toggle="dropdown" aria-expanded="false">
                                                <i class="fas fa-user fa-2x"></i>
                                            </a>
                                            <ul class="dropdown-menu dropdown-menu-end p-4"
                                                ariaxlabelledby="dropdownMenuLink">
                                                <li class="d-flex align-items-center flex-column"
                                                    style="min-width: 300px;">
                                                    <img style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden;"
                                                        src="/images/avatar/${sessionScope.avatar}" />
                                                    <div class="text-center my-3">
                                                        Hello,
                                                        <b style="color: darkslategrey; font-style: italic;">
                                                            <c:out value="${sessionScope.fullname}" />
                                                        </b>
                                                    </div>

                                                </li>
                                                <li><a class="dropdown-item" href="#">Quản lý tài khoản</a></li>
                                                <li><a class="dropdown-item" href="/order-history">Lịch sử mua hàng</a>
                                                </li>
                                                <c:if test="${sessionScope.role == 'ADMIN'}">
                                                    <li><a class="dropdown-item" href="/admin">Trang admin</a></li>
                                                </c:if>
                                                <li>
                                                    <hr class="dropdown-divider">
                                                </li>
                                                <li>
                                                    <form action="/logout" method="post">
                                                        <input type="hidden" name="${_csrf.parameterName}"
                                                            value="${_csrf.token}" />
                                                        <button class="dropdown-item">Đăng xuất</button>
                                                    </form>
                                                </li>
                                            </ul>
                                        </div>
                                    </c:if>
                                    <c:if test="${ empty pageContext.request.userPrincipal}">
                                        <a href="/login">Đăng nhập</a>
                                    </c:if>
                                </div>
                            </div>
                        </nav>
                    </div>
                </div>
                <!-- Navbar End -->