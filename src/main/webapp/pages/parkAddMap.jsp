<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.jsp.jstl.sql.Result"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mytool.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="https://unpkg.com/leaflet@1.6.0/dist/leaflet.css"
	integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
	crossorigin=""/>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/leaflet.markercluster/1.4.1/MarkerCluster.css"></link> 
	<link href="https://cdnjs.cloudflare.com/ajax/libs/leaflet.markercluster/1.4.1/MarkerCluster.Default.css"></link> 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0-12/css/all.min.css"/>
	
	<style type="text/css">
/* 站點狀態欄 */
	    .StopStatus ul{
	        padding-left: 10px;
	        list-style: none;
	    }
	
	    .StopStatus li{ 
	        display: inline-block;
	        margin-right: 20px;
	        list-style: none;
	    }
	
	    .StopStatus{
	        text-align: left;
	        margin: 15px 0px;
	        padding: 0px;
	
	    }
	    
/*設定換頁列*/

	
	/* 設定div樣式的整體佈局 */
	.page-icon{
		margin:40px 0 0 0;/*設定距離頂部20畫素*/
		font-size:0;/*修復行內元素之間空隙間隔*/
		text-align:center;/*設定內容居中顯示*/
	
	
	}
	
	/*設定共有的的樣式佈局，主要是進行程式碼優化，提高執行效率*/
	.page-icon a,.page-disabled,.page-next{
		border:1px solid #ccc;
		border-radius:3px;
		padding:4px 10px 5px;
		font-size:14PX;/*修復行內元素之間空隙間隔*/
		margin-right:10px;
	}
	
	/*對 a 標籤進行樣式佈局 */
	.page-icon a{
		text-decoration:none;/*取消連結的下劃線*/
		color:#005aa0;
		display: inline-block;

		
	}
	
	.page-current{
		color:#ff6600;
		padding:4px 10px 5px;
		font-size:14PX;/*修復行內元素之間空隙間隔*/
	}
	
	.page-disabled{
		color:#ccc;
	}
	
	.page-next i,.page-disabled i{
		cursor:pointer;/*設定滑鼠經過時的顯示狀態，這裡設定的是顯示狀態為小手狀態*/
		display:inline-block;/*設定顯示的方式為行內塊元素*/
		width:5px;
		height:9px;
		
	}
	.page-disabled i{
		background-position:-80px -608px;
		margin-right:3px;
	}

	.page-next i{
		background-position:-62px -608px;
		margin-left:3px;
	}



	.pgBar{
		margin:40px auto;/*設定距離頂部40畫素*/
		font-size:1;/*修復行內元素之間空隙間隔*/
		text-align:center;/*設定內容居中顯示*/
		
	}
	
	.pgBar form{
		display: inline-block;
		padding: 0px;
	}
	
	

	.pgBar a{
		font-family: '微軟正黑體','Times New Roman', Times, serif;
		color:#727272;
		text-decoration: none;
		margin: 0px;

		border:1px solid #ccc;
		border-radius:3px;
		padding:4px 10px 5px;
		font-size:14PX;/*修復行內元素之間空隙間隔*/
		margin-right:10px;
	}

	input:last-child{
		font-family: '微軟正黑體','Times New Roman', Times, serif;
		margin: 0px 20px;
		border:1px solid #ccc;
		border-radius:3px;
		padding:4px 10px 5px;
		font-size:15PX;
		background-color: teal;
		color: white;
		font-weight: bolder;
	}
	    
	    
    </style>
	
	<title>Park</title>
</head>
<body>

	<div class="headpho">
		<img src="${pageContext.request.contextPath}/pic/Parking_top.jpg" alt="">
	</div>

	<nav class="navbar navbar-expand-lg bg-dark navbar-dark justify-content-center sticky-top">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/homepg.jsp"><img
			src="${pageContext.request.contextPath}/pic/Logo.png" alt="" style="height: 35px;"></a> <span>&emsp;</span>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="bike.controller">Youbike
					微笑單車查詢</a></li>
			<span class="MyNavSeparator">|</span>

			<li class="nav-item active"><a class="nav-link" href="park.controller">Park
					停車場查詢</a></li>
			<span class="MyNavSeparator">|</span>

			<li class="nav-item"><a class="nav-link" href="bus.controller">Bus
					公車查詢</a></li>
			<span class="MyNavSeparator">|</span>

			<li class="nav-item"><a class="nav-link" href="food.jsp">Food
					美食查詢</a></li>
		</ul>
	</nav>


	 <!-- 地圖實做 -->

    <div class="contentArea">
        <h2 id="mapTitle">停車場 站點資訊即時訊息</h2>
        <div class="underLine"></div>
        <div id="map"></div>
        <div class="GeoBtn" id="jsGeoBtn"><img src="${pageContext.request.contextPath}/pic/geoIcon.svg" alt=""></div>
        
        <div class="StopStatus">
            <ul>
                <li><img src="${pageContext.request.contextPath}/pic/marker-icon-2x-green.png" alt="" width="20px">
                    <span>車位充裕</ㄋ> 
                </li>
                <li><img src="${pageContext.request.contextPath}/pic/marker-icon-2x-red.png" alt="" width="20px">
                    <span>車位將滿</span>
                </li>
                <li><img src="${pageContext.request.contextPath}/pic/marker-icon-2x-grey.png" alt="" width="20px">
                    <span>暫停營運</span>
                </li>
            </ul>
        </div>
        
    </div><br><br><br><br>
    

	<!-- 表格實做 -->
	
	<div class="InfoTable"  style="width: 80%; margin: 0 auto; position: relative;" id="pgHead">

		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th class="text-nowrap">停車場</th>
					<th class="text-nowrap">Lat (緯度)</th>
					<th class="text-nowrap">Lng (經度)</th>
					<th class="text-nowrap">地址</th>
					<th class="text-nowrap">可用車位</th>
					<th class="text-nowrap">總車位</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lists}" var="row" varStatus="status">
					<tr>
						<td>${row.name }</td>
						<td>${row.lat }</td>
						<td>${row.lng }</td>
						<td>${row.address }</td>
						<td>${row.available }</td>
						<td>${row.total }</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		
		<!-- 換頁實做 -->
		
		<div class="pgBar">
		
			<c:if test="${pageNos>1 }">
				<a href="${pageContext.request.contextPath}/pages/park.controller?pageNos=1#pgHead"> « 首頁 </a>
				<a href="${pageContext.request.contextPath}/pages/park.controller?pageNos=${pageNos-1 }#pgHead">上一頁</a>
			</c:if>
			
			<c:if test="${pageNos <recordCount }">
				<a href="${pageContext.request.contextPath}/pages/park.controller?pageNos=${pageNos+1 }#pgHead">下一頁</a>
				<a href="${pageContext.request.contextPath}/pages/park.controller?pageNos=${recordCount }#pgHead">末頁 » </a>
			</c:if>

			<form action="<c:url value="/pages/park.controller"/>" method="get">
				<h4 align="center">
					共${recordCount}頁 &nbsp <input type="text" value="${pageNos}"
						name="pageNos" size="1">頁 <input type="submit" value="到達">
				</h4>
			</form>
		</div>
		
	</div>


	<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
    integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
    crossorigin=""></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet.markercluster/1.4.1/leaflet.markercluster.js"></script>
   
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/park.js"></script>


</body>
</html>