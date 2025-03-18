<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Perfume Paradise Store</title>
        <link rel="icon" href="images/logo1.png"/>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css">

        <link rel="stylesheet" href="css/style.css">
        <style>
            li a:hover {
                color: #f6692a;
            }
            ul li a.active {
                color: #f6692a;
            }

            .li_header {
                color: white;
                margin-left: 50px;
                font-size: 18px;
            }

            .submit-price {
                font-size: 16px;
                background-color: black;
                color: white;
                font-weight: 600;
                padding: 5px 40px;
                border-radius: 20px;
                margin: 10px 0 20px
            }

            .submit-price:hover {
                background-color: rgba(0,0,0,0.3);
                color: black;
                font-weight: 600;
            }
        </style>
    </head>
    <body>
        <header>
            <div class="main_header header_transparent header-mobile-m">
                <div class="header_container sticky-header" style="padding: 0">
                    <div class="container-fluid" style="background-color: black">
                        <div class="row align-items-center" style="padding: 8px 0">
                            <div class="col-lg-1">
                                <div class="logo">
                                    <a href="RefineServlet"><img src="images/logoFPT.png" alt="" width="65px"></a>
                                </div>
                            </div>

                            <div class="col-lg-4" ">
                                <div class="main_menu menu_two menu_position">
                                    <nav>
                                        <ul>
                                            <li>
                                                <a href="RefineServlet" class="active"> Home</a>
                                            </li>
                                            <li><a href="aboutUs.jsp">About Us</a></li>
                                            <li>
                                                <a href="profile.jsp">Profile</a>
                                            </li>
                                            <li>
                                                <a href="ChangePassServlet">Security</a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>


                            <div class="col-lg-4">
                                <form action="" method="get" style="display: flex; justify-content: center">
                                    <input name="productName" value="${requestScope.searchAtHome != null ? requestScope.searchAtHome : ""}" id="searchId" type="text" oninput="searchByName()" placeholder="Search your perfume" style="width: 60%; padding: 4px 10px; border-radius: 15px">
                                    <button type="submit" style="border-radius: 50%; width: 40px; font-size: 18px;"><i class="fa fa-search"></i></button>
                                </form>
                            </div>
                            <div class="col-lg-3">
                                <%@ include file="header_right.jsp" %> 
                            </div>
                        </div>
                        <div class="row" style="justify-content: center; padding: 10px 0"">
                            <ul style="display: flex">
                                <li class="li_header"  >
                                    <a class="${cid_refine==0?"active":""}" href="RefineServlet?cid=${0}">ALL
                                    </a>
                                </li>
                                <c:forEach items="${requestScope.category}" var="c">
                                    <li class="li_header">
                                        <a class="${c.id==cid_refine?"active":""}" href="RefineServlet?cid_refine=${c.id}">
                                            ${c.name}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <br>
        <div class="home_section_two color_two mb-70" style="margin-top: 100px; margin-bottom: 50px">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-12">
                        <div class="home_section_left">
                            <!-- testimonial section starts  -->
                            <c:set var="cat" value="${requestScope.category}"/>
                            <div class="testimonial_style_two mb-60 rightleft">
                                <div class="testimonial_container">
                                    <div class="section_title section_title_style2">
                                        <h2 style="font-size: 20px">Filter</h2>
                                        <i style="font-size: 18px; margin-left: 5px" class="fa fa-filter"></i>
                                    </div>
                                    <form id="f1" action="RefineServlet" method="get">
                                        <div class="section_title section_title_style2">
                                            <h2 style="font-size: 16px;">Brand</h2>
                                            <fieldset class="responsiveFacets_sectionContent " aria-hidden="false">
                                                <div class="responsiveFacets_sectionItemLabel">
                                                    <input type="checkbox" class="responsiveFacets_sectionItemCheckbox" 
                                                           ${chid[0]?"checked":""} 
                                                           id="c0" 
                                                           name="cid_refinee"
                                                           value="${0}" onclick="setCheck(this)">
                                                    <label class="responsiveFacets_sectionItem" for="brand">
                                                        ALL (${allproduct.size()})
                                                    </label>
                                                </div>
                                                <c:if test="${cat!=null}">
                                                    <c:forEach begin="0" end="${cat.size()-1}" var="i">
                                                        <div class="responsiveFacets_sectionItemLabel">
                                                            <input
                                                                type="checkbox"
                                                                ${cat.get(i).getId()==cid_refine?"checked":""}
                                                                class="responsiveFacets_sectionItemCheckbox"
                                                                id="cm" 
                                                                name="cid_refinee"
                                                                value="${cat.get(i).getId()}"
                                                                ${chid[i+1]?"checked":""}
                                                                onclick="setCheck(this)"/>
                                                            <label class="responsiveFacets_sectionItem" for="brand" >
                                                                ${cat.get(i).name} (${cat.get(i).getTotalProduct()})
                                                            </label>
                                                        </div>
                                                    </c:forEach>
                                                </c:if>
                                            </fieldset>
                                        </div>

                                        <div class="section_title section_title_style2">
                                            <h2 style="font-size: 16px">Price</h2>
                                            <fieldset class="responsiveFacets_sectionContent " aria-hidden="false">
                                                <div class="shopee-filter-group__body shopee-price-range-filter__edit">
                                                    <div class="shopee-price-range-filter__inputs" style="margin: 1.25rem 2px 0.625rem;
                                                         display: flex;
                                                         justify-content: space-between;
                                                         align-items: center;
                                                         padding-right: 30px">
                                                        <input type="number" style="width: 90px" name="pricefrom" class="shopee-price-range-filter__input" placeholder="$ FROM" value="${price1 == 0 ? "$ FROM" : price1}" step="0.5" min=1>
                                                        <div class="shopee-price-range-filter__range-line" style="
                                                             flex: 1;
                                                             height: 1px;
                                                             background: #bdbdbd;
                                                             margin: 0 10px;">
                                                        </div>
                                                        <input type="number" style="width: 90px" name="priceto" class="shopee-price-range-filter__input" placeholder="$ TO" value="${price2 == 0 ? "" : price2}" step="0.5" min=1">
                                                    </div>
                                                    <button class="submit-price" style="">Apply</button>
                                                </div>
                                            </fieldset>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- testimonial section ends -->
                        </div>
                    </div>

                    <div class="col-lg-9 col-md-12">
                        <div class="home_section_right">
                            <div class="product_area" >
                                <div class="section_title_style2" style="margin-top: 50px">
                                    <c:if test="${requestScope.cat.name == null}">
                                        <div style="text-align: start; font-size: 18px">
                                            Sản phẩm hiện có
                                        </div>
                                    </c:if>
                                    <h3 style="font-weight: 600; text-decoration: underline">${requestScope.cat.name}</h3>
                                    <div style="text-align: center; font-size: 15px">
                                        ${requestScope.cat.describe}
                                    </div>
                                </div>
                                <c:set var="page" value="${requestScope.page}"/>

                                <div id ="contentt" class="row">
                                    <c:set var="proA" value=""/>
                                    <c:forEach items="${requestScope.productPage}" var="i">
                                        <div class="product_items col-lg-3" style="margin: 30px 0">
                                            <article class="single_product">
                                                <figure>
                                                    <div class="product_thumb" onclick="openModal('modal_box',${i.id}, '${i.image[0]}', '${i.image[1]}',
                                                                    '${i.name}',${i.salePrice},${i.price}, '${i.describe}', '${i.classifyStr}',
                                                                    '${i.supplier.getCompanyName()}')" 
                                                         <a href="#" class="primary_img"  data-toggle="modal" data-target="#modal_box">
                                                            <img src="${i.image[0]}" alt="" >
                                                        </a>
                                                        <a href="#" class="secondary_img"  data-toggle="modal" data-target="#modal_box">
                                                            <img src="${i.image[1]}" alt="">
                                                        </a>
                                                        <div class="action_links">
                                                            <ul>
                                                                <li class="wishlist">
                                                                    <a href="#" onclick="toggleWishlist(${i.id})" title="Add to Wishlist">
                                                                        <i style="color: #f6692a" class="fa-solid fa-heart"></i>
                                                                    </a>
                                                                </li>
                                                            </ul>

                                                        </div>
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
                                                            </ul>
                                                        </div>
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
                                </div>

                                <div class="pagination col-md-12" style="margin-top: 20px; display: flex; justify-content: center">
                                    <c:if test="${requestScope.cid_refinee == null}">
                                        <c:forEach begin="${1}" end="${requestScope.numberpage}" var="i">
                                            <a class="linkLoad ${i==1?"active":""}" id="numberPage" onclick="loadMore(this)" >${i}</a>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${requestScope.cid_refinee != null}">
                                        <c:forEach begin="${1}" end="${requestScope.numberpage}" var="i"> 
                                            <a href="RefineServlet?${stringForLink}&numberStar=${numberStar}&pricefrom=${price1}&priceto=${price2}&discount=${discount}&page=${i}">${i}</a>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- product section ends -->
            </div>
        </div>

        <jsp:include page="footer.jsp"/>
        <div class="modal fade" id="modal_box" role="dialog"></div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>   
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/owl.carousel.min.js"></script>
        <script src="js/countdown.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
        <script src="js/clickevents.js"></script>
        <script src="js/main.js"></script>
        <script type="text/javascript">
                                                function setCheckk(obj) {
                                                    var fries = document.getElementsByName('discount');
                                                    var checked = obj.value;
                                                    console.log(obj.checked)
                                                    for (var i = 0; i < fries.length; i++) {
                                                        if (checked == fries[i].value) {
                                                            fries[i].checked = obj.checked;
                                                        } else {
                                                            fries[i].checked = false;
                                                        }
                                                    }

                                                    document.getElementById('f1').submit();
                                                }


                                                function setCheck(obj) {
                                                    var fries = document.getElementsByName('cid_refinee');
                                                    if ((obj.id == 'c0') && (fries[0].checked) == true) {
                                                        for (var i = 1; i < fries.length; i++) {
                                                            fries[i].checked = false;
                                                        }
                                                    } else {
                                                        for (var i = 1; i < fries.length; i++) {
                                                            if (fries[i].checked == true) {
                                                                fries[0].checked = false;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    document.getElementById('f1').submit();
                                                }

                                                function searchByName() {
                                                    var text = document.querySelector("#searchId").value;
                                                    $.ajax({
                                                        url: "/PerfumeShop/SearchServlet",
                                                        type: "get",
                                                        data: {
                                                            txt: text
                                                        },
                                                        success: function (data) {
                                                            var row = document.getElementById("contentt");
                                                            row.innerHTML = data;
                                                        },
                                                        error: function (xhr) {
                                                        }
                                                    });
                                                }


        </script>
    </body>
</html>
