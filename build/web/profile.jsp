<%-- 
    Document   : profile
    Created on : Dec 19, 2023, 5:20:39 PM
    Author     : Admin
--%>

<%@page import="model.Wallet"%>
<%@page import="model.User"%>
<%@page import="perfumeshopDAO.WalletDAO"%>
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

            #buttonVip2{
                display: none;
            }

            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
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
                background-image: url(images/backgrond2.png);
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

            .mainMenu{
                margin-left: 550px;
            }

        </style>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        
        <% WalletDAO walletDAO = new WalletDAO(); 
        User user = (User) session.getAttribute("account"); 
        Wallet wallet = walletDAO.getWalletByUserName(user.getUserName());%>
        
        <c:set var="user" value="${sessionScope.account}"/>
        <c:set var="imageSession" value="${sessionScope.imageUser}"/>
        <header style="background-color: rgba(0, 0, 0, 0.7); color: white; padding: 10px 0; font-family: Lato, sans-serif">
            <div class="row align-items-center" style="margin: 0">
                <div class="col-lg-2" style="padding: 0px">
                    <div class="logo">
                        <a href="HomeServlet"><img src="images/logoFPT.png" alt="" width="65px"></a>
                    </div>
                </div>
                <div class="col-lg-3 mainMenu" style="padding: 0px">
                    <div class="main_menu menu_two menu_position">
                        <nav>
                            <ul>
                                <li>
                                    <a href="RefineServlet">Home</a>
                                </li>
                                <li><a href="aboutUs.jsp">About Us</a></li>
                                <li>
                                    <a href="profile.jsp" class="active">Profile</a>
                                </li>
                                <li>
                                    <a href="ChangePassServlet">Security</a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="col-lg-3" style="padding: 0px; display: flex; justify-content: flex-end; align-items: center">
                    <%@ include file="header_right.jsp"%>
                </div>
            </div>
        </header>
        <div class="container-xl px-4 mt-4">


            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-xl-4">
                    <div class="card mb-4 mb-xl-0 shadow-sm border-0">
                        <div class="card-header bg-danger text-white text-center fw-bold rounded-top">
                            Profile Picture
                        </div>
                        <div class="card-body text-center box_info">
                            <c:choose>
                                <c:when test="${imageSession != null}">
                                    <img class="user_image rounded-circle border border-danger shadow-sm" 
                                         src="${imageSession}" 
                                         alt="User Image" 
                                         id="iUser" 
                                         style="width: 150px; height: 150px; object-fit: cover;" />
                                </c:when>
                                <c:otherwise>
                                    <img class="user_image rounded-circle border border-danger shadow-sm" 
                                         src="images/users/user.PNG" 
                                         alt="Default User" 
                                         id="iUser" 
                                         style="width: 150px; height: 150px; object-fit: cover;" />
                                </c:otherwise>
                            </c:choose>

                            <form id="fima" action="uploadImg" method="post" enctype="multipart/form-data" class="mt-3">
                                <label for="imageInput" class="edit d-block text-danger fw-semibold">
                                    JPG or PNG no larger than 5 MB <i class="fa-solid fa-pen-to-square ms-2"></i>
                                </label>
                                <input type="hidden" id="userid" name="uid" value="${user.userName}" />
                                <div class="mt-2">
                                    <input id="imageInput" 
                                           name="imageFile" 
                                           type="file" 
                                           accept="image/png, image/jpeg" 
                                           class="form-control form-control-sm border-danger" />
                                </div>
                                <button type="submit" class="btn btn-danger mt-3 shadow-sm">
                                    <i class="fa-solid fa-upload me-2"></i> Upload
                                </button>
                            </form>
                        </div>
                    </div>

                    <div class="container d-flex justify-content-center">
                        <div class="card p-3 shadow-sm border-0" style="margin: 10px 0; border-radius: 10px;">
                            <div class="d-flex flex-row justify-content-center align-items-center">
                                <img src="images/logoFPT.png" alt="Logo" width="30px" class="me-2">
                                <p class="text-dark fw-bold mb-0">Shopping Wallet</p>
                            </div>
                            <div class="card-bottom pt-3 px-3 mb-2">
                                <div class="d-flex flex-row justify-content-between align-items-center">
                                    <div class="d-flex flex-column">
                                        <span class="text-muted">Balance Amount</span>
                                        <p class="fw-bold text-danger fs-5 mb-0">
                                            VNĐ: <span class="text-danger"> <%= wallet.getBalance() %> </span>
                                        </p>
                                    </div>
                                    <button class="btn btn-secondary" data-toggle="modal" data-target="#modal_box" onclick="modalOpen2('modal_box', '${user.userName}',
                                                    '${imageSession}', <%= wallet.getBalance() %>)">
                                        <i class="fas fa-plus text-white"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xl-8" style="margin-top: -37px">
                    <div class="card mb-4 shadow-sm border-0">
                        <div class="card-header bg-danger text-white fw-bold rounded-top">YOUR PROFILE</div>
                        <div class="card-body">
                            <form method="post" action="ProfileServlet">
                                <div class="mb-3">
                                    <label class="mb-1" for="inputUsername">Username</label>
                                    <input class="form-control border-danger" id="inputUsername" name="username" readonly type="text" placeholder="Enter your username" value="${user.userName}">
                                </div>
                                <div class="gx-3 mb-3">
                                    <label class="mb-1" for="inputFirstName">Full name</label>
                                    <c:if test="${sessionScope.name!=null}">
                                        <input class="form-control acceptEdit border-danger" readonly name="name" id="inputFirstName" type="text" placeholder="Full Name" value="${sessionScope.name}">
                                    </c:if>
                                </div>

                                <div class="row gx-3 mb-3">
                                    <div class="col-md-6">
                                        <label class="mb-1" for="inputOrgName">Role</label>
                                        <c:choose>
                                            <c:when test="${user.roleID == 2}">
                                                <input class="form-control border-danger" id="inputOrgName" readonly type="text" value="Customer">
                                            </c:when>
                                            <c:otherwise>
                                                <input class="form-control border-danger" id="inputOrgName" readonly type="text" value="Admin">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="mb-1" for="inputLocation">Address</label>
                                        <c:if test="${sessionScope.address!=null}">
                                            <input class="form-control acceptEdit border-danger" readonly name="address" id="inputLocation" type="text" placeholder="Address" value="${sessionScope.address}">
                                        </c:if>
                                    </div>
                                </div>

                                <div class="mb-3">
                                    <label class="mb-1" for="inputEmailAddress">Email address</label>
                                    <c:if test="${sessionScope.email!=null}">
                                        <input class="form-control acceptEdit border-danger" readonly name="email" id="inputEmailAddress" type="text" placeholder="Email" value="${sessionScope.email}">
                                    </c:if>
                                </div>

                                <div class="row gx-3 mb-3">
                                    <div class="col-md-6">
                                        <label class="mb-1" for="inputPhone">Phone number</label>
                                        <c:if test="${sessionScope.phone!=null}">
                                            <input class="form-control acceptEdit border-danger" readonly name="phone" id="inputPhone" type="text" placeholder="Phone" value="${sessionScope.phone}">
                                        </c:if>
                                    </div>
                                    <div class="col-md-6">
                                        <label class="mb-1" for="inputBirthday">Birthdate</label>
                                        <c:if test="${sessionScope.birthdate!=null}">
                                            <input class="form-control acceptEdit border-danger" readonly name="birthday" id="inputBirthday" type="text" placeholder="Birthdate" value="${sessionScope.birthdate}">
                                        </c:if>
                                    </div>
                                </div>
                                <button class="btn btn-danger shadow-sm" onclick="acceptRead()" id="buttonVip" type="button">Edit Profile</button>
                                <button class="btn btn-danger shadow-sm mt-3 px-4" onclick="notAccept()" id="buttonVip2" type="submit">Save</button>
                            </form>
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
                    document.addEventListener("DOMContentLoaded", function () {
                        const inputFile = document.querySelector("#imageInput");
                        const imgPreview = document.querySelector(".box_info .avatar img");

                        inputFile.addEventListener("change", function (event) {
                            const file = this.files[0];

                            if (file) {
                                // Kiểm tra loại file (JPG, PNG)
                                if (!["image/jpeg", "image/png"].includes(file.type)) {
                                    alert("Chỉ chấp nhận tệp JPG hoặc PNG!");
                                    return;
                                }

                                // Kiểm tra kích thước file (≤ 5MB)
                                if (file.size > 5 * 1024 * 1024) {
                                    alert("File quá lớn! Vui lòng chọn ảnh dưới 5MB.");
                                    return;
                                }

                                // Hiển thị ảnh xem trước
                                const reader = new FileReader();
                                reader.onload = function (e) {
                                    imgPreview.src = e.target.result;
                                };
                                reader.readAsDataURL(file);
                            }
                        });
                    });

        </script>
    </body>
</html>