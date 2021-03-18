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
		ParkJsonOut ojson = new ParkJsonOut();
		
		//資料輸出
		try {
			ObjectOutputStream oout = new ObjectOutputStream(
			new FileOutputStream("E:\\資策會課程\\JAVAEE\\eclipse-workspace\\final-webproj\\src\\main\\webapp\\json\\parkInfo.json"));
			
			ParkJsonOut res = new ParkJsonOut();
			oout.writeObject(res);
			System.out.println("set parkInfo OK");
			
		} catch (Exception e) {
			System.out.println(e.toString());
		} 
		
		
		
		ParkDAO parkDao = new ParkDaoJdbc();
		parkDao.parkOutJson();
		
		
		List<ParkBean> select = parkDao.select();
		out.println("<h3>select:" + select + "</h3>");
		
		List<ParkBean> pgdata = parkDao.dataInPg(10);
		out.println("<h3>pgdata:" + pgdata + "</h3>");
		
		int pages = parkDao.getPage();
		out.println("<h3>pages:" + pages + "</h3>");
	%>

	<%@ page import="java.sql.*"%>
	<%@ page import="javax.sql.DataSource"%>
	<%@ page import="javax.naming.*"%>
	<%
	Context ctx = new InitialContext();
	DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ConnDB");
	Connection conn = dataSource.getConnection();
	Statement stmt = conn.createStatement();
	ResultSet rset = stmt.executeQuery("select * from park");
	while (rset.next()) {
		Double Lat = rset.getDouble("lat");
		Double Lng = rset.getDouble("lng");

		//out.println("<h3>" + Lat + ":" + Lng + "</h3>");
	}

	rset.close();
	stmt.close();
	conn.close();
	%>
</body>
</html>