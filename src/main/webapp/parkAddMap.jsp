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
        <img src="pic/Parking_top.jpg" alt="">
    </div>

    <nav  class="navbar navbar-expand-lg bg-dark navbar-dark justify-content-center sticky-top">
        <a class="navbar-brand" href="homepg.jsp"><img src="pic/Logo.png" alt=""  style="height: 35px;"></a>
        <span>&emsp;</span>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="bike.jsp">Youbike 微笑單車查詢</a>
            </li>
            <span class="MyNavSeparator">|</span>
            
            <li class="nav-item active">
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
   
    <div class="contentArea">
        <div class="map"></div>
        <div class="GeoBtn" id="jsGeoBtn"><img src="pic/geoIcon.svg" alt=""></div>
        <div class="panel">

            <div class="panelTop">
                <div class="toggleBtn js_toggle">
                    <svg version="1.1" id="圖層_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
                    viewBox="0 0 659.9 1156.8" style="enable-background:new 0 0 659.9 1156.8;" xml:space="preserve">
                    <g transform="translate(0.000000,2600.000000) scale(0.100000,-0.100000)" fill="#ffffff">
                        <path d="M14,20046.3c22-98,63-196,118-280c40-62,520-546,2531-2560c1365-1366,2520-2518,2568-2560c102-90,208-149,333-187
                        c78-23,108-27,220-27s142,4,220,27c533,161,760,762,463,1228c-29,46-634,658-2271,2295l-2232,2233l2240,2242
                        c1713,1715,2248,2256,2274,2300c75,129,109,243,118,388c22,389-229,729-617,836c-90,25-300,25-390,0c-95-26-176-62-255-114
                        c-54-35-630-605-2583-2556c-1382-1381-2537-2540-2566-2576c-127-155-186-323-185-525C0,20155.3,7,20081.3,14,20046.3z"/>
                    </g>
                    </svg>
                </div>
            </div>
            <div class="parkLogo">

            </div>
            <div class="contentInfo">

            </div>


        </div>


    </div>
    

</body>
</html>