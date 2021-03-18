package model.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
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

import org.json.JSONArray;
import org.json.JSONObject;

import model.bean.BikeBean;
import model.bean.ParkBean;



public class ParkDaoJdbc implements ParkDAO{

	private DataSource dataSource;
	
	public ParkDaoJdbc() {
	
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/ConnDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	//新增資料
	private static final String SELECT_ALL = 
			"select * from park ";

	@Override
	public List<ParkBean> select() {
		List<ParkBean> result = new ArrayList<ParkBean>();
		ResultSet rset = null;
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL)) {
			rset = pstmt.executeQuery();
			while(rset.next()) {
				ParkBean parkBean = new ParkBean();
				parkBean.setLat(rset.getDouble("lat"));
				parkBean.setLng(rset.getDouble("lng"));
				parkBean.setName(rset.getString("name"));
				parkBean.setAddress(rset.getString("address"));
				parkBean.setAvailable(rset.getInt("available"));
				parkBean.setTotal(rset.getInt("total"));
				
				result.add(parkBean);	
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
		String sql = "select count(*) from park";
		
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
		public List<ParkBean> dataInPg(int pageNo){
			//PreparedStatement pstmt = null;
			ResultSet rset = null;
			List<ParkBean> list = new ArrayList<ParkBean>();
			int pageSize = 10;
			int pageStartNo = (pageNo-1) * pageSize;
			String sql = "select * from  park limit ?,?";
			
			
			try (
					Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);){
				
				pstmt.setInt(1, pageStartNo);
				pstmt.setInt(2, pageSize);		
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					ParkBean parkBean = new ParkBean();
					parkBean.setLat(rset.getDouble("lat"));
					parkBean.setLng(rset.getDouble("lng"));
					parkBean.setName(rset.getString("name"));
					parkBean.setAddress(rset.getString("address"));
					parkBean.setAvailable(rset.getInt("available"));
					parkBean.setTotal(rset.getInt("total"));
					
					list.add(parkBean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			return list;
		}
	
	
	@Override
	public boolean parkOutJson() {
		
		ResultSet rset = null;
		boolean flag = false;
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL)) {
			rset = pstmt.executeQuery();
			JSONObject object = new JSONObject();
			JSONArray array = new JSONArray();
			
			while(rset.next()) {
				if(rset != null) {
					JSONObject subObj = new JSONObject();
					subObj.put("lat", rset.getDouble("lat"));
					subObj.put("lng", rset.getDouble("lng"));
					subObj.put("name", rset.getString("name"));
					subObj.put("address", rset.getString("address"));
					subObj.put("available", rset.getInt("available"));
					subObj.put("total", rset.getInt("total"));
					
					array.put(subObj);
				}
			}
			object.put("parkInfo", array);
			System.out.println(array);
			saveJson(array.toString());
			
			flag = true;
			


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
		return flag;
	
	}
	
	
	//save Json
	static void saveJson(String json) {
		try {
	
			FileOutputStream fop = new FileOutputStream("E:\\資策會課程\\JAVAEE\\eclipse-workspace\\final-webproj\\src\\main\\webapp\\json\\parkInfo.json");
			OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
			writer.append(json);
			writer.flush();
			writer.close();
			
			System.out.println("OK");
		
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
	
}
