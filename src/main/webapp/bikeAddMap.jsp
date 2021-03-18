<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/mytool.css">
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    
    <title>IBike</title>
</head>
<body>
    
    <div class="headpho">
        <img src="pic/Youbike_top.jpg" alt="">
    </div>

    <nav  class="navbar navbar-expand-lg bg-dark navbar-dark justify-content-center sticky-top">
        <a class="navbar-brand" href="homepg.jsp"><img src="pic/Logo.png" alt=""  style="height: 35px;"></a>
        <span>&emsp;</span>
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="bike.jsp">Youbike 微笑單車查詢</a>
            </li>
            <span class="MyNavSeparator">|</span>
            
            <li class="nav-item">
                <a class="nav-link" href="park.jsp">Park 停車場查詢</a>
            </li>
            <span class="MyNavSeparator">|</span>
            
            <li class="nav-item">
                <a class="nav-link" href="bus.jsp">Bus 公車查詢</a>
            </li>
            <span class="MyNavSeparator">|</span>
            
            <li class="nav-item">
                <a class="nav-link" href="food.jsp">Food 美食查詢</a>
            </li>
        </ul>
    </nav>
   
    <div class="container-fluid contentArea"></div>
    

</body>
</html>