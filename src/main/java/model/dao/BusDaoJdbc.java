package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import model.bean.BusBean;





public class BusDaoJdbc implements BusDAO{

	private DataSource dataSource;
	
	public BusDaoJdbc() {
	
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/ConnDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	//新增資料
	private static final String SELECT_ALL = 
			"select * from bus ";

	
	@Override
	public List<BusBean> select() {
		List<BusBean> result = new ArrayList<BusBean>();
		ResultSet rset = null;
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL)) {
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BusBean busBean = new BusBean();
				busBean.setOperatorName(rset.getString("OperatorName"));
				busBean.setRouteName(rset.getString("RouteName"));
				busBean.setHeadsign(rset.getString("Headsign"));
				busBean.setRouteMapImageUrl(rset.getString("RouteMapImageUrl"));
				busBean.setOutbound(rset.getString("Outbound"));
				busBean.setInbound(rset.getString("Inbound"));
				
				result.add(busBean);	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			if (rset!=null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		}
		return result;
	}
	
	
	private static final String INSERT = 
		"INSERT INTO bus (OperatorName, RouteName, Headsign, RouteMapImageUrl, 	Outbound, Inbound) VALUES (?,?,?,?,?,?)";
	
	//新增資料到資料庫
	@Override
	public void insert(String json) {
		//進行連線 > 下sql指令
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(INSERT)){
		
		//解析JSON資料，匯入資料庫
			JSONArray root = new JSONArray(json);
		
			for(int i=0;i<root.length();i++) {
				JSONObject dataSet = root.getJSONObject(i);
				String operatorName = dataSet.getString("公車業者");
				String routeName = dataSet.getString("路線");
				String headsign = dataSet.getString("路線說明");				
				String routeMapImageUrl = dataSet.getString("路線圖");
				String outbound = dataSet.getString("去程");
				String inbound = dataSet.getString("回程");
				
				pstmt.setString(1, operatorName);
				pstmt.setString(2, routeName);
				pstmt.setString(3, headsign);
				pstmt.setString(4, routeMapImageUrl);
				pstmt.setString(5, outbound);
				pstmt.setString(6, inbound);
				
				pstmt.executeUpdate();
			}
		
			System.out.println("BusData Insert OK :" + new Date());
		} catch (Exception e) {
				System.out.println(e.toString());
		}
		
	}
	
	//刪除資料庫資料
	private static final String DELETE = 
			"TRUNCATE TABLE bus";
	@Override
	public void deleteOldData() {
		//進行連線 > 下sql指令
			try (Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(DELETE)){	
				pstmt.execute();
			}catch(Exception e){
				System.out.println(e.toString());
			}	
		}
	
	
	
	//計算總頁數
	
	@Override
	public int getPage() {
		
		int recordCount=0, t1=0, t2=0;
		//PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select count(*) from bus";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement  pstmt = conn.prepareStatement(sql);) {
			rset = pstmt.executeQuery();
			rset.next();
			recordCount = rset.getInt(1);
			t1=recordCount%5;
			t2=recordCount/5;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		if(t1 != 0) {
			t2=t2+1;
		}
		return t2;
	}
	
	
	//查詢指定頁的資料
	
	@Override
	public List<BusBean> dataInPg(int pageNo){
		//PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BusBean> list = new ArrayList<BusBean>();
		int pageSize = 5;
		int pageStartNo = (pageNo-1) * pageSize;
		String sql = "select * from  bus limit ?,?";
		
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setInt(1, pageStartNo);
			pstmt.setInt(2, pageSize);		
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BusBean busBean = new BusBean();
				busBean.setOperatorName(rset.getString("OperatorName"));
				busBean.setRouteName(rset.getString("RouteName"));
				busBean.setHeadsign(rset.getString("Headsign"));
				busBean.setRouteMapImageUrl(rset.getString("RouteMapImageUrl"));
				busBean.setOutbound(rset.getString("Outbound"));
				busBean.setInbound(rset.getString("Inbound"));
				
				list.add(busBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
}
