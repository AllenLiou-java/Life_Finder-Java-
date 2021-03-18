package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.bean.BikeBean;




public class BikeDaoJdbc implements BikeDAO{

	private DataSource dataSource;
	
	public BikeDaoJdbc() {
	
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/ConnDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	//新增資料
	private static final String SELECT_ALL = 
			"select * from bike ";

	@Override
	public List<BikeBean> select() {
		List<BikeBean> result = new ArrayList<BikeBean>();
		ResultSet rset = null;
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL)) {
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BikeBean bikeBean = new BikeBean();
				bikeBean.setId(rset.getString("ID"));
				bikeBean.setPosition(rset.getString("Position"));
				bikeBean.setEname(rset.getString("EName"));
				bikeBean.setCaddress(rset.getNString("CAddress"));
				bikeBean.setX(rset.getDouble("X_axis"));
				bikeBean.setY(rset.getDouble("Y_axis"));
				bikeBean.setCarea(rset.getString("CArea"));
				bikeBean.setAvailableCNT(rset.getInt("AvailableCNT"));
				bikeBean.setEmpCNT(rset.getInt("EmpCNT"));
				bikeBean.setUpdateTime(rset.getString("UpdateTime"));
				
				result.add(bikeBean);	
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
	
	
	//計算總頁數
	@Override
	public int getPage() {
		
		int recordCount=0, t1=0, t2=0;
		//PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select count(*) from bike";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement  pstmt = conn.prepareStatement(sql);) {
			rset = pstmt.executeQuery();
			rset.next();
			recordCount = rset.getInt(1);
			t1=recordCount%10;
			t2=recordCount/10;
			
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
	public List<BikeBean> dataInPg(int pageNo){
		//PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<BikeBean> list = new ArrayList<BikeBean>();
		int pageSize = 10;
		int pageStartNo = (pageNo-1) * pageSize;
		String sql = "select * from  bike limit ?,?";
		
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setInt(1, pageStartNo);
			pstmt.setInt(2, pageSize);		
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BikeBean bikeBean = new BikeBean();
				bikeBean.setId(rset.getString("ID"));
				bikeBean.setPosition(rset.getString("Position"));
				bikeBean.setEname(rset.getString("EName"));
				bikeBean.setCaddress(rset.getNString("CAddress"));
				bikeBean.setX(rset.getDouble("X_axis"));
				bikeBean.setY(rset.getDouble("Y_axis"));
				bikeBean.setCarea(rset.getString("CArea"));
				bikeBean.setAvailableCNT(rset.getInt("AvailableCNT"));
				bikeBean.setEmpCNT(rset.getInt("EmpCNT"));
				bikeBean.setUpdateTime(rset.getString("UpdateTime"));
				
				list.add(bikeBean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	

}
