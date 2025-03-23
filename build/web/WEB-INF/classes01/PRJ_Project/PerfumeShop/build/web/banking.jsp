
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Nạp tiền FUO</title>
        <link rel="icon" href="images/logo1.png"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>
        <style type="text/css">
            body {
                background-color: #111;
                color: white;
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }
            
            .page-container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }
            
            .page-title {
                font-size: 2rem;
                margin-bottom: 20px;
            }
            
            .payment-container {
                background-color: #111;
                border-radius: 10px;
                padding: 20px;
                margin-top: 20px;
            }
            
            .payment-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 20px;
            }
            
            .account-title {
                font-size: 1.5rem;
                font-weight: bold;
            }
            
            .qr-title {
                font-size: 1.5rem;
                font-weight: bold;
                text-align: right;
            }
            
            .account-info {
                margin-bottom: 15px;
            }
            
            .account-label {
                font-weight: bold;
                margin-bottom: 5px;
            }
            
            .account-value-red {
                color: #ff4d4d;
                font-weight: bold;
            }
            
            .account-value-blue {
                color: #4d94ff;
                font-weight: bold;
            }
            
            .payment-note {
                margin-top: 20px;
            }
            
            .point-value {
                margin-top: 30px;
                margin-bottom: 30px;
            }
            
            .point-label {
                font-weight: bold;
                margin-bottom: 5px;
            }
            
            .point-rate {
                color: #ff4d4d;
                font-weight: bold;
            }
            
            .warning-text {
                margin-top: 15px;
                line-height: 1.5;
            }
            
            .highlight-text {
                color: #ff4d4d;
                font-weight: bold;
            }
            
            .qr-container {
                background-color: white;
                padding: 15px;
                border-radius: 5px;
                text-align: center;
                max-width: 400px;
                margin-left: auto;
            }
            
            .payment-logos {
                display: flex;
                justify-content: space-around;
                margin-top: 10px;
            }
            
            .payment-logo {
                height: 30px;
            }
            
            .payment-content {
                display: flex;
                justify-content: space-between;
            }
            
            .account-section {
                flex: 1;
                padding-right: 20px;
            }
            
            .qr-section {
                flex: 1;
                display: flex;
                justify-content: flex-end;
            }
        </style>

        <script>
            function acceptPayment() {
                alert("Hãy đợi admin xác minh chuyển khoản");
                window.location.href = "profile.jsp"; // Điều hướng về trang chính
            }
        </script>
    </head>
    <body>
        <div class="page-container">
            <h1 class="page-title">Nạp tiền FPTPerfume</h1>
            
            <div class="payment-container">
                <div class="payment-header">
                    <div class="account-title">Tài khoản tiếp nhận nạp FPTPerfume</div>
                    <div class="qr-title">QUÉT QR NẠP TIỀN</div>
                </div>
                
                <div class="payment-content">
                    <div class="account-section">
                        <div class="account-info">
                            <div class="account-label">CHỦ TÀI KHOẢN:</div>
                            <div class="account-value-red">Nguyen Van Linh</div>
                        </div>
                        
                        <div class="account-info">
                            <div class="account-label">SỐ TK:</div>
                            <div class="account-value-red">1030760599</div>
                        </div>
                        
                        <div class="account-info">
                            <div class="account-label">NGÂN HÀNG:</div>
                            <div class="account-value-blue">VietcomBank</div>
                        </div>
                        
                        <div class="account-info">
                            <div class="account-label">NỘI DUNG CHUYỂN:</div>
                            <div class="account-value-red">Nap tien</div>
                        </div>
                        
                        <div class="warning-text">
                            Vui lòng chuyển đúng nội dung chuyển tiền để tránh trường hợp không nhận được tiền, nếu chuyển sai nội dung vui lòng liên hệ với <span class="highlight-text">Admin</span> để được hỗ trợ.
                        </div>
                        
                        <div class="warning-text">
                            Sau khi chuyển tiền thành công, vui lòng chờ ít nhất 1 phút để hệ thống xử lý.
                        </div>
                    </div>
                    
                    <div class="qr-section">
                        <div class="qr-container">
                            <img src="images/Banking.png" alt="MB Bank QR Code" style="width: 100%; max-width: 300px;">
                            
                            <div class="payment-logos">
                                <img src="images/napas247.jpg" alt="Napas 247" class="payment-logo">
                                <img src="images/mono.jpg" alt="MoMo" class="payment-logo">
                                <img src="images/vnpay.jpg" alt="VNPay" class="payment-logo">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>