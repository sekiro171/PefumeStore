<%-- 
    Document   : load
    Created on : Dec 22, 2023, 3:28:44 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> FPT Perfume Store</title>
        <link rel="icon" href="images/logo1.png"/>
    </head>
    <body>
        <c:forEach items="${requestScope.productPage}" var="i">
            <div class="product_items ${(requestScope.col == 4)?"col-lg-4":"col-lg-3"}" style="margin: 30px 0">
                <article class="single_product">
                    <figure>
                        <div class="product_thumb" onclick="openModal('modal_box',${i.id}, '${i.image[0]}', '${i.image[1]}',
                                                            '${i.name}',${i.salePrice},${i.price}, '${i.describe}', '${i.classifyStr}',
                                                            '${i.supplier.getCompanyName()}')">
                            <a href="#" class="primary_img" data-toggle="modal" data-target="#modal_box">
                                <img src="${i.image[0]}" alt="" style="width: 250px; height: 250px">
                            </a>
         

                        </div>
                        <figcaption class="product_content">
                            <h4 class="product_name">
                                <a href="#">${i.name}</a>
                            </h4>
                            <div class="product_rating" style="${requestScope.col == 4?"":"width: 100%;"}">
                              <ul>
                                                                <c:set var="numstar" value="${i.starRating}"/>
                                                                <c:forEach begin="1" end="${numstar}" step="1">
                                                                    <li>
                                                                        <a href="#" style="color: orange">
                                                                            <i class="fa fa-star"></i>
                                                                        </a>
                                                                    </li>
                                                                </c:forEach>
                                                                <c:if test="${numstar != 5}">
                                                                    <c:forEach begin="${numstar + 1}" end="5" step="1">
                                                                        <li>
                                                                            <a href="#" style="color: black">
                                                                                <i class="fa fa-star"></i>
                                                                            </a>
                                                                        </li>
                                                                    </c:forEach>
                                                                </c:if>

                                                                <div class="wishlist" style="margin-left: 20px;">                                                                    <a href="#" onclick="toggleWishlist(${i.id})" title="Add to Wishlist">
                                                                        <i style="color: #f6692a; border: 2px solid #f6692a; padding: 5px; border-radius: 5px;" class="fa-solid fa-heart"></i>

                                                                    </a>
                                                                </div>
                                                            </ul>
                            <div class="price_box">
                                <c:if test="${i.price != i.salePrice}">
                                    <span class="old_price">Rs. ${i.price}</span>
                                </c:if>
                                <span class="current_price">Rs. ${i.salePrice}</span>
                            </div>
                        </figcaption>
                    </figure>
                </article>
            </div>
        </c:forEach>
    </body>
</html>
