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
	SELECT * FROM bike
</sql:query>

<c:set var="pgds" value="5" />

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
	SELECT * FROM bus LIMIT ${pgFst }, ${pgds }
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
    
    <title>IBike</title>
</head>
<body>
    
    <div class="headpho">
        <img src="../pic/Bus_top.jpg" alt="">
    </div>

    <nav  class="navbar navbar-expand-lg bg-dark navbar-dark justify-content-center sticky-top">
        <a class="navbar-brand" href="../homepg.jsp"><img src="../pic/Logo.png" alt=""  style="height: 35px;"></a>
        <span>&emsp;</span>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="bike.jsp">Youbike 微笑單車查詢</a>
            </li>
            <span class="MyNavSeparator">|</span>
            
            <li class="nav-item">
                <a class="nav-link" href="park.jsp">Park 停車場查詢</a>
            </li>
            <span class="MyNavSeparator">|</span>
            
            <li class="nav-item active">
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
            <thead class="">
                <tr>
                    <th scope="tb-col">公車業者</th>
                    <th scope="tb-col">路線</th>
                    <th scope="tb-col">路線說明</th>
                    <th scope="tb-col">路線圖</th>
                    <th scope="tb-col">去程</th>
                    <th scope="tb-col">回程</th>                  
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${pgCmd.rows }" var="row" varStatus="status">
	                <tr>
	                    <td>${row.OperatorName }</td>
	                    <td>${row.RouteName }</td>
	                    <td>${row.Headsign }</td>
	                    <td>${row.RouteMapImageUrl }</td>
	                    <td>${row.Outbound }</td>
	                    <td>${row.Inbound }</td>
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