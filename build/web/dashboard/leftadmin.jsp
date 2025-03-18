<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Sidebar -->
<nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-black" style="padding: 0px; width: 270px; background-color: black">
    <div class="position-sticky" >
        <div class="list-group list-group-flush mx-3 mt-4" style="margin: 0">
            <div class="footer_logo" style="text-align: center; margin-bottom: 0">
                <a href="RefineServlet"><img src="images/logoFPT.png" alt="" width="65px"></a>
            </div>
            <a href="DashBoardServlet" class="list-group-item list-group-item-action" aria-current="true" style="margin-top: 10px;">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-tachometer-alt fa-fw me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Main dashboard</span>
            </a>
            <a href="InvoiceServlet" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-file-invoice-dollar fa-fw me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Invoice</span></a>
            <a href="ManagerAccountServlet" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-user-circle fa-fw me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Accounts</span>
            </a>
            <a href="ManagerWalletServlet" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fa-solid fa-wallet"></i>
                <span style="font-size: 16px; font-weight: 600">Wallets</span>
            </a>
            <a href="ManagerProductServlet" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" <i class="fa-solid fa-cart-shopping"></i>
                <span style="font-size: 16px; font-weight: 600">Products</span>
            </a>
            <a href="ManagerSupplierServlet" class="list-group-item list-group-item-action" style="margin-top: 10px">
                <i style="margin-right: 10px; font-size: 18px" class="fas fa-parachute-box fa-fw me-3"></i>
                <span style="font-size: 16px; font-weight: 600">Supplier</span>
            </a>
        </div>
    </div>
</nav>