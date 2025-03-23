<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> FPT Perfume Store</title>
        <link rel="icon" href="images/logo1.png"/>
    </head>
    <body>
        <c:forEach items="${requestScope.productPage}" var="i">
            <div class="product_items col-lg-3" style="margin: 20px 20px;">
                <article class="single_product">
                    <figure>
                        <div class="product_thumb" onclick="openModal('modal_box',${i.id}, '${i.image[0]}', '${i.image[1]}',
                                                                    '${i.name}',${i.salePrice},${i.price}, '${i.describe}', '${i.classifyStr}',
                                                                    '${i.supplier.getCompanyName()}')" 
                             <a href="#" class="primary_img"  data-toggle="modal" data-target="#modal_box">
                                <img src="${i.image[0]}" alt="" style="height: 200px; width: 250px">
                            </a>
                        </div>
                        <figcaption class="product_content">
                            <h4 class="product_name">
                                <a href="#">${i.name}</a>
                            </h4>
                            <div class="product_rating" style="width: 100%;">
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

                                    <div class="wishlist" style="margin-left: 20px;">                                                                    
                                        <a href="#" onclick="toggleWishlist(${i.id})" title="Add to Wishlist">
                                            <i style="color: #f6692a; border: 2px solid #f6692a; padding: 5px; border-radius: 5px;" class="fa-solid fa-heart"></i>
                                        </a>
                                    </div>
                                </ul>
                            </div>

                            <div class="price_box">
                                <c:if test="${i.price != i.salePrice}">
                                    <div style="display: flex">
                                        <span class="old_price">
                                            <fmt:formatNumber value="${i.price}" type="number" pattern="0"/> 
                                        </span> 
                                        <span style="font-weight: 400;font-size: 15px;margin-right: 5px">VNĐ</span>
                                    </div>
                                </c:if>
                                <br>

                                <div style="display: flex">
                                    <span class="current_price">
                                        <fmt:formatNumber value="${i.salePrice}" type="number" pattern="0"/> 
                                    </span> 
                                    <span style=" font-weight: 700;font-size: 17px;color: #000; margin-left: 5px">VNĐ</span>
                                </div>
                            </div>
                        </figcaption>
                    </figure>
                </article>
            </div>
        </c:forEach>

        <script type="text/javascript">
            function formatPrice(element) {
                let price = element.innerText.trim(); // Lấy giá trị trong thẻ
                let formattedPrice = parseInt(price, 10).toLocaleString("vi-VN"); // Chuyển đổi sang định dạng VN
                element.innerText = formattedPrice; // Gán lại nội dung đã format
            }

            // Lặp qua tất cả các phần tử có class "old_price" và "current_price"
            document.querySelectorAll(".old_price, .current_price").forEach(formatPrice);
        </script>
    </body>
</html>
