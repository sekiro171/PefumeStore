<%-- 
    Document   : profile
    Created on : Dec 19, 2023, 5:20:39 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Profile</title>
        <link rel="icon" href="images/logo1.png"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.carousel.min.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css">
        <style type="text/css">
            body{
                background-color:#f2f6fc;
                color:#69707a;
            }
            .img-account-profile {
                height: 10rem;
            }
            .rounded-circle {
                width: 150px;
                border-radius: 50% !important;
            }
            .card .card-header {
                font-weight: 500;
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }



            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }

            body {
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                width: 100%;
                font-family: "Nunito", sans-serif;
                background-image: url(images/magic.gif);
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
            }

            .card{
                border: none;
                border-radius: 10px;
                width: 100%;
                margin-top: 10%;
            }

            .fa-ellipsis-v{
                font-size: 10px;
                color: #C2C2C4;
                margin-top: 6px;
                cursor: pointer;
            }
            .text-dark{
                font-weight: bold;
                margin-top: 8px;
                font-size: 13px;
                letter-spacing: 0.5px;
            }
            .card-bottom{
                background: #3E454D;
                border-radius: 6px;
            }
            .flex-column{
                color: #adb5bd;
                font-size: 13px;
            }
            .flex-column p{
                letter-spacing: 1px;
                font-size: 18px;
            }
            .btn-secondary{
                height: 40px!important;
                margin-top: 3px;
            }
            .btn-secondary:focus{
                box-shadow: none;
            }

        </style>

        <script>
            function acceptPayment() {
                alert("Hãy đợi admin xác minh chuyển khoản");
                window.location.href = "profile.jsp"; // Điều hướng về trang chính
            }
        </script>

        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <c:set var="user" value="${sessionScope.account}"/>
        <c:set var="imageSession" value="${sessionScope.imageUser}"/>
        <header style="background-color: rgba(0, 0, 0, 0.7); color: white; padding: 10px 0; font-family: Lato, sans-serif">
            <div class="row align-items-center" style="margin: 0">
                <div class="col-lg-2" style="padding: 0px">
                    <div class="logo">
                        <a href="HomeServlet"><img src="images/logoFPT.png" alt="" width="65px"></a>
                    </div>
                </div>
                <div class="col-lg-3" style="padding: 0px">
                    <div class="main_menu menu_two menu_position">
                        <nav>
                            <ul>
                                <li>
                                    <a href="HomeServlet">Home</a>
                                </li>
                                <li><a href="aboutUs.jsp">About Us</a></li>
                                <li>
                                    <a href="profile.jsp" class="active">Profile</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="col-lg-6" style="padding: 0px; display: flex; justify-content: flex-end; align-items: center">
                    <%@ include file="header_right.jsp"%>
                </div>
            </div>
        </header>
        <div class="container-xl ">

            <nav class="nav nav-borders">
                <!--                <a class="nav-link active ms-0" href="https://www.bootdey.com/snippets/view/bs5-edit-profile-account-details" target="__blank">Profile</a>
                                <a class="nav-link" href="https://www.bootdey.com/snippets/view/bs5-profile-billing-page" target="__blank">Billing</a>
                                <a class="nav-link" href="https://www.bootdey.com/snippets/view/bs5-edit-notifications-page" target="__blank">Notifications</a>-->
            </nav>
            <hr class="mt-0 mb-12">
            <div class="row">
                <div class="container d-flex justify-content-center align-items-center">
                    <div class="card shadow-lg p-5 text-center" style="max-width: 450px; width: 100%;">
                        <h2 class="mb-4">Trang Thanh Toán</h2>
                        <div class="d-flex justify-content-center">
                            <img src="images/Banking.png" alt="Thanh toán" class="img-fluid mb-4">
                        </div>
                        <div class="d-flex justify-content-around">
                            <button class="btn btn-success fw-bold" onclick="acceptPayment()"> Accept </button>
                            <button  class="btn btn-danger fw-bold" onclick="window.location.href = 'profile.jsp'">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modal_box" role="dialog"></div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>   
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/owl.carousel.min.js"></script>
        <script src="js/countdown.js"></script>
        <script src="js/clickevents.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
        <script src="js/main.js"></script>
        <script type="text/javascript">

        </script>
    </body>
</html>