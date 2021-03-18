<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.jsp.jstl.sql.Result"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme();

%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/mytool.css">
<script src="../js/jquery-3.5.1.min.js"></script>
<script src="../js/bootstrap.bundle.min.js"></script>
<script src="../js/bootstrap.min.js"></script>


<base href="<%=basePath%>">
<title>Park</title>
</head>
<body>

	<div class="headpho">
		<img src="../pic/Parking_top.jpg" alt="">
	</div>

	<nav
		class="navbar navbar-expand-lg bg-dark navbar-dark justify-content-center sticky-top">
		<a class="navbar-brand" href="../homepg.jsp"><img
			src="../pic/Logo.png" alt="" style="height: 35px;"></a> <span>&emsp;</span>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="bike.jsp">Youbike
					微笑單車查詢</a></li>
			<span class="MyNavSeparator">|</span>

			<li class="nav-item active"><a class="nav-link" href="park.jsp">Park
					停車場查詢</a></li>
			<span class="MyNavSeparator">|</span>

			<li class="nav-item"><a class="nav-link" href="bus.jsp">Bus
					公車查詢</a></li>
			<span class="MyNavSeparator">|</span>

			<li class="nav-item"><a class="nav-link" href="food.jsp">Food
					美食查詢</a></li>
		</ul>
	</nav>

	<br>
	<br>

	<div style="width: 80%; margin: 0 auto; position: relative;">

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
	</div>


	<div class="pgBar">

		<form action="<c:url value="/pages/park.controller"/>" method="get">

			<c:if test="${pageNos>1 }">
				<a href="/final-webproj/pages/park.controller?pageNos=1"> « 首頁 </a>
				<a href="/final-webproj/pages/park.controller?pageNos=${pageNos-1 }">上一頁</a>
			</c:if>
			
			<c:if test="${pageNos <recordCount }">
				<a href="/final-webproj/pages/park.controller?pageNos=${pageNos+1 }">下一頁</a>
				<a href="/final-webproj/pages/park.controller?pageNos=${recordCount }">末頁 » </a>
			</c:if>

			<h4 align="center">
				共${recordCount}頁 &nbsp <input type="text" value="${pageNos}"
					name="pageNos" size="1">頁 <input type="submit" value="到達">
			</h4>
		</form>
	</div>




</body>
</html>