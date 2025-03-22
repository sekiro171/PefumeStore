<%-- 
    Document   : viewcart
    Created on : Mar 7, 2025, 8:51:24 PM
    Author     : Sekiro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart    </title>
        <link rel="icon" href="images/logo1.png"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdn.tailwindcss.com/3.3.2"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"/>
        <style>
            body {
                color: #000;
                overflow-x: hidden;
                height: 100%;
                background-color: #fff;
                background-repeat: no-repeat;
            }
            .book, .book-img {
                width: 120px;
                height: 180px;
                border-radius: 5px;
            }

            .book {
                margin: 20px 15px 5px 15px;
            }

            .border-top {
                border-top: 1px solid #EEEEEE !important;
                margin-top: 20px;
                padding-top: 15px;
            }

            .card {
                margin: 40px 0px;
                padding: 40px 50px;
                border-radius: 20px;
                border: none;
                box-shadow: 1px 5px 10px 1px rgba(0,0,0,0.2);
            }

            input, textarea {
                background-color: #F3E5F5;
                padding: 8px 15px 8px 15px;
                width: 100%;
                border-radius: 5px !important;
                box-sizing: border-box;
                border: 1px solid #F3E5F5;
                font-size: 15px !important;
                color: #000 !important;
                font-weight: 300;
            }

            input:focus, textarea:focus {
                -moz-box-shadow: none !important;
                -webkit-box-shadow: none !important;
                box-shadow: none !important;
                border: 1px solid #9FA8DA;
                outline-width: 0;
                font-weight: 400;
            }

            button:focus {
                -moz-box-shadow: none !important;
                -webkit-box-shadow: none !important;
                box-shadow: none !important;
                outline-width: 0;
            }

            .btn-blue {
                border: none;
                border-radius: 10px;
                background-color: #673AB7;
                color: #fff;
                padding: 8px 15px;
                margin: 20px 0px;
                cursor: pointer;
            }

            .btn-blue:hover {
                background-color: #311B92;
                color: #fff;
            }

            #checkout {
                float: left;
            }

            @media screen and (max-width: 768px) {
                .card {
                    padding-left: 15px;
                    padding-right: 15px;
                }

                .mob-text {
                    font-size: 13px;
                }

                .pad-left {
                    padding-left: 20px;
                }
            }
        </style>
    </head>
    <body>
        <header style="background-color: black; padding: 15px 40px; display: flex; justify-content: space-between; align-items: center">
            <div class="col-lg-2">
                <div class="logo">
                    <a href="RefineServlet"><img src="images/logoFPT.png" alt="" width="65px"></a>
                </div>
            </div>
            <div class="col-lg-10">
                <jsp:include page="header_right.jsp"></jsp:include>
                </div>
            </header>

            <div id="viewcart_content">

                <div class="container px-4 py-5 mx-auto text-center">
                <c:if test="${requestScope.message1 == 'Order Success'}">
                    <h2 style="color: green; text-align: center">${requestScope.message1}</h2>
                </c:if>
                <c:if test="${requestScope.message1 == 'Order Fail'}">
                    <h2 style="color: red; text-align: center">${requestScope.message1}</h2>
                    <h4 style="color: red; text-align: center">${requestScope.message2}</h4>
                </c:if>
                <c:if test="${sessionScope.listItemsInCart == null || sessionScope.cartSize == 0}">
                    <img src="images/emptycart1.png" width="400px"  alt="Emptycart"/>
                </c:if>
                <c:if test="${sessionScope.cartSize != 0}">
                    <div class="row d-flex justify-content-center">
                        <div class="col-4">
                            <h4 class="heading">Shopping Bag</h4>
                        </div>
                        <div class="col-8">
                            <div class="row text-right">
                                <div class="col-3">
                                    <h6 class="mt-2">Supplier</h6>
                                </div>
                                <div class="col-3">
                                    <h6 class="mt-2">Quantity</h6>
                                </div>
                                <div class="col-3">
                                    <h6 class="mt-2">Price</h6>
                                </div>
                                <div class="col-3" style="padding-right: 30px">
                                    <h6 class="mt-2">Delete</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:forEach items="${sessionScope.listItemsInCart}" var="item">
                        <div class="row d-flex justify-content-center border-top">
                            <div class="col-4">
                                <div class="row">
                                    <div class="book col-7" style="margin-right: 30px; flex: 1">
                                        <img src="${item.product.image[0]}" style="width: 170px; height: 150px" class="book-img">
                                    </div>
                                    <div class="my-auto flex-column d-flex pad-left col-5">
                                        <h6 class="mob-text">${item.product.name}</h6>
                                    </div>
                                </div>
                            </div>
                            <div class="my-auto col-8 ">
                                <div class="row text-right">
                                    <div class="col-3">
                                        <p class="mob-text">${item.product.supplier.companyName}</p>
                                    </div>
                                    <div class="col-3">
                                        <h6>${item.quantity}</h6>
                                    </div>
                                    <div class="col-3">
                                        <h6 class="mob-text number"><fmt:formatNumber value="${item.price}" type="number" pattern="0"/> </h6>  
                                    </div>
                                    <div class="col-3">
                                        <a  href="ViewCartServlet?rid=${item.product.id}" 
                                            style="padding: 8px 15px; border: none; border-radius: 5px; background-color: #1763c7; color: white">
                                            Delete
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

                <div class="row justify-content-center">
                    <div class="col-lg-12" style="padding: 0">
                        <div class="card">
                            <div class="row">
                                <div class="col-lg-12 mt-2">
                                    <div class="row d-flex justify-content-between px-4">
                                        <p class="mb-1 text-left">Subtotal</p>
                                        <h6 class="mb-1 text-right number">
                                            <fmt:formatNumber value=" ${sessionScope.cart.getTotalMoney()}" type="number" pattern="0"/>
                                        </h6> 
                                    </div>
                                    <div class="row d-flex justify-content-between px-4">
                                        <p class="mb-1 text-left">Shipping</p>
                                        <h6 class="mb-1 text-right number">
                                            <c:choose>
                                                <c:when test="${sessionScope.cart == null || sessionScope.cartSize == 0}">
                                                    0
                                                </c:when>
                                                <c:otherwise>
                                                  <fmt:formatNumber value="30000" type="number" pattern="0"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </h6>

                                    </div>
                                    <div class="row d-flex justify-content-between px-4" id="tax">
                                        <p class="mb-1 text-left">Total (tax included)</p>
                                        <h6 class="mb-1 text-right number" style="font-size: 18px; font-weight: bold;">
                                            <c:choose>
                                                <c:when test="${sessionScope.cart == null || sessionScope.cartSize == 0}">
                                                    0
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:formatNumber value="  ${sessionScope.cart.getTotalMoney() + 30000} " type="number" pattern="0"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </h6>
                                    </div>
                                    <span>
                                        <form action="ViewCartServlet" method="post">
                                            <input class="btn-block btn-blue" type="submit" value="CHECKOUT" id="checkout"
                                                   style="color: white; font-weight: bold; background-color: orange"/>
                                        </form>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="footer.jsp"%>
        <div class="modal fade" id="modal_box" role="dialog"></div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script type="text/javascript" src="js/clickevents.js"></script>
        <script type="text/javascript">
            function formatPrice(element) {
                let price = element.innerText.trim(); // Lấy giá trị trong thẻ
                let formattedPrice = parseInt(price, 10).toLocaleString("vi-VN"); // Chuyển đổi sang định dạng VN
                element.innerText = formattedPrice; // Gán lại nội dung đã format
            }

            // Lặp qua tất cả các phần tử có class "old_price" và "current_price"
            document.querySelectorAll(".number").forEach(formatPrice);
        </script>
    </body>
</html>
