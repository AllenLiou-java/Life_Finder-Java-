<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.jsp.jstl.sql.Result"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>    

<%
String path = request.getContextPath();
String basePath = request.getScheme();

System.out.println(request.getAttribute("lists"));

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
    
    <title>IBike</title>
</head>
<body>
    
    <div class="headpho">
        <img src="../pic/Youbike_top.jpg" alt="">
    </div>

    <nav  class="navbar navbar-expand-lg bg-dark navbar-dark justify-content-center sticky-top">
        <a class="navbar-brand" href="../homepg.jsp"><img src="../pic/Logo.png" alt=""  style="height: 35px;"></a>
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
   
    <br><br>

    <div style="width: 80%; margin: 0 auto; position: relative;">
    
        <table class="table table-bordered table-hover">
            <thead class="">
                <tr>
                    <th scope="tb-col">ID</th>
                    <th scope="tb-col">站名</th>
                    <th scope="tb-col">X_axis</th>
                    <th scope="tb-col">Y_axis</th>
                    <th scope="tb-col">地區別</th>
                    <th scope="tb-col">可借</th>
                    <th scope="tb-col">可停</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${lists}" var="row" varStatus="status">
	                <tr>
	                    <td>${row.id }</td>
	                    <td>${row.position }</td>
	                    <td>${row.x }</td>
	                    <td>${row.y }</td>
	                    <td>${row.carea }</td>
	                    <td>${row.availableCNT }</td>
	                    <td>${row.empCNT }</td>
	                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
	<div class="pgBar">

		<form action="<c:url value="/pages/bike.controller"/>" method="get">

			<c:if test="${pageNos>1 }">
				<a href="/final-webproj/pages/bike.controller?pageNos=1"> « 首頁 </a>
				<a href="/final-webproj/pages/bike.controller?pageNos=${pageNos-1 }">上一頁</a>
			</c:if>
			
			<c:if test="${pageNos <recordCount }">
				<a href="/final-webproj/pages/bike.controller?pageNos=${pageNos+1 }">下一頁</a>
				<a href="/final-webproj/pages/bike.controller?pageNos=${recordCount }">末頁 » </a>
			</c:if>

			<h4 align="center">
				共${recordCount}頁 &nbsp <input type="text" value="${pageNos}"
					name="pageNos" size="1">頁 <input type="submit" value="到達">
			</h4>
		</form>
	</div>
    

</body>
</html>