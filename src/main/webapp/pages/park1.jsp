<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.jsp.jstl.sql.Result"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>    


<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/lfproj?serverTimezone=Asia/Taipei"
	user="root"
	password="root"
/>


<sql:query var="cmd">
	SELECT * FROM park
</sql:query>

<c:set var="pgds" value="20" />

<%
	Result result = (Result)pageContext.getAttribute("cmd");
	int rowCount = result.getRowCount();
	int pgds = Integer.parseInt((String)pageContext.getAttribute("pgds"));
	int pages = (int)Math.ceil((rowCount * 1.0 /pgds));
	pageContext.setAttribute("pages", pages);
%>

<c:set var="page" value="${param.page == null? 1:param.page }" />
<c:set var="pgFst" value="${(page - 1) * pgds}" />
<c:set var="prev" value="${page == 1? 1 : page - 1 }" />
<c:set var="next" value="${page == pages? page : page + 1 }" />

<sql:query var="pgCmd">
	SELECT * FROM park LIMIT ${pgFst }, ${pgds }
</sql:query>



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

    <title>Park</title>
</head>
<body>
    
    <div class="headpho">
        <img src="../pic/Parking_top.jpg" alt="">
    </div>

    <nav  class="navbar navbar-expand-lg bg-dark navbar-dark justify-content-center sticky-top">
        <a class="navbar-brand" href="../homepg.jsp"><img src="../pic/Logo.png" alt=""  style="height: 35px;"></a>
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
   
    <br><br>

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
	            	<c:forEach items="${pgCmd.rows }" var="row" varStatus="status">
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
    
    
	<div class="page-icon">
		<a href='?page=${prev }'>上一頁</a>
		<span class="page-current">1</span>
		<a href="#">2</a>
		<a href="#">3</a>
		<a href="#">4</a>
		<a href="#">5</a>
		<a href="#">6</a>
		<a href="#">7</a>
		……
		<a href="#">200</a>
		<a class="page-next" href='?page=${next }'>下一頁</a>
	</div><br><br>
	
 
</body>
</html>