<%@page import="model.outJSON.ParkJson"%>
<%@page import="model.outJSON.BikeJson"%>
<%@page import="model.outJSON.BikeJsonOut"%>
<%@page import="model.crawler.BikeCrawler"%>
<%@page import="model.outJSON.ParkJsonOut"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.ParkBean"%>
<%@page import="java.io.*" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Brad Big Company</title>
</head>
<body>
	<%@ page import="model.*"%>
	<%@ page import="model.dao.*"%>
	<%
		BikeJson bike = new BikeJsonOut();
		bike.BikeDbToJsonOut();
		out.println("<h3>BikeJson save OK!</h3>");
	
		ParkJson park = new ParkJsonOut();
		park.ParkDbToJsonOut();
		out.println("<h3>ParkJson save OK!</h3>");
		
	%>

</body>
</html>