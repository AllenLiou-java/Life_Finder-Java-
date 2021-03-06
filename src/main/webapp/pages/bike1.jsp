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
	SELECT * FROM bike LIMIT ${pgFst }, ${pgds }
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
        <img src="../pic/Youbike_top.jpg" alt="">
    </div>

    <nav  class="navbar navbar-expand-lg bg-dark navbar-dark justify-content-center sticky-top">
        <a class="navbar-brand" href="../homepg.jsp"><img src="../pic/Logo.png" alt=""  style="height: 35px;"></a>
        <span>&emsp;</span>
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="bike.jsp">Youbike ??????????????????</a>
            </li>
            <span class="MyNavSeparator">|</span>
            
            <li class="nav-item">
                <a class="nav-link" href="park.jsp">Park ???????????????</a>
            </li>
            <span class="MyNavSeparator">|</span>
            
            <li class="nav-item">
                <a class="nav-link" href="bus.jsp">Bus ????????????</a>
            </li>
            <span class="MyNavSeparator">|</span>
            
            <li class="nav-item">
                <a class="nav-link" href="food.jsp">Food ????????????</a>
            </li>
        </ul>
    </nav>
   
    <br><br>

    <div style="width: 80%; margin: 0 auto; position: relative;">
    
        <table class="table table-bordered table-hover">
            <thead class="">
                <tr>
                    <th scope="tb-col">ID</th>
                    <th scope="tb-col">??????</th>
                    <th scope="tb-col">X_axis</th>
                    <th scope="tb-col">Y_axis</th>
                    <th scope="tb-col">?????????</th>
                    <th scope="tb-col">??????</th>
                    <th scope="tb-col">??????</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${pgCmd.rows }" var="row" varStatus="status">
	                <tr>
	                    <td>${row.ID }</td>
	                    <td>${row.Position }</td>
	                    <td>${row.X_axis }</td>
	                    <td>${row.Y_axis }</td>
	                    <td>${row.CArea }</td>
	                    <td>${row.AvailableCNT }</td>
	                    <td>${row.EmpCNT }</td>
	                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
	<div class="page-icon">
		<a href='?page=${prev }'>?????????</a>
		<span class="page-current">1</span>
		<a href="#">2</a>
		<a href="#">3</a>
		<a href="#">4</a>
		<a href="#">5</a>
		<a href="#">6</a>
		<a href="#">7</a>
		??????
		<a href="#">200</a>
		<a class="page-next" href='?page=${next }'>?????????</a>
	</div><br><br>
    

</body>
</html>