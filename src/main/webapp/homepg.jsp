<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/mytool.css" type="text/css">
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/myjstool.js"></script>
    
    <title>Home</title>

</head>
    <body>
        <div class="container-fluid">
                <div class="" id="ar12">
                    <nav  class="navbar navbar-expand-lg  bg-dark navbar-dark  justify-content-center fixed-top">
                        <a class="navbar-brand" href="homepg.jsp"><img src="pic/Logo.png" alt=""  style="height: 35px;"></a>
                        <span>&emsp;</span>
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link nav-title" href="pages/bike.controller">Youbike 微笑單車查詢</a>                            
                            </li>
                            <span class="MyNavSeparator">|</span>
                            
                            <li class="nav-item">
                                <a class="nav-link nav-title" href="pages/park.controller">Park 停車場查詢</a>
                            </li>
                            <span class="MyNavSeparator">|</span>
                            
                            <li class="nav-item">
                                <a class="nav-link nav-title" href="pages/bus.controller">Bus 公車查詢</a>
                            </li>
                            <span class="MyNavSeparator">|</span>
                            
                            <li class="nav-item nav-title">
                                <a class="nav-link" href="pages/food.jsp">Food 美食查詢</a>
                            </li>
                        </ul>
                    </nav>
                </div>
        </div>

        
        <div id="myCarousel" class="carousel slide">

            <!-- Indicators -->
            <ul class="carousel-indicators">
                <li class="item1 active"></li>
                <li class="item2"></li>
                <li class="item3"></li>
                <li class="item4"></li>
                <li class="item5"></li>
            </ul>

            <!-- The slideshow -->
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <a href="homepg.html">
                        <img src="pic/Index_01.jpg" alt="" width=100% height="50%">
                    </a>
                </div>
                <div class="carousel-item">
                    <img src="pic/Index_02.jpg" alt="" width=100% height="50%">
                </div>
                <div class="carousel-item">
                    <img src="pic/Index_03.jpg" alt="" width=100% height=50%>
                </div>
                <div class="carousel-item">
                    <img src="pic/Index_04.jpg" alt="" width=100% height=50%>
                </div>
                <div class="carousel-item">
                    <img src="pic/Index_05.jpg" alt="" width=100% height="50%">
                </div>
            </div>
        </div>

        <div class="container-fluid">    
            <div class="row">
                <div class="col-1" id="ar31"></div>
                
                <div class="col-10" id="ar32">
                    
                    <section class="ImgLeft" >
                        <div class="IntroPart">
                            <div class="card-photo"><img src="pic/role_1.png" alt="" id="class1-ph" ></div>
                            <div class="card-desc">
                                <div id="class1-wd">
                                    <h1 class="desc-header">YOUBIKE資訊</h1>
                                    <h1 class="desc-header">查詢超簡單!</h1><br>
                                    <div class="div-line"></div><br>
                                    <p class="desc-ctx"> LIFE FINDER APP讓您輕鬆查詢最近的YOUBIKE租賃站點位置，以及隨時掌握站點車輛車位剩餘數量，立即下載APP讓您輕鬆享受在台中城市漫遊的騎乘樂趣。</p>
                                    
                                </div>
                            </div>
                        </div>
                    </section>

                    <section class="ImgRight">
                        <div class="IntroPart">
                            <div class="card-photo"><img src="pic/role_2.png" alt="" id="class2-ph"></div>
                            <div class="card-desc">
                                <div id="class2-wd">
                                    <h1 class="desc-header">等公車，</h1>
                                    <h1 class="desc-header">也可以很輕鬆!</h1><br>
                                    <div class="div-line"></div><br>
                                    <p class="desc-ctx"> 等公車苦等不著、站在馬路邊眼看公車開走，是公車族常有的經驗，LIFE FINDER可以查詢你目前所在地附近有哪些站牌，以及該站牌有哪些公車路線，再到查詢該路線公車的動態。透過手機、網路就可以隨手查，就不用再擔心錯過公車。</p>
                                    
                                </div>
                            </div>
                        </div>
                    </section>

                    <section class="ImgLeft">
                        <div class="IntroPart">
                            <div class="card-photo"><img src="pic/role_3.png" alt="" id="class3-ph"></div>
                            <div class="card-desc">
                                <div id="class3-wd">
                                    <h1 class="desc-header">找車位好難？</h1>
                                    <h1 class="desc-header">快來下載LIFE FINDER</h1><br>
                                    <div class="div-line"></div><br>
                                    <p class="desc-ctx"> 最簡單的快捷查詢服務，在所處地點輸入停車位，配合定位功能就可以找到附近的停車場及停車場剩餘車位。不過沒有功能路邊停車格的查詢功能，只能找地圖上的停車處。不過好處就是配合導航功能，可以直接開到停車場，算是簡單易用。</p>   
                                </div>
                        </div>
                    </section>

                    <section class="ImgRight">
                        <div class="IntroPart">
                            <div class="card-photo"><img src="pic/role_4.png" alt="" id="class4-ph"></div>
                            <div class="card-desc">
                                <div id="class4-wd">
                                    <h1 class="desc-header">你餓了嗎？</h1>
                                    <h1 class="desc-header">不知道要吃什麼？</h1><br>
                                    <div class="div-line"></div><br>
                                    <p class="desc-ctx"> 當出門旅行，來到一個陌生的景點，不知道應該找些什麼當地特色的小吃、餐廳來大快朵頤？利用LIFE FINDER APP尋找沒吃過的美食，實際嘗試看看，也是生活裡有趣的冒險。</p>         
                                </div>
                        </div>
                    </section>

                </div>

                <div class="col-1" id="ar33"></div>
            </div>
        </div>

        <div class="jumbotron text-center" style="margin-bottom: 0">
            <p></p>
        </div>


        <script>
            $(document).ready(function() {
                // Activate Carousel with a specified interval
                $("#myCarousel").carousel({
                    interval : 5000
                });

                // Enable Carousel Indicators
                $(".item1").click(function() {
                    $("#myCarousel").carousel(0);
                });
                $(".item2").click(function() {
                    $("#myCarousel").carousel(1);
                });
                $(".item3").click(function() {
                    $("#myCarousel").carousel(2);
                });
                $(".item4").click(function() {
                    $("#myCarousel").carousel(3);
                });
                $(".item5").click(function() {
                    $("#myCarousel").carousel(4);
                });
            });
        </script>
        
    </body>
</html>